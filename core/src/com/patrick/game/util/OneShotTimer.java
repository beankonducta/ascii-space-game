package com.patrick.game.util;

public class OneShotTimer extends Timer {

    private boolean finished;

    public boolean isFinished() {
        return this.finished;
    }

    /**
     * A one and done timer. Automatically sets to finished once the timer reaches the (max) time.
     *
     * @param max
     */
    public OneShotTimer(float max) {
        super(max);
        this.finished = false;
        this.start();
    }

    public void update(float delta) {
        if (this.started)
            this.time += delta;
        if (this.time >= this.max) this.finished = true;
    }
}
