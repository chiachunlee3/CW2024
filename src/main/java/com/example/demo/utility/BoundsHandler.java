package com.example.demo.utility;

import javafx.geometry.Bounds;
import javafx.geometry.BoundingBox;
import javafx.scene.image.ImageView;

/**
 * A utility class for handling and calculating precise bounds of JavaFX ImageView nodes.
 */
public class BoundsHandler {

    /**
     * Calculates and returns the precise bounds of an {@link ImageView}.
     * <p>
     * This method adjusts the bounds to reduce the height by a specific factor (80% in this case),
     * shifting it vertically for a refined fit.
     * </p>
     *
     * @param imageView the {@link ImageView} for which the bounds need to be calculated.
     * @return a {@link Bounds} object representing the adjusted bounding box.
     */
    public static Bounds getPreciseBounds(ImageView imageView) {
        Bounds bounds = imageView.getBoundsInParent();
        double verticalReduction = 0.8;
        double heightReduction = bounds.getHeight() * verticalReduction;
        return new BoundingBox(
            bounds.getMinX(),
            bounds.getMinY() + heightReduction / 2,
            bounds.getWidth(),
            bounds.getHeight() - heightReduction
        );
    }
}
