package com.patrick.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Boss;
import com.patrick.game.entity.Entity;

public class CollisionController {

    public static boolean basicCollision(Entity entity1, Entity entity2) {
        if (entity1.getCollider() == null || entity2.getCollider() == null) return false;
        return entity1.getCollider().overlaps(entity2.getCollider());
    }

    public static Vector2 bossCollision(Boss boss, Entity entity) {
        for (int i = 0; i < boss.getColliders().length; i++) {
            for (int j = 0; j < boss.getColliders()[i].length; j++) {
                if (boss.getColliders()[i][j] != null)
                    if (entity.getCollider().overlaps(boss.getColliders()[i][j])) return new Vector2(i, j);
            }
        }
        return null;
    }
}