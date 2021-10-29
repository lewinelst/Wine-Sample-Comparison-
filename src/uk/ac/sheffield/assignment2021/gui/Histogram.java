package uk.ac.sheffield.assignment2021.gui;
/**
 * 
 * Class that holds data in a way that is stuctured like a histogram.
 * 
 *
 * @author Lewin Elston 
 *
 */

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.HistogramBin;
import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

public class Histogram extends AbstractHistogram {
    /**
     * Constructor. Called by AbstractWineSampleBrowserPanel
     *
     * @param cellar              to allow for getting min / max / avg values
     * @param filteredWineSamples a List of WineSamples to generate a histogram for.
     *                            These have already been filtered by the GUI's queries.
     * @param property            the WineProperty to generate a histogram for.
     */
    public Histogram(AbstractWineSampleCellar cellar, List<WineSample> filteredWineSamples, WineProperty property)
    {
        super(cellar, filteredWineSamples, property);
    }

    @Override
    public void updateHistogramContents(WineProperty property, List<WineSample> filteredWineSamples) {
        wineCountsPerBin.clear();
        if (filteredWineSamples.isEmpty() == false){ // checks if list of samples is empty
            double diff = cellar.getMaximumValue(property, filteredWineSamples) - cellar.getMinimumValue(property, filteredWineSamples);
            double min = cellar.getMinimumValue(property, filteredWineSamples);
            double increment = diff / 11; 
            for (int i = 0; i < 11; i++){ // makes 11 bins
                int value = 0;
                for (int j = 0; j < filteredWineSamples.size(); j++){ // works out number of values in bin
                    if ((filteredWineSamples.get(j).getProperty(property) > (min + (increment * i))) && (filteredWineSamples.get(j).getProperty(property) < (min + (increment * i) + increment))){
                        value += 1; 
                    }
                }
                if (i == 10){ // last bin in histogram being added 
                    HistogramBin bin = new HistogramBin(min + (increment * i), min + (increment * i) + increment, true);
                    wineCountsPerBin.put(bin, value);
                } else { // rest of bins in histogram being added
                    HistogramBin bin = new HistogramBin(min + (increment * i), min + (increment * i) + increment, false);
                    wineCountsPerBin.put(bin, value);
                }
            }
        } else { // clears list if sample list is empty
            wineCountsPerBin.clear();
        }
    }

    @Override
    public double getAveragePropertyValue() throws NoSuchElementException {
        return cellar.getMeanAverageValue(property, filteredWineSamples);
    }
}
