package uk.ac.sheffield.assignment2021.test.forstudents;

import static org.junit.Assert.*;

import org.junit.*;
import uk.ac.sheffield.assignment2021.WineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.gui.Histogram;
import uk.ac.sheffield.assignment2021.test.common.TestCommon;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHistogram
{
    private static final WineSampleCellar CELLAR = new WineSampleCellar(
            TestCommon.RED_FILE,
            TestCommon.WHITE_FILE
    );

    @Test
    public void testUpdateHistogramContentsNoWines() {

        Histogram histogram = new Histogram(CELLAR, TestCommon.DUMMY_RED_WINES, WineProperty.FixedAcidity);
        histogram.updateHistogramContents(WineProperty.FixedAcidity, new ArrayList<>());
        assertTrue(histogram.getBinsInBoundaryOrder().isEmpty());
    }

    @Test
    public void testUpdateHistogramContentsAllWinesSame() {

        Histogram histogram = new Histogram(CELLAR, TestCommon.DUMMY_RED_WINES, WineProperty.FixedAcidity);
        histogram.updateHistogramContents(WineProperty.FixedAcidity, Arrays.asList(
                TestCommon.DUMMY_RED_WINES.get(0),
                TestCommon.DUMMY_RED_WINES.get(0),
                TestCommon.DUMMY_RED_WINES.get(0)
        ));

        assertEquals(1, histogram.getBinsInBoundaryOrder().size());
        assertTrue(histogram.getWineCountsPerBin().containsValue(3));
    }

}
