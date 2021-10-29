package uk.ac.sheffield.assignment2021.test.forstudents;

import static org.junit.Assert.*;

import org.junit.*;
import uk.ac.sheffield.assignment2021.WineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WinePropertyMap;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;
import uk.ac.sheffield.assignment2021.test.common.TestCommon;

import java.util.List;

public class TestCellarWineParsing
{
    // TODO Make a test that validates IDs, types and data are correct for the example dataset

    private static final double DELTA = 0.0001;

    @Test
    public void testParseWineFileLine() {
        WineSampleCellar cellar = new WineSampleCellar(TestCommon.RED_FILE, TestCommon.WHITE_FILE);

        String sampleLine = "8.1;0.38;0.28;2.1;0.066;13;30;0.9968;3.23;0.73;9.7;7";

        WinePropertyMap map = cellar.parseWineFileLine(sampleLine);

        assertEquals(8.1, map.get(WineProperty.FixedAcidity), DELTA);
        assertEquals(0.28, map.get(WineProperty.CitricAcid), DELTA);
        assertEquals(7, map.get(WineProperty.Quality), DELTA);
    }

    @Test
    public void testParseWineFileLineTooFewObservations() {
        WineSampleCellar cellar = new WineSampleCellar(TestCommon.RED_FILE, TestCommon.WHITE_FILE);

        String sampleLine = "8.1;0.38;0.28;2.1;13;30;0.9968;0.73;9.7;7";

        try {
            cellar.parseWineFileLine(sampleLine);
        } catch (IllegalArgumentException expected) {
            return; // Pass
        }
        fail("Expected exception now thrown");
    }

}
