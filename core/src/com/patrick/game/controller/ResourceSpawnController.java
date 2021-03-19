package com.patrick.game.controller;

import com.patrick.game.entity.Resource;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

public class ResourceSpawnController {

    public static Resource randomResource() {
        final int random = Math.randomBetween(0, 2);
        Resource.ResourceType type = Resource.ResourceType.GUN;
        switch (random) {
            case 1:
                type = Resource.ResourceType.LIFE;
                break;
            case 2:
                type = Resource.ResourceType.SHIELD;
                break;
            default:
                break;
        }
        final int level = 1;
        return new Resource(CameraController.camera.viewportWidth / 2, CameraController.camera.viewportHeight + 20, Settings.RESOURCE_SPEED, 0, type, level);
    }
}