package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageHandler {
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    public static void setImageProperties(ImageView imageView, String imageName, int imageHeight) {
        imageView.setImage(new Image(ImageHandler.class.getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        imageView.setFitHeight(imageHeight);
        imageView.setPreserveRatio(true);
    }
}
