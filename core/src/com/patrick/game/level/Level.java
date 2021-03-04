package com.patrick.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.CollisionController;
import com.patrick.game.controller.MovementController;
import com.patrick.game.entity.*;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Wave> waves;
    private Player player;
    private List<Bullet> bullets;
    private List<Particle> particles;

    private List<Entity> toRemove;

    private int currentWave;
    private int difficulty;

    private boolean finished;

    public boolean isFinished() {
        return this.finished;
    }

    public Level(int difficulty) {
        this.difficulty = difficulty;
        this.waves = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.player = new Player(new Vector2(200, 40), 6f, .25f, 'P');
        this.currentWave = 0;
        this.finished = false;
        this.fillWaves();
    }

    private void fillWaves() {
        for (int i = 0; i < Settings.WAVE_COUNT; i++) {
            this.waves.add(new Wave(this.difficulty + (i * 3)));
        }
    }

    public void process(float delta, BitmapFont font, Batch batch) {
        this.removeEntities();
        this.processBulletEnemyCollisions();
        this.progressWave();

        for (Bullet bullet : this.bullets) {
            this.removeOffScreen(bullet);
            bullet.update(delta);
            bullet.render(font, batch);
            bullet.move(CameraController.camera.viewportWidth);
        }

        for (Particle particle : this.particles) {
            this.removeOffScreen(particle);
            this.removeDeadParticle(particle);
            particle.update(delta);
            particle.render(font, batch);
            particle.move(CameraController.camera.viewportWidth);
        }

        for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
            enemy.update(delta);
            enemy.render(font, batch);
            enemy.move(CameraController.camera.viewportWidth);
            if(MovementController.processEnemyMovement(enemy, this.player, CameraController.camera.viewportWidth)) {
                // no work
                this.bullets.add(new Bullet(new Vector2(200, 500), -20f, 0, 'o'));
            }
        }

        this.player.update(delta);
        this.player.render(font, batch);
        player.move(CameraController.camera.viewportWidth);
        if (MovementController.processPlayerMovement(this.player))
            this.bullets.add(new Bullet(new Vector2(this.player.x(), this.player.y()), 20f, 0, 'o'));
//            uncomment to test wave progression
//            this.toRemove.addAll(this.waves.get(this.currentWave).getEnemies());
    }

    private void removeOffScreen(Entity entity) {
        if (entity.x() > CameraController.camera.viewportWidth || entity.x() < 0 || entity.y() > CameraController.camera.viewportHeight || entity.y() < 0)
            this.toRemove.add(entity);
    }

    private void removeDeadParticle(Particle particle) {
        if (particle.getTimeToLive() <= 0)
            this.toRemove.add(particle);
    }

    private void progressWave() {
        if (this.waves.get(this.currentWave).getEnemies().size() == 0)
            if(this.currentWave < 4)
                this.currentWave++;
            else {
                this.finished = true;
                System.out.println("DONE");
            }
    }

    private void processBulletEnemyCollisions() {
        for (Bullet bullet : this.bullets) {
            for (Enemy enemy : this.waves.get(this.currentWave).getEnemies()) {
                if (CollisionController.BASIC_COLLISION(bullet, enemy)) {
                    this.toRemove.add(enemy);
                }
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
        }
    }
}