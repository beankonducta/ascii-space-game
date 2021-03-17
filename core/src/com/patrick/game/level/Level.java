package com.patrick.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.*;
import com.patrick.game.entity.*;
import com.patrick.game.util.Math;
import com.patrick.game.util.OneShotTimer;
import com.patrick.game.util.Settings;

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

    private boolean bossKilled;
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
        this.bossKilled = false;
        this.player = player;
        this.fillWaves();
        this.resources.add(ResourceSpawnController.randomResource());
    }

    private void fillWaves() {
        for (int i = 0; i < Settings.WAVE_COUNT - 1; i++) {
            this.waves.add(new Wave(this.difficulty + (i * 5)));
        }
        this.waves.add(new Wave(true, this.difficulty));
    }

    private void killPlayer() {
        this.respawnTimer = new OneShotTimer(5f);
        this.player.killPlayer();
    }

    public void process(float delta, BitmapFont font, BitmapFont secondaryFont, BitmapFont thirdFont, Batch batch) {
        if (bossKilled) delta = .001f;

        this.removeEntities();
        this.processBulletEnemyCollisions();
        this.processPlayerCollisions();
        this.processWave();
        this.processRespawn();
        this.updateTimers(delta);
        if(Settings.DEBUG) {
            secondaryFont.draw(batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 0, 100);
            secondaryFont.draw(batch, "PARTICLE COUNT: "+this.particles.size(), 0, 140);
            secondaryFont.draw(batch, "ENEMY COUNT: "+this.waves.get(this.currentWave).getEnemies().size(), 0, 180);
        }

        if (Math.randomBetween(0, 5) == 0)
            this.particles.addAll(ParticleController.waveOfStars(Math.randomBetween(1, 10)));

        if (this.player.isDead()) {
            secondaryFont.draw(batch, "rip", (CameraController.camera.viewportWidth / 2) - 9, CameraController.camera.viewportHeight / 2);
        }

        for (Bullet bullet : this.bullets) {
            this.removeOffScreen(bullet);
            bullet.update(delta);
            bullet.render(secondaryFont, batch);
            bullet.move(delta);
        }

        for (Particle particle : this.particles) {
            this.removeOffScreen(particle);
            this.removeDeadParticle(particle);
            particle.update(delta);
            particle.render((particle.getType() == Particle.ParticleType.damage ? thirdFont : secondaryFont), batch);
            particle.move(delta);
        }

        for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
            enemy.update(delta);
            if (enemy instanceof Boss)
                enemy.render(thirdFont, batch);
            else
                enemy.render(font, batch);
            enemy.move(delta);
            if (enemy instanceof Boss) {
                if (MovementController.processBossMovement((Boss) enemy, this.player)) {
                    this.fireBossWeapon((Boss) enemy);
                }
            } else {
                if (MovementController.processEnemyMovement(enemy, this.player)) {
                    this.bullets.add(new Bullet(enemy.x(), enemy.y(), -Settings.BULLET_SPEED, 0, 'o', false, Bullet.BulletOwner.ENEMY));
                }
            }
        }

        for (Resource resource : this.resources) {
            resource.update(delta);
            resource.render(secondaryFont, batch);
            resource.move(delta);
            if (MovementController.processResourceMovement(this.player, resource))
                this.toRemove.add(resource);
        }
        this.player.update(delta);
        this.player.render(font, batch);
        this.player.move(delta);
        if (MovementController.processPlayerMovement(this.player))
            if (!Settings.DEBUG)
                this.firePlayerWeapon();
            else
                this.progressWave();
    }

    private void firePlayerWeapon() {
        switch (this.player.getGunLevel()) {
            case 0:
                if (this.player.getBulletCooldown() < Settings.BULLET_COOLDOWN) {
                    this.bullets.add(new Bullet(this.player.x(), this.player.y(), Settings.BULLET_SPEED, 0, 'o', false, Bullet.BulletOwner.PLAYER));
                    this.player.addBulletCooldown();
                }
                break;
            case 1:
                for (int i = 0; i < Settings.SPREAD_FIRE_COUNT; i++) {
                    if (this.player.getBulletCooldown() < Settings.BULLET_COOLDOWN) {
                        this.bullets.add(new Bullet(this.player.x(), this.player.y(), Math.floatRandomBetween(Settings.BULLET_SPEED * .8f, Settings.BULLET_SPEED), 0, 'o', true, Bullet.BulletOwner.PLAYER));
                        this.player.addBulletCooldown();
                    }
                }
                break;
            default:
                if (this.player.getBulletCooldown() < Settings.BULLET_COOLDOWN) {
                    this.bullets.add(new Bullet(this.player.x(), this.player.y(), Settings.BULLET_SPEED, 0, '.', false, Bullet.BulletOwner.PLAYER));
                    this.player.addBulletCooldown();
                }
        }
    }

    private void fireBossWeapon(Boss boss) {
        int random = Math.randomBetween(0, 20);
        int x = 0;
        int y = 0;
        while (boss.getColliders()[x][y] == null) {
            x = Math.randomBetween(0, boss.getColliders().length - 1);
            y = Math.randomBetween(0, boss.getColliders()[0].length - 1);
        }
        if (random <= this.difficulty / Settings.INITIAL_DIFFICULTY)
            this.bullets.add(new Bullet(boss.getColliders()[x][y].x, boss.getColliders()[x][y].y, Math.floatRandomBetween(-Settings.BULLET_SPEED, -Settings.BULLET_SPEED * .3f), 0, 'o', true, Bullet.BulletOwner.ENEMY));
        if (random == 3 && this.difficulty >= Settings.INITIAL_DIFFICULTY * 3)
            this.fireBossInCircle(boss, boss.getSmarts() / 500);
    }

    private void fireBossInCircle(Boss boss, float count) {
        for (int i = 0; i < count; i++) {
            float xVelo = Math.floatRandomBetween(-Settings.BULLET_SPEED, Settings.BULLET_SPEED);
            float yVelo = Math.floatRandomBetween(-Settings.BULLET_SPEED, Settings.BULLET_SPEED);
            int x = 0;
            int y = 0;
            while (boss.getColliders()[x][y] == null) {
                x = Math.randomBetween(0, boss.getColliders().length - 1);
                y = Math.randomBetween(0, boss.getColliders()[0].length - 1);
            }
            Bullet b = new Bullet(boss.getColliders()[x][y].x, boss.getColliders()[x][y].y, '*', Bullet.BulletOwner.ENEMY);
            b.setXVelocity(xVelo);
            b.setYVelocity(yVelo);
            this.bullets.add(b);
        }
    }

    private void removeOffScreen(Entity entity) {
        if (entity.x() > CameraController.camera.viewportWidth || entity.x() < 0 || entity.y() > CameraController.camera.viewportHeight + 100 || entity.y() < -100)
            this.toRemove.add(entity);
    }

    private void removeDeadParticle(Particle particle) {
        if (particle.getTimeToLive() <= 0)
            this.toRemove.add(particle);
    }

    private void processWave() {
        if (this.waves.get(this.currentWave).getEnemies().size() == 0)
            if (this.waveStartTimer == null) this.waveStartTimer = new OneShotTimer(this.bossKilled ? .5f : 2f);
        if (this.waveStartTimer != null)
            if (this.waveStartTimer.isFinished()) {
                this.progressWave();
            }
    }

    private void progressWave() {
        this.bossKilled = false;
        final int random = Math.randomBetween(0, 3);
        if (this.currentWave < 4) {
            this.currentWave++;
            this.waveStartTimer = null;
            if (random == 1)
                this.resources.add(ResourceSpawnController.randomResource());
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
                this.player.resetPlayer();
            }
        }
    }

    private void processBulletEnemyCollisions() {
        for (Bullet bullet : this.bullets) {
            if (bullet.getOwner() == Bullet.BulletOwner.PLAYER) {
                for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
                    if (enemy instanceof Boss) {
                        Boss boss = (Boss) enemy;
                        Vector2 collision = CollisionController.bossCollision(boss, bullet);
                        if (collision != null) {
                            this.toRemove.add(bullet);
                            boss.removeHealth();
                            this.particles.addAll(ParticleController.explosionParticles(boss.getColliders()[(int) collision.x][(int) collision.y].x, boss.getColliders()[(int) collision.x][(int) collision.y].y, Settings.EXPLOSION_SIZE));
                            if (boss.dead()) {
                                for (int i = 0; i < boss.getColliders().length; i++) {
                                    for (int j = 0; j < boss.getColliders()[i].length; j++) {
                                        if (boss.getColliders()[i][j] != null)
                                            this.particles.addAll(ParticleController.slowExplosionParticles(boss.getColliders()[i][j].x, boss.getColliders()[i][j].y, Settings.BOSS_EXPLOSION_SIZE));
                                    }
                                    this.bossKilled = true;
                                    this.toRemove.add(boss);
                                    this.player.addPoints(boss.getSmarts());
                                }
                            }
                            boss.removeCharAt((int) collision.x, (int) collision.y);
                        }
                    } else {
                        if (CollisionController.basicCollision(bullet, enemy)) {
                            this.toRemove.add(bullet);
                            this.toRemove.add(enemy);
                            this.particles.addAll(ParticleController.explosionParticles(enemy, Settings.EXPLOSION_SIZE));
                            this.player.addPoints(enemy.getPoints());
                        }
                    }
                }
            }
        }

    }

    private void processPlayerCollisions() {
        for (Bullet bullet : this.bullets) {
            if (bullet.getOwner() != Bullet.BulletOwner.PLAYER) {
                if (CollisionController.basicCollision(this.player, bullet)) {
                    this.toRemove.add(bullet);
                    if (!this.player.getShield()) {
                        this.particles.addAll(ParticleController.explosionParticles(this.player, Settings.EXPLOSION_SIZE));
                        this.killPlayer();
                    } else {
                        this.particles.addAll(ParticleController.explosionParticles(this.player, 1));
                    }
                }
            }
        }
        for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
            if (enemy instanceof Boss) {
                if (CollisionController.bossCollision((Boss) enemy, this.player) != null) {
                    if (!this.player.getShield()) {
                        this.particles.addAll(ParticleController.explosionParticles(this.player, Settings.EXPLOSION_SIZE));
                        this.killPlayer();
                    }
                }
            } else {
                if (CollisionController.basicCollision(this.player, enemy)) {
                    this.particles.addAll(ParticleController.explosionParticles(this.player, Settings.EXPLOSION_SIZE));
                    if (!this.player.getShield()) {
                        this.killPlayer();
                    } else
                        this.toRemove.add(enemy);
                }
            }
        }
        for (Resource resource : this.resources) {
            if (CollisionController.basicCollision(this.player, resource)) {
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