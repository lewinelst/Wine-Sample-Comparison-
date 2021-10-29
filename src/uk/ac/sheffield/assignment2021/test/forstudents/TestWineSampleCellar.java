package uk.ac.sheffield.assignment2021.test.forstudents;

import static org.junit.Assert.*;

import org.junit.*;
import uk.ac.sheffield.assignment2021.WineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WinePropertyMap;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;
import uk.ac.sheffield.assignment2021.test.common.TestCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TestWineSampleCellar {

    private static final double DELTA = 0.0001;

    @Test
    public void testUpdateCellar() {
        WineSampleCellar cellar = new WineSampleCellar(TestCommon.RED_FILE, TestCommon.WHITE_FILE);

        assertEquals(1599, cellar.getWineSampleList(WineType.RED).size());
        assertEquals(4898, cellar.getWineSampleList(WineType.WHITE).size());
        assertEquals(1599 + 4898, cellar.getWineSampleList(WineType.ALL).size());

        // Should not change if updateCellar is called again
        cellar.updateCellar();

        assertEquals(1599, cellar.getWineSampleList(WineType.RED).size());
        assertEquals(4898, cellar.getWineSampleList(WineType.WHITE).size());
        assertEquals(1599 + 4898, cellar.getWineSampleList(WineType.ALL).size());
    }

    @Test
    public void testGetMeanAverageValue() {
        WineSampleCellar cellar = new WineSampleCellar(TestCommon.RED_FILE, TestCommon.WHITE_FILE);

        WinePropertyMap ap = new WinePropertyMap();
        WinePropertyMap bp = new WinePropertyMap();

        ap.put(WineProperty.Quality, 4);
        bp.put(WineProperty.Quality, 6);

        WineSample a = new WineSample(1, WineType.RED, ap);
        WineSample b = new WineSample(1, WineType.RED, bp);

        List<WineSample> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        assertEquals(5, cellar.getMeanAverageValue(WineProperty.Quality, list), DELTA);
    }

}
