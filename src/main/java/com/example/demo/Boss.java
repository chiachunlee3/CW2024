package com.example.demo;

/**
 * The {@code Boss} class represents a specialized enemy entity in a game.
 * It extends the {@link FighterPlane} class and introduces advanced behaviors
 * such as movement management, shield activation, and projectile firing.
 * 
 * <p>The {@code Boss} class manages its own position, shields, and health.
 * It restricts vertical movement within specified bounds and interacts
 * with projectiles to damage players or other actors.</p>
 * 
 * <p>Key features include:</p>
 * <ul>
 *   <li>Vertical movement with boundary enforcement.</li>
 *   <li>Shield management to block damage.</li>
 *   <li>Projectile firing capabilities.</li>
 *   <li>Health tracking with maximum and current health levels.</li>
 * </ul>
 */
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

    /**
     * Constructs a new {@code Boss} with specified level view and shield probability.
     *
     * @param levelView The level view object providing context for this boss entity.
     * @param shieldProbability The probability of activating the shield during gameplay.
     */
    public Boss(LevelViewLevelTwo levelView, double shieldProbability) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, MAX_HEALTH);
        this.currentHealth = MAX_HEALTH;
        this.movementManager = new MovementManager();
        this.shieldManager = new ShieldManager(levelView, shieldProbability);
        this.projectileManager = new ProjectileManager();
    }

    /**
     * Updates the vertical position of the boss based on its movement manager.
     * Ensures the position stays within the predefined bounds.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(movementManager.getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Updates the state of the boss entity, including position and shield management.
     */
    @Override
    public void updateActor() {
        updatePosition();
        shieldManager.updateShield(getLayoutX() + getTranslateX(), getLayoutY() + getTranslateY());
    }

    /**
     * Fires a projectile from the boss's current position.
     *
     * @return A new projectile instance targeting players or other entities.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return projectileManager.fireProjectile(getLayoutY(), getTranslateY());
    }

    /**
     * Reduces the boss's health when it takes damage, unless shielded.
     * Calls the superclass's takeDamage method if damage is applied.
     */
    @Override
    public void takeDamage() {
        if (!shieldManager.isShielded()) {
            currentHealth--;
            super.takeDamage();
        }
    }

    /**
     * Gets the current health of the boss.
     *
     * @return The current health value.
     */
    public int getHealth() {
        return currentHealth;
    }

    /**
     * Gets the maximum health of the boss.
     *
     * @return The maximum health value.
     */
    public int getMaxHealth() {
        return MAX_HEALTH;
    }
}
