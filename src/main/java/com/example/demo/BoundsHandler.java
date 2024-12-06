package com.example.demo;

import javafx.geometry.Bounds;
import javafx.geometry.BoundingBox;
import javafx.scene.image.ImageView;

public class BoundsHandler {

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
