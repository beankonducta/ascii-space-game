package com.patrick.game.util;

public class Timer {

    protected float time;
    protected float max;
    protected boolean started;

    public float getTime() {
        return this.time;
    }

    public boolean isStarted() {
        return this.started;
    }

    public Timer(float max) {
        this.time = 0f;
        this.max = max;
    }

    public Timer() {
        this.time = 0f;
        this.max = -1;
    }

    public void update(float delta) {
        if (this.started)
            this.time += delta;
        if (this.max == -1) return;
        if (this.time >= max) this.time -= max;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }

    public void reset() {
        this.stop();
        this.time = 0f;
    }
}
