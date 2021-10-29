package uk.ac.sheffield.assignment2021;

/**
 * 
 * Class that provides cellar management functions.
 * 
 *
 * @author Lewin Elston 
 *
 */

import uk.ac.sheffield.assignment2021.codeprovided.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class WineSampleCellar extends AbstractWineSampleCellar {
    /**
     * Constructor - reads wine sample datasets and list of queries from text file,
     * and initialises the wineSampleRacks Map
     *
     * @param redWineFilename
     * @param whiteWineFilename
     */
    public WineSampleCellar(String redWineFilename, String whiteWineFilename) {
        super(redWineFilename, whiteWineFilename);
    }

    @Override
    public WinePropertyMap parseWineFileLine(String line) throws IllegalArgumentException {
        //Splits line into string array and then converts it to a double array
        WinePropertyMap parsedLine = new WinePropertyMap();
        String[] stringValues = line.split(";");
        Double[] doubleValues = new Double[stringValues.length];

        if (doubleValues.length != 12){ // throws exepcetion if line is not formatted right. 
            throw new IllegalArgumentException("Line does not have correct number of fields");
        }
        for(int i = 0; i < stringValues.length; i++){
            doubleValues[i] = Double.parseDouble(stringValues[i]);
        }
        //Puts values in the property map
        parsedLine.put(WineProperty.FixedAcidity, doubleValues[0]);
        parsedLine.put(WineProperty.VolatileAcidity, doubleValues[1]);
        parsedLine.put(WineProperty.CitricAcid, doubleValues[2]);
        parsedLine.put(WineProperty.ResidualSugar, doubleValues[3]);
        parsedLine.put(WineProperty.Chlorides, doubleValues[4]);
        parsedLine.put(WineProperty.FreeSulfurDioxide, doubleValues[5]);
        parsedLine.put(WineProperty.TotalSulfurDioxide, doubleValues[6]);
        parsedLine.put(WineProperty.Density, doubleValues[7]);
        parsedLine.put(WineProperty.PH, doubleValues[8]);
        parsedLine.put(WineProperty.Sulphates, doubleValues[9]);
        parsedLine.put(WineProperty.Alcohol, doubleValues[10]);
        parsedLine.put(WineProperty.Quality, doubleValues[11]);

        //Returns the map
        return parsedLine;
    }

    @Override
    public void updateCellar() {
        //Gets lists of white and red wines
        List<WineSample> red = wineSampleRacks.get(WineType.RED);
        List<WineSample> white = wineSampleRacks.get(WineType.WHITE);
        
        //Combines the list
        List<WineSample> all = new ArrayList<WineSample>();
        all.addAll(red);
        all.addAll(white);

        //Puts them into wineSampleRacks
        wineSampleRacks.put(WineType.ALL, all);
    }

    @Override
    public double getMinimumValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
                //Selects the first value of the property 
                double lowest = wineList.get(0).getProperty(wineProperty);
                //Goes through each sample in the list and checks for a lower value
                for (int i = 0; i < wineList.size(); i++){
                    if (wineList.get(i).getProperty(wineProperty) < lowest){
                        lowest = wineList.get(i).getProperty(wineProperty);
                    }
                }

        return lowest;

    }

    @Override
    public double getMaximumValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
                //Selects the first value of the property
                double highest = wineList.get(0).getProperty(wineProperty);
                //Goes through each sample in the list and checks for a higher value
                for (int i = 0; i < wineList.size(); i++){
                    if (wineList.get(i).getProperty(wineProperty) > highest){
                        highest = wineList.get(i).getProperty(wineProperty);
                    }
                }

        return highest;
    }

    @Override
    public double getMeanAverageValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
                double total = 0;
                //adds up properties from each value
                for (int i = 0; i < wineList.size(); i++){
                    total += wineList.get(i).getProperty(wineProperty);
                }
        // returns average of that property
        return (total / wineList.size());
    }

    @Override
    public List<WineSample> getFirstFiveWines(WineType type) {
        //Makes list to hold the wines and list of all of that wine type
        List<WineSample> fiveWines = new ArrayList<WineSample>();
        List<WineSample> allWines = wineSampleRacks.get(type);

        //Adds the first 5 wines of that type to the list and returns the list.
        for (int i = 0; i < 5; i++){
            fiveWines.add(i, allWines.get(i));
        }
        return fiveWines;
    }
}
