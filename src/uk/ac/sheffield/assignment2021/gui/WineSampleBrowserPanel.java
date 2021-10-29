package uk.ac.sheffield.assignment2021.gui;

/**
 * 
 * Class which extends AbstractWineSampleBrowserPanel and provides a layout for the gui 
 * 
 *
 * @author Lewin Elston 
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogramPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractWineSampleBrowserPanel;
import uk.ac.sheffield.assignment2021.gui.Histogram;
import uk.ac.sheffield.assignment2021.gui.HistogramPanel;

public class WineSampleBrowserPanel extends AbstractWineSampleBrowserPanel {
    public WineSampleBrowserPanel(AbstractWineSampleCellar cellar) {
        super(cellar);
    }

    @Override
    public void addListeners() {
        buttonAddFilter.addActionListener(new AddFilterButton());
        buttonClearFilters.addActionListener(new ClearFilterButton());
        comboHistogramProperties.addActionListener(new ComboHistogramProp());
    }

    @Override
    public void addFilter() {
        String stringProperty = comboQueryProperties.getSelectedItem().toString(); //Creates the wine property needed for the subquery 
        WineProperty convertedWineProperty = WineProperty.fromName(stringProperty);
        Double queryValue;

        try //checks if text field for value isn't empty or not a number
        {  
            queryValue = Double.parseDouble(value.getText());
        }  
        catch(NumberFormatException nfe) //makes value zero
        {  
            queryValue = 0.0;
        }  
        
        
        SubQuery subQuery = new SubQuery(convertedWineProperty, comboOperators.getSelectedItem().toString(), queryValue); //Creates the subquery 
        subQueryList.add(subQuery);
        subQueriesTextArea.append(subQuery.toString() + "; "); //Adds subquery to text area

        executeQuery();

        // Updates filteredWineSamplesTextArea with the results
        updateWineDetailsBox();

        //Updates statistics area
        updateStatistics();
        
    }

    @Override
    public void clearFilters() {
        //Clears Lists
        subQueryList.clear();
        filteredWineSampleList.clear();
        //Reverts back to original sample list 
        filteredWineSampleList = cellar.getWineSampleList(WineType.ALL);
        //Sets text areas to null and updates
        subQueriesTextArea.setText(null);
        updateStatistics();
        updateWineDetailsBox();
        
    }

    @Override
    public void updateStatistics() {
        statisticsTextArea.setText(null);
        Object[][] data = {{cellar.getMinimumValue(WineProperty.FixedAcidity, filteredWineSampleList), cellar.getMinimumValue(WineProperty.VolatileAcidity, filteredWineSampleList), // Minimum
            cellar.getMinimumValue(WineProperty.CitricAcid, filteredWineSampleList), cellar.getMinimumValue(WineProperty.ResidualSugar, filteredWineSampleList),
            cellar.getMinimumValue(WineProperty.Chlorides, filteredWineSampleList), cellar.getMinimumValue(WineProperty.FreeSulfurDioxide, filteredWineSampleList),
            cellar.getMinimumValue(WineProperty.TotalSulfurDioxide, filteredWineSampleList), cellar.getMinimumValue(WineProperty.Density, filteredWineSampleList),
            cellar.getMinimumValue(WineProperty.PH, filteredWineSampleList), cellar.getMinimumValue(WineProperty.Sulphates, filteredWineSampleList),
            cellar.getMinimumValue(WineProperty.Alcohol, filteredWineSampleList), cellar.getMinimumValue(WineProperty.Quality, filteredWineSampleList)}, 
            
            {cellar.getMaximumValue(WineProperty.FixedAcidity, filteredWineSampleList), cellar.getMaximumValue(WineProperty.VolatileAcidity, filteredWineSampleList), // Maximum
            cellar.getMaximumValue(WineProperty.CitricAcid, filteredWineSampleList), cellar.getMaximumValue(WineProperty.ResidualSugar, filteredWineSampleList),
            cellar.getMaximumValue(WineProperty.Chlorides, filteredWineSampleList), cellar.getMaximumValue(WineProperty.FreeSulfurDioxide, filteredWineSampleList),
            cellar.getMaximumValue(WineProperty.TotalSulfurDioxide, filteredWineSampleList), cellar.getMaximumValue(WineProperty.Density, filteredWineSampleList),
            cellar.getMaximumValue(WineProperty.PH, filteredWineSampleList), cellar.getMaximumValue(WineProperty.Sulphates, filteredWineSampleList),
            cellar.getMaximumValue(WineProperty.Alcohol, filteredWineSampleList), cellar.getMaximumValue(WineProperty.Quality, filteredWineSampleList)}, 
            
            {cellar.getMeanAverageValue(WineProperty.FixedAcidity, filteredWineSampleList), cellar.getMeanAverageValue(WineProperty.VolatileAcidity, filteredWineSampleList), // Maximum
            cellar.getMeanAverageValue(WineProperty.CitricAcid, filteredWineSampleList), cellar.getMeanAverageValue(WineProperty.ResidualSugar, filteredWineSampleList),
            cellar.getMeanAverageValue(WineProperty.Chlorides, filteredWineSampleList), cellar.getMeanAverageValue(WineProperty.FreeSulfurDioxide, filteredWineSampleList),
            cellar.getMeanAverageValue(WineProperty.TotalSulfurDioxide, filteredWineSampleList), cellar.getMeanAverageValue(WineProperty.Density, filteredWineSampleList),
            cellar.getMeanAverageValue(WineProperty.PH, filteredWineSampleList), cellar.getMeanAverageValue(WineProperty.Sulphates, filteredWineSampleList),
            cellar.getMeanAverageValue(WineProperty.Alcohol, filteredWineSampleList), cellar.getMeanAverageValue(WineProperty.Quality, filteredWineSampleList)}};
        Object[] columnNames = {"f_acid", "v_acid", "c_acid", "r_sugar", "chlorid", "f_sulf", "t_sulf", "dens", "ph", "sulph", "alc", "qual"};
        String[] valueNames = {"Minimum", "Maximum", "Mean"};
        JTable table = new JTable(data, columnNames);

        int rows = table.getRowCount();
        int columns = table.getColumnCount();
        Object[][] dataAtPos = new Object[rows][columns];

        for (int i = -1; i < rows; i++){ // Sets up table of statistics to be displayed 
            if (i == -1){
                for (int j = 0; j < columns; j++){
                    statisticsTextArea.append("\t" + table.getColumnName(j));
                }
            } else {
                statisticsTextArea.append(valueNames[i] + "\t");
                for (int j = 0; j < columns; j++){
                    dataAtPos[i][j] = table.getValueAt(i, j);
                    statisticsTextArea.append(String.format("%.3f", dataAtPos[i][j]) + "\t");
                }
            }
            statisticsTextArea.append("\n");
        }

        
    }

    @Override
    public void updateWineDetailsBox() {
        filteredWineSamplesTextArea.setText(null);

        for (int i = 0; i < filteredWineSampleList.size(); i++){
            filteredWineSamplesTextArea.append(filteredWineSampleList.get(i).toString() + "\n");
        }
    }

    @Override
    public void executeQuery() {
        WineType queryWineType = WineType.ALL;
        if (comboWineTypes.getSelectedItem().toString() == "ALL"){ // finds the wine type
            queryWineType = WineType.ALL;
        } else if (comboWineTypes.getSelectedItem().toString() == "RED"){
            queryWineType = WineType.RED;
        } else if (comboWineTypes.getSelectedItem().toString() == "WHITE"){
            queryWineType = WineType.WHITE;
        }
        Query queryToApply = new Query(subQueryList, queryWineType); //Makes the query to be executed 
        filteredWineSampleList = queryToApply.executeQuery(cellar); //returns list of samples
    }

    class AddFilterButton implements ActionListener {
        public void actionPerformed(ActionEvent e){
            addFilter();
        }
    }

    class ClearFilterButton implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (subQueriesTextArea.getText().trim().length() == 0){ // checks if no subqueries 

            } else {
                clearFilters();
            }
        }
    }

    class ComboHistogramProp implements ActionListener {
        public void actionPerformed(ActionEvent e){
            updateHistogram();
            repaint();
        }
    }
}


