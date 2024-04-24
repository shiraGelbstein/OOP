package image;

import java.awt.*;

/**
 * The ImageAsSubImages class represents an image divided into sub-images for further processing.
 */
public class ImageAsSubImages {

    private static final Color PADDING_COLOR = Color.WHITE;
    private final SubImage[][] subImages;
    private final Image image;
    private final Image imageWithWhiteEdges;
    private final int subImageDim;
    private int height;
    private int width;
    private final int resolution;

    /**
     * Constructs an ImageAsSubImages object with the given image and resolution.
     *
     * @param image      The original image to be processed.
     * @param resolution The resolution used for dividing the image into sub-images.
     */
    public ImageAsSubImages(Image image, int resolution) {
        this.image = image;
        this.height = xAsClosestPowerOf2(image.getHeight());
        this.width = xAsClosestPowerOf2(image.getWidth());
        this.resolution = resolution;
        this.subImageDim = width / resolution;
        this.imageWithWhiteEdges = getImageWithWhiteEdges();
        this.subImages = makeSubImages();
    }

    private SubImage[][] makeSubImages() {
        int subImageDim = width / resolution;
        int subImagesInCol = height / subImageDim;
        SubImage[][] subImages = new SubImage[subImagesInCol][resolution];
        for (int i = 0; i < subImagesInCol; i++) {
            for (int j = 0; j < resolution; j++) {
                int x = i * subImageDim;
                int y = j * subImageDim;
                subImages[i][j] = new SubImage(imageWithWhiteEdges, x, y, subImageDim);
            }
        }
        return subImages;
    }

    private Image getImageWithWhiteEdges() {
        Color[][] newPixelArray = new Color[height][width];
        int widthPadding = (width - image.getWidth()) / 2;
        int heightPadding = (height - image.getHeight()) / 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < heightPadding || i >= height - heightPadding || j < widthPadding ||
                        j >= width - widthPadding) {
                    newPixelArray[i][j] = PADDING_COLOR;
                } else {
                    newPixelArray[i][j] = image.getPixel(i - heightPadding, j - widthPadding);
                }
            }
        }
        return new Image(newPixelArray, width, height);
    }

    /**
     * Gets the height of the image.
     *
     * @return The height of the image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the image.
     *
     * @return The width of the image.
     */
    public int getWidth() {
        return width;
    }

    private static int xAsClosestPowerOf2(int x) {
        return (int) Math.pow(2, Math.ceil(Math.log(x) / Math.log(2)));
    }

    /**
     * Gets the dimension of each sub-image.
     *
     * @return The dimension of each sub-image.
     */
    public int getSubImageDim() {
        return subImageDim;
    }

    /**
     * Gets the array of sub-images.
     *
     * @return The array of sub-images.
     */
    public SubImage[][] getSubImages() {
        return subImages;
    }

}
