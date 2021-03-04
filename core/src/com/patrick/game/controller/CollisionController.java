package com.patrick.game.controller;

import com.patrick.game.entity.Entity;

public class CollisionController {

    public static boolean BASIC_COLLISION(Entity entity1, Entity entity2) {
        if(entity1.getCollider() == null || entity2.getCollider() == null) return false;
        return entity1.getCollider().overlaps(entity2.getCollider());
    }
}