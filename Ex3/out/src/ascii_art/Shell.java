package ascii_art;

import ascii_art.Exceptions.EmptyCharsetException;
import ascii_art.Exceptions.InvalidCommandException;
import ascii_art.Exceptions.InvalidResolutionException;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;
import image.ImageAsSubImages;
import image_char_matching.SubImgCharMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class represents a shell for interacting with ASCII art generation functionalities.
 */
public class Shell {
    // Messages

    private static final String BAD_RESOLUTION_FORMAT_MESSAGE = "Did not change resolution due to " +
            "incorrect format.";
    private static final String INVALID_REMOVE_CHAR_COMMAND_MESSAGE = "Did not remove due to incorrect " +
            "format.";
    private static final String INVALID_ADD_CHAR_COMMAND_MESSAGE = "Did not add due to incorrect format.";
    private static final String INVALID_COMMAND_MESSAGE = "Did not execute due to incorrect command.";
    private static final String INVALID_OUTPUT_COMMAND_MESSAGE = "Did not change output method due to " +
            "incorrect format.";
    private static final String INVALID_IMAGE_FILE_COMMAND_MESSAGE = "Did not execute due to problem with " +
            "image file.";
    private static final String RESOLUTION_SETTLED_MESSAGE = "Resolution set to ";


    // Constants
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String SPACE = "space";
    private static final String ALL = "all";
    private static final String DEFAULT_OUT_PATH = "out.html";
    private static final String CONSOLE = "console";
    private static final String HTML = "html";
    private static final String COMMAND_CHARS = "chars";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_REMOVE = "remove";
    private static final String COMMAND_IMAGE = "image";
    private static final String COMMAND_OUTPUT = "output";
    private static final String COMMAND_ASCII_ART = "asciiArt";
    private static final String COMMAND_RES = "res";
    private static final String COMMAND_EXIT = "exit";
    private static final String INPUT_SIGN = ">>> ";
    private static final String MULTIPLE_SPACES_REG = "\\s+";
    private static final int FIRST_ASCII_AS_NUM = 32;
    private static final int LAST_ASCII_AS_NUM = 127;

    // Fields
    private static final char[] DEFAULT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final String DEFAULTER_PICTURE_PASS = "cat.jpeg";
    private static final int DEFAULT_RESOLUTION = 128;
    private final SubImgCharMatcher subImgCharMatcher = new SubImgCharMatcher(DEFAULT_CHARS);
    private Image curImage = new Image(DEFAULTER_PICTURE_PASS);
    private AsciiOutput asciiOutput = new ConsoleAsciiOutput();
    private ImageAsSubImages imageAsSubImages;
    private boolean imageAsSubImagesShouldBeCalculated = true;
    private int curResolution = DEFAULT_RESOLUTION;
    private final String FONT = "Courier New";

    /**
     * Initializes a new Shell instance.
     *
     * @throws IOException if an I/O error occurs.
     */
    public Shell() throws IOException{
    }

    /**
     * Runs the shell
     */
    public void run() {
        System.out.print(INPUT_SIGN);
        String userInput = KeyboardInput.readLine();
        while (!userInput.equals(COMMAND_EXIT)) {
            try {
                handleInput(userInput);
            } catch (InvalidCommandException | EmptyCharsetException | InvalidResolutionException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(INVALID_IMAGE_FILE_COMMAND_MESSAGE);
            }
            System.out.print(INPUT_SIGN);
            userInput = KeyboardInput.readLine();
        }
    }

    // Handles the "add" command.
    private void handleAddCommand(String userInput) throws InvalidCommandException {
        String[] words = userInput.split(MULTIPLE_SPACES_REG);
        if (words.length == 2) {
            if (words[1].equals(ALL)) {
                for (int i = FIRST_ASCII_AS_NUM; i < LAST_ASCII_AS_NUM; i++) {
                    subImgCharMatcher.addChar((char) i);
                }
            } else if (words[1].length() == 1) {
                subImgCharMatcher.addChar(words[1].charAt(0));
            } else if (words[1].equals(SPACE)) {
                subImgCharMatcher.addChar(' ');
            } else if (words[1].length() == 3 && words[1].charAt(1) == '-') {
                char char1 = words[1].charAt(0), char2 = words[1].charAt(2);
                char biggerChar = (char1 > char2) ? char1 : char2;
                char smallerChar = (char1 < char2) ? char1 : char2;
                for (int i = smallerChar; i < ((int) biggerChar) + 1; i++) {
                    subImgCharMatcher.addChar((char) i);
                }
            }
        } else throw new InvalidCommandException(INVALID_ADD_CHAR_COMMAND_MESSAGE);
    }

    // Handles the "chars" command.
    private void handleCharsCommand() {
        HashSet<Character> arrayList = subImgCharMatcher.getCharSet();
        for (char c : arrayList) {
            System.out.print(c + " ");
        }
        System.out.print("\n");
    }

