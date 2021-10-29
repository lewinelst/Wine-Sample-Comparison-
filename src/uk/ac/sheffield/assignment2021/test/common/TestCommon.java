package uk.ac.sheffield.assignment2021.test.common;

import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WinePropertyMap;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TestCommon
{

    // File input, you should _not_ modify these files or create new ones
    public static final String RED_FILE = "resources/winequality-red.csv";
    public static final String WHITE_FILE = "resources/winequality-white.csv";

    public static final List<WineSample> DUMMY_RED_WINES = Arrays.asList(
            new WineSample(0, WineType.RED, new WinePropertyMapBuilder()
                .with(WineProperty.FixedAcidity, 7.4)
                .build()),
            new WineSample(1, WineType.RED, new WinePropertyMapBuilder()
                .with(WineProperty.FixedAcidity, 7.8)
            .build())
    );

    /**
     * Helper method to tokenize a String, by splitting a String by the presence of
     * whitespace
     *
     * @param toTokenize the String to tokenize.
     * @return A list of token Strings
     */
    public static List<String> tokenizeString(String toTokenize) {
        List<String> tokens;
        toTokenize = toTokenize.replaceAll(Pattern.compile("\\s+").pattern(), " ");
        tokens = Arrays.asList(toTokenize.split(Pattern.compile(" ").pattern()));
        return tokens;
    }

    /**
     * Helper method to split a multi-line String into a List of Strings by the
     * newline character (\n).
     *
     * @param toSplit the String to split into a series of lines.
     * @return A list of individual lines.
     */
    @SuppressWarnings("unused")
    public static List<String> getLines(String toSplit) {
        List<String> lines;
        lines = Arrays.asList(toSplit.split(Pattern.compile("\n").pattern()));
        return lines;
    }


    /**
     * Class to manually create a WinePropertyMap
     * Not necessarily recommended for normal use, but it can be useful for tests
     */
    private static class WinePropertyMapBuilder {

        final WinePropertyMap map = new WinePropertyMap();

        WinePropertyMapBuilder with(@SuppressWarnings("SameParameterValue") WineProperty wineProperty, double value) {
            this.map.put(wineProperty, value);
            return this;
        }

        WinePropertyMap build() {
            return map;
        }
    }
}
