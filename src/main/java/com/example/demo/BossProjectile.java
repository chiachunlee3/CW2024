package com.example.demo;

/**
 * Represents a projectile used by a boss in the game. 
 * This projectile is represented by a fireball and has specific properties 
 * such as size, initial position, and velocity.
 */
public class BossProjectile extends Projectile {
	
	/**
	 * The name of the image file used to represent the projectile.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the projectile's image in pixels.
	 */
	private static final int IMAGE_HEIGHT = 75;

	/**
	 * The horizontal velocity of the projectile in pixels per update.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial X-coordinate position of the projectile.
	 */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a new BossProjectile with a specified initial Y-coordinate.
	 * 
	 * @param initialYPos the initial Y-coordinate of the projectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally 
	 * based on its horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	/**
	 * Updates the state of the projectile. 
	 * This method currently updates its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
