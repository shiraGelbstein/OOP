package image_char_matching;

import java.util.*;

/**
 * The SubImgCharMatcher class is responsible for matching characters to brightness values
 * for use in converting images to ASCII art.
 */
public class SubImgCharMatcher {

    private final HashSet<Character> charSet;
    private double maxBrightness = Double.MIN_VALUE;
    private double minBrightness = Double.MAX_VALUE;
    private TreeMap<Double, Character> charMatcherMap;

    /**
     * Constructs a SubImgCharMatcher with the provided character set.
     * run time: O(nlogn) for each char = O(n) adding to the treeMap = O(logn)
     *
     * @param charSet The character set used for matching brightness values.
     */
    public SubImgCharMatcher(char[] charSet) {
        this.charSet = new HashSet<>();
        for (char c : charSet) {
            this.charSet.add(c);
        }
        // Create hash map from charSet
        createMap();
        // Linear stretching
        linerNormalisation();
    }

    /**
     * Creates the tree map with the key as a character from charSet
     * and the value as the brightness for that character.
     */
    private void createMap() {
        TreeMap<Double, Character> charMatcherMapTemp = new TreeMap<>();
        for (char c : charSet) {
            double brightness = getBrightnessForChar(c);
            if (charMatcherMapTemp.containsKey(brightness) &&
                    charMatcherMapTemp.get(brightness) < (Character) c) {
                continue;
            }
            charMatcherMapTemp.put(brightness, c);
            updateMinMaxBrightness(c, brightness);
        }
        this.charMatcherMap = charMatcherMapTemp;
    }

    private double getBrightnessForChar(char c) {
        boolean[][] mat = CharConverter.convertToBoolArray(c);
        double counter = 0;
        for (int i = 0; i < CharConverter.DEFAULT_PIXEL_RESOLUTION; i++) {
            for (int j = 0; j < CharConverter.DEFAULT_PIXEL_RESOLUTION; j++) {
                if (mat[i][j]) {
                    counter++;
                }
            }
        }
        return counter / (CharConverter.DEFAULT_PIXEL_RESOLUTION * CharConverter.DEFAULT_PIXEL_RESOLUTION);
    }

    private void linerNormalisation() {
        TreeMap<Double, Character> charMatcherMapTemp = new TreeMap<>();
        for (Map.Entry<Double, Character> entry : charMatcherMap.entrySet()) {
            double newVal = normaliseVal(entry.getKey());
            charMatcherMapTemp.put(newVal, entry.getValue());
        }
        charMatcherMap = charMatcherMapTemp;
    }

    private double normaliseVal(double brightness) {
        return (brightness - minBrightness) / (maxBrightness - minBrightness);
    }

    /**
     * Updates the min and max brightness values and characters.
     *
     * @param c           The character.
     * @param brightness  The brightness value for the character.
     * @return            True if min or max brightness is updated, false otherwise.
     */
    private boolean updateMinMaxBrightness(char c, double brightness) {
        boolean changed = false;
        if (brightness > maxBrightness) {
            maxBrightness = brightness;
            changed = true;
        }
        if (brightness < minBrightness) {
            minBrightness = brightness;
            changed = true;
        }
        return changed;
    }

    /**
     * Returns the character closest to the given brightness value.
     * run time: O(logn) because it searches on a tree map.
     *
     * @param brightness  The brightness value.
     * @return            The closest character.
     */
    public char getCharByImageBrightness(double brightness) {
        // Greatest key less than or equal to the target key
        Double floorKey = charMatcherMap.floorKey(brightness);
        // Smallest key greater than or equal to the target key
        Double ceilingKey = charMatcherMap.ceilingKey(brightness);

        // Assume floorKey and ceilingKey are 0 if they are null
        double assumedFloorKey = (floorKey != null) ? floorKey : 0.0;
        double assumedCeilingKey = (ceilingKey != null) ? ceilingKey : 0.0;

        double dist1 = Math.abs(brightness - assumedFloorKey);
        double dist2 = Math.abs(assumedCeilingKey - brightness);
        double closestKey = (dist1 < dist2) ? assumedFloorKey : assumedCeilingKey;

        return charMatcherMap.get(closestKey);
    }

    /**
     * Adds a character to the character set and updates the tree map if
     * c was a minimum or maximum. run time: O(nlogn) in the worst case and O(1) that depends on the
     * ArrayList length.
     * @param c The character to be added.
     */
    public void addChar(char c) {
        // Calculate brightness for the new char
        double brightness = getBrightnessForChar(c);
        double normBrightness = normaliseVal(brightness);
        this.charSet.add(c);

        // Check if char's brightness already in the tree, if not adding the char to the tree
        if (!charMatcherMap.containsKey(normBrightness) || charMatcherMap.get(brightness) != null
                && c < charMatcherMap.get(brightness)) {
            // Adding the char to the char's list

            // Updating all the other chars if the max or min have changed
            if (updateMinMaxBrightness(c, brightness)) {
                createMap();
                linerNormalisation();
                return;
            }

            // Adding the char to the tree
            charMatcherMap.put(normBrightness, c);
        }
    }

    /**
     * Removes a character from the character set and updates the tree map if
     * c was a minimum or maximum = O(nlogn) in the worst case and O(1) that depends on the
     * ArrayList behavior.
     *
     * @param c The character to be removed.
     */
    public void removeChar(char c) { // Assuming there is c in the charSet, otherwise bugs!
        // Calculate brightness for the char
        double brightness = getBrightnessForChar(c);
        double normBrightness = normaliseVal(brightness);
        this.charSet.remove((Character) c);
        // Check if char's brightness already in the tree, if not adding the char to the tree
        if (charMatcherMap.containsKey(normBrightness)) {
            // Removing the char from the charSet and from the map
            charMatcherMap.remove(normBrightness);

            // Updating all the other chars if the max or min have changed
            if (updateMinMaxBrightness(c, brightness)) {
                createMap();
                linerNormalisation();
            }
        }
    }

    /**
     * Gets the character set.
     *
     * @return The character set.
     */
    public HashSet<Character> getCharSet() {
        return charSet;
    }

}
