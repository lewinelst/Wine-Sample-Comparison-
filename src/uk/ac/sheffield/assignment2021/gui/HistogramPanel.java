package uk.ac.sheffield.assignment2021.gui;
/**
 * 
 * Class that draws and displays a histogram.
 * 
 *
 * @author Lewin Elston 
 *
 */

import java.util.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Dimension;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogramPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractWineSampleBrowserPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.HistogramBin;

public class HistogramPanel extends AbstractHistogramPanel
{
    public HistogramPanel(AbstractWineSampleBrowserPanel parentPanel, AbstractHistogram histogram)
    {
        super(parentPanel, histogram);
    }


    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;
        List<HistogramBin> bins = getHistogram().getBinsInBoundaryOrder();
        double widthDim = (double)(d.getWidth());
        double heightDim = (double)(d.getHeight());
        //draws axis labels 
        g2.drawString("Frequency", (int)(widthDim * 0.1), (int)(heightDim * 0.3));
        g2.drawString("Property value", (int)(widthDim * 0.5), (int)(heightDim * 0.6));


        for (int i = 0; i < 5; i++){ // prints liens and labels 
            double yIncrement = getHistogram().largestBinCount() - (i * (getHistogram().largestBinCount() / 4));
            g2.drawString(String.format("%.3f", yIncrement),(int)(widthDim * 0.2), (int)(i * (heightDim * 0.125)));
            Line2D l = new Line2D.Double(widthDim * 0.25 ,i * (heightDim * 0.125), widthDim * 0.75 + (widthDim/20), i * (heightDim * 0.125));
            g2.draw(l);
        }
        for (int i = 0; i < 11; i++){ // adds rectangles that represent bins to graph 
            HistogramBin currentBin = bins.get(i);
            double height = (double)getHistogram().getNumWinesInBin(currentBin) / (double)getHistogram().largestBinCount();
            double barHeight = (height * (heightDim / 2));
            Rectangle2D bar = new Rectangle2D.Double((widthDim * 0.25) + (i * (widthDim/20)),(heightDim / 2) - barHeight,widthDim/20, barHeight); // Work on this, this is what makes the bars
            g2.draw(bar);
            g2.fill(bar);
            g2.drawString(String.format("%.3f", currentBin.getLowerBoundary()),(int)(widthDim * 0.25 + (i * (widthDim/20))), (int)(heightDim * 0.55)); // Draw x axis labels 
            if (i == 10){
                g2.drawString(String.format("%.3f", currentBin.getUpperBoundary()),(int)(widthDim * 0.25 + (( i + 1) * (widthDim/20))), (int)(heightDim * 0.55)); // Draw last label
            }
            
        } 


    }

    
}
