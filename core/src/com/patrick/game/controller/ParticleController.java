package com.patrick.game.controller;

import com.patrick.game.entity.Entity;
import com.patrick.game.entity.Particle;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class ParticleController {

    private static final char[] EXPLOSION_CHARS = new char[]{'*', '"', '.', ',', '#'};

    public static List<Particle> explosionParticles(Entity entity, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.floatRandomBetween(Settings.EXPLOSION_SPEED / 2, Settings.EXPLOSION_SPEED * 2);
            final int charIndex = Math.randomBetween(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(entity.x(), entity.y(), speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> slowExplosionParticles(Entity entity, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.floatRandomBetween(Settings.EXPLOSION_SPEED / 6, Settings.EXPLOSION_SPEED);
            final int charIndex = Math.randomBetween(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(entity.x(), entity.y(), speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> explosionParticles(float x, float y, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.floatRandomBetween(Settings.EXPLOSION_SPEED / 2, Settings.EXPLOSION_SPEED * 2);
            final int charIndex = Math.randomBetween(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(x, y, speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
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
            Particle p = new Particle(x, y, speed, 0, EXPLOSION_CHARS[charIndex], Math.randomBetween(Settings.EXPLOSION_TTL, Settings.EXPLOSION_TTL * 5));
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }
}
