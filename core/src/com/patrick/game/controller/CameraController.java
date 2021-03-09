package com.patrick.game.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.patrick.game.util.Settings;

public class CameraController {

    public static OrthographicCamera camera = new OrthographicCamera();

    public static void resetCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
        camera.update();
    }

    public static void update() {
        camera.update();
    }
}
