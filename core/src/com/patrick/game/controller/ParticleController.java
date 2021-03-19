package com.patrick.game.controller;

import com.patrick.game.entity.Entity;
import com.patrick.game.entity.Particle;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class ParticleController {

    private static final char[] EXPLOSION_CHARS = new char[]{'*', '"', '.', ',', '#'};
    private static final char[] ABSORBTION_CHARS = new char[]{'[', ']'};

    public static List<Particle> explosionParticles(float x, float y, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.floatRandomBetween(Settings.EXPLOSION_SPEED / 2, Settings.EXPLOSION_SPEED * 2);
            final int charIndex = Math.randomBetween(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(x, y, speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL, Particle.ParticleType.damage);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> slowExplosionParticles(float x, float y, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.floatRandomBetween(Settings.EXPLOSION_SPEED / 6, Settings.EXPLOSION_SPEED);
            final int charIndex = Math.randomBetween(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(x, y, speed, 0, EXPLOSION_CHARS[charIndex], Math.randomBetween(Settings.EXPLOSION_TTL, Settings.EXPLOSION_TTL * 5), Particle.ParticleType.damage);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> absorbtionParticles(float x, float y, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Particle p = new Particle(x, y, 40 + (10*i), 0, ABSORBTION_CHARS[0], Settings.EXPLOSION_TTL, Particle.ParticleType.powerUp);
            p.setXVelocity(-p.getSpeed());
            p.setYVelocity(0);
            Particle p2 = new Particle(x, y, 40 + (10*i), 0, ABSORBTION_CHARS[1], Settings.EXPLOSION_TTL, Particle.ParticleType.powerUp);
            p2.setXVelocity(p.getSpeed());
            p2.setYVelocity(0);
            particles.add(p);
            particles.add(p2);
        }
        return particles;
    }

    public static List<Particle> waveOfStars(int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Particle p = new Particle(Math.randomBetween(0, (int)CameraController.camera.viewportWidth), CameraController.camera.viewportHeight + Math.randomBetween(0, 30),  -100, 0, '.', 1000000, Particle.ParticleType.star);
            p.setYVelocity(p.getSpeed());
            particles.add(p);
        }
        return particles;
    }
}