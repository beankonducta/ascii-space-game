package com.patrick.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Resource;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

public class ResourceSpawnController {

    public static Resource RANDOM_RESOURCE() {
        final int random = Math.EITHER_OR(0, 1);
        final Resource.ResourceType type = random == 0 ? Resource.ResourceType.GUN : Resource.ResourceType.LIFE;
        final int level = 1;
        return new Resource(new Vector2(CameraController.camera.viewportWidth / 2, CameraController.camera.viewportHeight + 20), Settings.RESOURCE_SPEED, 0, type, level);
    }
}