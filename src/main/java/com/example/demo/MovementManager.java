package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 * The {@code MovementManager} class manages movement patterns for game objects.
 * It generates and applies predefined movement patterns, including horizontal and vertical
 * movements, and ensures variability by shuffling the patterns after a certain number of
 * repetitions.
 */
public class MovementManager {

    private static final int MOVE_FREQUENCY_PER_CYCLE = 5; // Frequency of each type of movement in a cycle
    private static final int VERTICAL_VELOCITY = 8; // Vertical movement speed
    private static final int ZERO = 0; // No movement
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10; // Maximum frames with the same movement

    private final List<Integer> movePattern = new ArrayList<>(); // List of movement patterns
    private int consecutiveMovesInSameDirection = 0; // Count of consecutive moves in the same direction
    private int indexOfCurrentMove = 0; // Index of the current move in the pattern

    /**
     * Constructs a {@code MovementManager} and initializes the movement pattern.
     */
    public MovementManager() {
        initializeMovePattern();
    }

    /**
     * Retrieves the next movement value from the current movement pattern.
     * The pattern is shuffled after a certain number of consecutive movements
     * in the same direction.
     *
     * @return the next movement value from the pattern.
     */
    public int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;

        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }

        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }

        return currentMove;
    }

    /**
     * Initializes the movement pattern with a mix of upward, downward, and no movement values.
     * The pattern is shuffled to add variability.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY); // Upward movement
            movePattern.add(-VERTICAL_VELOCITY); // Downward movement
            movePattern.add(ZERO); // No movement
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Moves an {@code ImageView} object horizontally by a specified amount.
     *
     * @param imageView     the {@code ImageView} to move.
     * @param horizontalMove the amount to move horizontally.
     */
    public static void moveHorizontally(ImageView imageView, double horizontalMove) {
        imageView.setTranslateX(imageView.getTranslateX() + horizontalMove);
    }

    /**
     * Moves an {@code ImageView} object vertically by a specified amount.
     *
     * @param imageView    the {@code ImageView} to move.
     * @param verticalMove the amount to move vertically.
     */
    public static void moveVertically(ImageView imageView, double verticalMove) {
        imageView.setTranslateY(imageView.getTranslateY() + verticalMove);
    }
}