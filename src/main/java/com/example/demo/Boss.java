package com.example.demo;

public class Boss extends FighterPlane {
    private static final String IMAGE_NAME = "bossplane.png";
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400.0;
    private static final int IMAGE_HEIGHT = 300;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_HEALTH = 100;

    private final MovementManager movementManager;
    private final ShieldManager shieldManager;
    private final ProjectileManager projectileManager;
    private int currentHealth;

    public Boss(LevelViewLevelTwo levelView, double shieldProbability) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, MAX_HEALTH);
        this.currentHealth = MAX_HEALTH;
        this.movementManager = new MovementManager();
        this.shieldManager = new ShieldManager(levelView, shieldProbability);
        this.projectileManager = new ProjectileManager();
    }

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(movementManager.getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
        shieldManager.updateShield(getLayoutX() + getTranslateX(), getLayoutY() + getTranslateY());
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return projectileManager.fireProjectile(getLayoutY(), getTranslateY());
    }

    @Override
    public void takeDamage() {
        if (!shieldManager.isShielded()) {
            currentHealth--;
            super.takeDamage();
        }
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }
}

