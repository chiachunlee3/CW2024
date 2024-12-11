package com.example.demo.utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Provides utility methods for handling images in the application.
 * This class simplifies the process of setting image properties for 
 * {@code ImageView} objects.
 */
public class ImageHandler {

    /**
     * The base location of image resources in the application.
     */
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    /**
     * Sets the properties of an {@code ImageView} with the specified image and dimensions.
     *
     * <p>This method loads the image specified by the {@code imageName} parameter from
     * the application's resource folder. It also sets the height of the {@code ImageView}
     * to the value provided in {@code imageHeight} while maintaining the aspect ratio.</p>
     *
     * @param imageView   the {@code ImageView} object whose properties are to be set.
     * @param imageName   the name of the image file to load.
     * @param imageHeight the height to set for the {@code ImageView}.
     *                    The width is automatically adjusted to preserve the image's aspect ratio.
     */
    public static void setImageProperties(ImageView imageView, String imageName, int imageHeight) {
        imageView.setImage(new Image(ImageHandler.class.getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        imageView.setFitHeight(imageHeight);
        imageView.setPreserveRatio(true);
    }
}