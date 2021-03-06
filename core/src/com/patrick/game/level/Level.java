package com.patrick.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.*;
import com.patrick.game.entity.*;
import com.patrick.game.util.Math;
import com.patrick.game.util.OneShotTimer;
import com.patrick.game.util.Settings;
import com.patrick.game.util.Timer;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Wave> waves;
    private Player player;
    private List<Bullet> bullets;
    private List<Particle> particles;
    private List<Resource> resources;
    private List<Entity> toRemove;

    private OneShotTimer respawnTimer;
    private OneShotTimer waveStartTimer;

    private int currentWave;
    private int difficulty;

    private boolean finished;

    public boolean isFinished() {
        return this.finished;
    }

    public Level(int difficulty, Player player) {
        this.difficulty = difficulty;
        this.waves = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.currentWave = 0;
        this.finished = false;
        this.player = player;
        this.fillWaves();
        this.resources.add(ResourceSpawnController.RANDOM_RESOURCE());
    }

    private void fillWaves() {
        for (int i = 0; i < Settings.WAVE_COUNT; i++) {
            this.waves.add(new Wave(this.difficulty + (i * 3)));
        }
    }

    private void killPlayer() {
        player.setPosition(new Vector2(-200, -200));
        this.respawnTimer = new OneShotTimer(5f);
    }

    private void resetPlayer() {
        player.setPosition(new Vector2(CameraController.camera.viewportWidth / 2, 40));
    }

    public void process(float delta, BitmapFont font, BitmapFont redFont, Batch batch) {
        this.removeEntities();
        this.processBulletEnemyCollisions();
        this.processPlayerCollisions();
        this.processWave();
        this.processRespawn();
        this.updateTimers(delta);

        for (Bullet bullet : this.bullets) {
            this.removeOffScreen(bullet);
            bullet.update(delta);
            bullet.render(font, batch);
            bullet.move(delta);
        }

        for (Particle particle : this.particles) {
            this.removeOffScreen(particle);
            this.removeDeadParticle(particle);
            particle.update(delta);
            particle.render(font, batch);
            particle.move(delta);
        }

        for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
            enemy.update(delta);
            enemy.render(font, batch);
            enemy.move(delta);
            if (MovementController.processEnemyMovement(enemy, this.player, CameraController.camera.viewportWidth)) {
                // no work
                this.toRemove.add(new Bullet(new Vector2(enemy.x(), enemy.y()), -Settings.BULLET_SPEED, 0, 'o', false, Bullet.BulletOwner.ENEMY));
            }
        }

        for (Resource resource : this.resources) {
            resource.update(delta);
            resource.render(redFont, batch);
            resource.move(delta);
            if (MovementController.processResourceMovement(this.player, resource))
                this.toRemove.add(resource);
        }
        this.player.update(delta);
        this.player.render(font, batch);
        this.player.move(delta);
        if (MovementController.processPlayerMovement(this.player))
            this.firePlayerWeapon();
//            uncomment to test wave progression
//            this.toRemove.addAll(this.waves.get(this.currentWave).getEnemies());
    }

    private void firePlayerWeapon() {
        switch (this.player.getGunLevel()) {
            case 0:
                if (this.player.getBulletCooldown() < Settings.BULLET_COOLDOWN) {
                    this.bullets.add(new Bullet(new Vector2(this.player.x(), this.player.y()), Settings.BULLET_SPEED, 0, 'o', false, Bullet.BulletOwner.PLAYER));
                    this.player.addBulletCooldown();
                }
                break;
            case 1:
                for (int i = 0; i < Settings.SPREAD_FIRE_COUNT; i++) {
                    if (this.player.getBulletCooldown() < Settings.BULLET_COOLDOWN) {
                        this.bullets.add(new Bullet(new Vector2(this.player.x(), this.player.y()), Math.FLOAT_RANDOM_BETWEEN(Settings.BULLET_SPEED * .8f, Settings.BULLET_SPEED), 0, 'o', true, Bullet.BulletOwner.PLAYER));
                        this.player.addBulletCooldown();
                    }
                }
                break;
            default:
                if (this.player.getBulletCooldown() < Settings.BULLET_COOLDOWN) {
                    this.bullets.add(new Bullet(new Vector2(this.player.x(), this.player.y()), Settings.BULLET_SPEED, 0, '.', false, Bullet.BulletOwner.PLAYER));
                    this.player.addBulletCooldown();
                }
        }
    }

    private void removeOffScreen(Entity entity) {
        if (entity.x() > CameraController.camera.viewportWidth || entity.x() < 0 || entity.y() > CameraController.camera.viewportHeight || entity.y() < 0)
            this.toRemove.add(entity);
    }

    private void removeDeadParticle(Particle particle) {
        if (particle.getTimeToLive() <= 0)
            this.toRemove.add(particle);
    }

    private void processWave() {
        if (this.waves.get(this.currentWave).getEnemies().size() == 0)
            if (this.waveStartTimer == null) this.waveStartTimer = new OneShotTimer(5f);
        if (this.waveStartTimer != null)
            if (this.waveStartTimer.isFinished()) {
                this.progressWave();
            }
    }


    private void progressWave() {
        final int random = Math.RANDOM_BETWEEN(0, 3);
        if (this.currentWave < 4) {
            this.currentWave++;
            this.waveStartTimer = null;
            if (random == 1)
                this.resources.add(ResourceSpawnController.RANDOM_RESOURCE());
        } else {
            this.finished = true;
        }
    }

    private void updateTimers(float delta) {
        if (this.respawnTimer != null) this.respawnTimer.update(delta);
        if (this.waveStartTimer != null) this.waveStartTimer.update(delta);
    }

    private void processRespawn() {
        if (this.respawnTimer != null) {
            if (this.respawnTimer.isFinished()) {
                this.respawnTimer = null;
                this.resetPlayer();
            }
        }
    }

    private void processBulletEnemyCollisions() {
        for (Bullet bullet : this.bullets) {
            for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
                if (CollisionController.BASIC_COLLISION(bullet, enemy)) {
                    this.toRemove.add(enemy);
                    this.toRemove.add(bullet);
                    this.particles.addAll(ParticleController.EXPLOSION_PARTICLES(enemy, Settings.EXPLOSION_SIZE));
                }
            }
        }
    }

    private void processPlayerCollisions() {
        for (Bullet bullet : this.bullets) {
            if (bullet.getOwner() != Bullet.BulletOwner.PLAYER) {
                if (CollisionController.BASIC_COLLISION(this.player, bullet)) {
                    this.toRemove.add(bullet);
                    this.particles.addAll(ParticleController.EXPLOSION_PARTICLES(player, Settings.EXPLOSION_SIZE));
                    this.killPlayer();
                    this.player.removeLife();
                }
            }
        }
        for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
            if (CollisionController.BASIC_COLLISION(this.player, enemy)) {
                this.particles.addAll(ParticleController.EXPLOSION_PARTICLES(player, Settings.EXPLOSION_SIZE));
                this.killPlayer();
                this.player.removeLife();
            }
        }
        for (Resource resource : this.resources) {
            if (CollisionController.BASIC_COLLISION(this.player, resource)) {
                this.player.processResource(resource);
                this.toRemove.add(resource);
            }
        }
    }

    private void removeEntities() {
        for (Entity entity : this.toRemove) {
            if (entity instanceof Bullet)
                this.bullets.remove(entity);
            if (entity instanceof Particle)
                this.particles.remove(entity);
            if (entity instanceof Enemy)
                this.waves.get(currentWave).removeEnemy(entity);
            if (entity instanceof Resource)
                this.resources.remove(entity);
        }
    }
}