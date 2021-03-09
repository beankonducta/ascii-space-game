package com.patrick.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Entity;
import com.patrick.game.entity.Particle;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class ParticleController {

    private static char[] EXPLOSION_CHARS = new char[]{'*', '"', '.', ',', '#'};

    public static List<Particle> EXPLOSION_PARTICLES(Entity entity, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.FLOAT_RANDOM_BETWEEN(Settings.EXPLOSION_SPEED / 2, Settings.EXPLOSION_SPEED * 2);
            final int charIndex = Math.RANDOM_BETWEEN(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(entity.x(), entity.y(), speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> SLOW_EXPLOSION_PARTICLES(Entity entity, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.FLOAT_RANDOM_BETWEEN(Settings.EXPLOSION_SPEED / 6, Settings.EXPLOSION_SPEED);
            final int charIndex = Math.RANDOM_BETWEEN(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(entity.x(), entity.y(), speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> EXPLOSION_PARTICLES(float x, float y, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.FLOAT_RANDOM_BETWEEN(Settings.EXPLOSION_SPEED / 2, Settings.EXPLOSION_SPEED * 2);
            final int charIndex = Math.RANDOM_BETWEEN(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(x, y, speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }

    public static List<Particle> SLOW_EXPLOSION_PARTICLES(float x, float y, int count) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final float speed = Math.FLOAT_RANDOM_BETWEEN(Settings.EXPLOSION_SPEED / 6, Settings.EXPLOSION_SPEED);
            final int charIndex = Math.RANDOM_BETWEEN(0, EXPLOSION_CHARS.length - 1);
            Particle p = new Particle(x, y, speed, 0, EXPLOSION_CHARS[charIndex], Settings.EXPLOSION_TTL);
            p.randomDir();
            particles.add(p);
        }
        return particles;
    }
}
