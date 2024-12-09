package com.example.demo;

import java.util.List;
import javafx.scene.Group;

public class EnemyManager {
    private final List<ActiveActorDestructible> enemyUnits;
    private final double screenWidth;
    private final Group root;
    
    public EnemyManager(List<ActiveActorDestructible> enemyUnits, double screenHeightAdjustment, double screenWidth, Group root) {
        this.enemyUnits = enemyUnits;
        this.screenWidth = screenWidth;
        this.root = root;
    }
    
    public void spawnEnemy(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    public boolean hasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    public int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    public void removeDestroyedEnemies() {
        enemyUnits.removeIf(enemy -> {
            if (enemy.isDestroyed()) {
                root.getChildren().remove(enemy); // Remove from scene graph
                return true;
            }
            return false;
        });
    }
}