    // Handles the "remove" command.
    private void handleRemoveCommand(String userInput) throws InvalidCommandException {
        String[] words = userInput.split(MULTIPLE_SPACES_REG);
        if (words.length == 2) {
            if (words[1].equals(ALL)) {
                for (int i = FIRST_ASCII_AS_NUM; i < LAST_ASCII_AS_NUM; i++) {
                    subImgCharMatcher.removeChar((char) i);
                }
            } else if (words[1].length() == 1) {
                subImgCharMatcher.removeChar(words[1].charAt(0));
            } else if (words[1].equals(SPACE)) {
                subImgCharMatcher.removeChar(' ');
            } else if (words[1].length() == 3 && words[1].charAt(1) == '-') {
                char char1 = words[1].charAt(0), char2 = words[1].charAt(2);
                char biggerChar = (char1 > char2) ? char1 : char2;
                char smallerChar = (char1 < char2) ? char1 : char2;
                for (int i = smallerChar; i < biggerChar + 1; i++) {
                    subImgCharMatcher.removeChar((char) i);
                }
            }
        } else throw new InvalidCommandException(INVALID_REMOVE_CHAR_COMMAND_MESSAGE);
    }

    // Handles the "image" command.
    private void handleImageCommand(String userInput) throws IOException {
        String[] words = userInput.split(MULTIPLE_SPACES_REG);
        if (words.length == 2) {
            Image image;
            try {
                image = new Image(words[1]);
                imageAsSubImagesShouldBeCalculated = true;
            } catch (IOException e) {
                throw e;
            }
            this.curImage = image;
        }
    }

    // Handles the "output" command.
    private void handleOutputCommand(String userInput) throws InvalidCommandException {
        String[] words = userInput.split(MULTIPLE_SPACES_REG);
        if (words.length == 2) {
            if (words[1].equals(CONSOLE)) {
                asciiOutput = new ConsoleAsciiOutput();
            } else if (words[1].equals(HTML)) {
                asciiOutput = new HtmlAsciiOutput(DEFAULT_OUT_PATH, FONT);
            }
        } else throw new InvalidCommandException(INVALID_OUTPUT_COMMAND_MESSAGE);
    }

    //  Handles the "asciiArt" command.
    private void handleAsciiArtCommand() throws EmptyCharsetException {
        if (imageAsSubImagesShouldBeCalculated) {
            imageAsSubImages = new ImageAsSubImages(curImage, curResolution);
        }
        if(subImgCharMatcher.getCharSet().isEmpty()){
            throw new EmptyCharsetException();}
        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(imageAsSubImages, subImgCharMatcher);
        char[][] image = asciiArtAlgorithm.run();
        asciiOutput.out(image);
    }

    // Handles the "res" command.
    private void handleResCommand(String userInput) throws InvalidResolutionException {
        String[] split = userInput.split(" ");
        int minCharsInRow = Math.max(1, curImage.getWidth() / curImage.getHeight());
        int maxCharsInRow = Math.min(curImage.getWidth(), curImage.getHeight());
        if (split.length != 2 || !(split[1].equals(UP) || split[1].equals(DOWN))) {
            System.out.println(BAD_RESOLUTION_FORMAT_MESSAGE);
        } else if (split[1].equals(UP) && (curResolution * 2) <= maxCharsInRow) {
            curResolution *= 2;
            System.out.println(RESOLUTION_SETTLED_MESSAGE + curResolution + ".");
            imageAsSubImagesShouldBeCalculated = true;
        } else if (split[1].equals(DOWN) && (curResolution / 2) >= minCharsInRow) {
            curResolution /= 2;
            System.out.println(RESOLUTION_SETTLED_MESSAGE + curResolution + ".");
            imageAsSubImagesShouldBeCalculated = true;
        } else {
            throw new InvalidResolutionException();
        }
    }

    // Handles the user input.
    private void handleInput(String userInput) throws
            IOException, InvalidCommandException, InvalidResolutionException,EmptyCharsetException {
        String[] inputParts = userInput.trim().split(MULTIPLE_SPACES_REG);
        String command = inputParts[0]; // Extract the command part

        switch (command) {
            case COMMAND_CHARS:
                handleCharsCommand();
                break;
            case COMMAND_ADD:
                handleAddCommand(userInput);
                break;
            case COMMAND_REMOVE:
                handleRemoveCommand(userInput);
                break;
            case COMMAND_IMAGE:
                handleImageCommand(userInput);
                break;
            case COMMAND_OUTPUT:
                handleOutputCommand(userInput);
                break;
            case COMMAND_ASCII_ART:
                handleAsciiArtCommand();
                break;
            case COMMAND_RES:
                handleResCommand(userInput);
                break;
            default:
                throw new InvalidCommandException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * The entry point of the program - creates Shells object and run it.
     *
     * @param args the command-line arguments.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        Shell shell = new Shell();
        shell.run();
    }
}
