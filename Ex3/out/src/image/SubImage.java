package image;

import java.awt.*;

/**
 * A class that represents a subImage of a current image.
 */
public class SubImage {

    private final Image image;
    private final int dim;
    private final int firstColPos;
    private final int firstRowPos;
    private final Double brightness;

    /**
     * Constructs a SubImage with the provided image, starting col, starting row, and dimension.
     *
     * @param image         The image to create the subImage from.
     * @param x             The starting col of the subImage.
     * @param y             The starting row of the subImage.
     * @param dim           The height and width of the subImage.
     */
    public SubImage(Image image, int x, int y, int dim) {
        this.image = image;
        this.firstColPos = x;
        this.firstRowPos = y;
        this.dim = dim;
        this.brightness = calculateBrightness();
    }

    /**
     * Gets the color at the specified row and col in the subImage.
     *
     * @param x The col of the wanted pixel.
     * @param y The row of the wanted pixel.
     * @return The color in the wanted pixel.
     */
    private Color getPixel(int x, int y) {
        return image.getPixel(firstColPos + x, firstRowPos + y);
    }

    /**
     * Calculates the brightness of the subImage.
     *
     * @return The brightness value.
     */
    private Double calculateBrightness() {
        double sumGrays = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Color pixelColor = getPixel(i, j);
                sumGrays += pixelColor.getRed() * 0.2126 + pixelColor.getGreen() * 0.7152
                        + pixelColor.getBlue() * 0.0722;
            }
        }
        return (sumGrays / (dim * dim)) / 255;
    }

    /**
     * Gets the brightness of the subImage.
     *
     * @return The brightness value.
     */
    public Double getBrightness() {
        return brightness;
    }
}
