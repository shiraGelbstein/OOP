package ascii_art;
import image.ImageAsSubImages;
import image.SubImage;
import image_char_matching.SubImgCharMatcher;

/**
 * This class represents an algorithm to convert an image to ASCII art image using sub-images and character
 * matching.
 */
public class AsciiArtAlgorithm {
    private final SubImgCharMatcher subImgCharMatcher;
    private final ImageAsSubImages imageAsSubImages;
    private final SubImage[][] subImages;

    /**
     * Constructs an AsciiArtAlgorithm with the given ImageAsSubImages and SubImgCharMatcher.
     *
     * @param imageAsSubImages   The ImageAsSubImages containing the original image and its sub-images.
     * @param subImgCharMatcher  The SubImgCharMatcher for matching sub-image brightness to characters.
     */
    public AsciiArtAlgorithm(ImageAsSubImages imageAsSubImages, SubImgCharMatcher subImgCharMatcher) {
        this.imageAsSubImages = imageAsSubImages;
        this.subImages = this.imageAsSubImages.getSubImages();
        this.subImgCharMatcher = subImgCharMatcher;
    }

    /**
     * Runs the ASCII art conversion algorithm on the provided image.
     *
     * @return A 2D char array representing the ASCII art picture.
     */
    public char[][] run() {
        int subImageDim = imageAsSubImages.getSubImageDim();
        char[][] imageAsChars =
                new char[imageAsSubImages.getHeight()/ subImageDim][imageAsSubImages.getWidth()/ subImageDim];

        for (int i = 0; i < imageAsSubImages.getHeight() / subImageDim; i++) { // iterating the rows
            for (int j = 0; j < imageAsSubImages.getWidth() / subImageDim; j++) { // iterating the cols
                Double subImageBrightness = subImages[i][j].getBrightness();
                imageAsChars[i][j] = subImgCharMatcher.getCharByImageBrightness(subImageBrightness);
            }
        }

        return imageAsChars;
    }
}