package com.patrick.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.patrick.game.util.Settings;

public class CameraController {

    public static OrthographicCamera camera = new OrthographicCamera();

    public static void resetCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera.setToOrtho(false, 900, 1600);
        camera.update();
    }

    public static void update() {
        camera.update();
    }
}
