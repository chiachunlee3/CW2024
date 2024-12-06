package com.example.demo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.ImageView;

public class MovementManager {
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

    private final List<Integer> movePattern = new ArrayList<>();
    private int consecutiveMovesInSameDirection = 0;
    private int indexOfCurrentMove = 0;

    public MovementManager() {
        initializeMovePattern();
    }

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

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }
    public static void moveHorizontally(ImageView imageView, double horizontalMove) {
        imageView.setTranslateX(imageView.getTranslateX() + horizontalMove);
    }

    public static void moveVertically(ImageView imageView, double verticalMove) {
        imageView.setTranslateY(imageView.getTranslateY() + verticalMove);
    }
}
