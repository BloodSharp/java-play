/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


//Class used to display the stroke slider
public class StrokeToolPanel extends JPanel
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    protected JSlider strokeSlider;
    protected JPanel strokePanel;
    protected StrokePanel stroke;

    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
    public StrokeToolPanel(int stroke)
    {
        setPreferredSize(new Dimension(50, 256));                   //customize appearance
        setBackground(Color.darkGray);
        setLayout(new FlowLayout());

        strokeSlider = new JSlider(0, 0, 16, 5);                    //create slider
        strokeSlider.setPreferredSize(new Dimension(200,20));       //customize slider
        strokeSlider.setPaintTicks(false);
        strokeSlider.setMajorTickSpacing(1);
        strokeSlider.setValue(stroke);
        strokeSlider.revalidate();

        SlideChangeListener listener = new SlideChangeListener();   //create and add change listener
        strokeSlider.addChangeListener(listener);
        this.stroke = new StrokePanel();                             //create the stroke
        strokePanel = new JPanel();                                  //create the stroke panel that will hold the stroke
        strokePanel.setBackground(new Color(228, 237, 245));
        strokePanel.setPreferredSize(new Dimension(200, 100));
        strokePanel.setLayout(new FlowLayout());
        strokePanel.add(this.stroke, "South");                      //add stroke to stroke panel
        add(strokePanel);                                           //add stroke panel to class
        add(strokeSlider);                                          //add stroke slider to class
    }

    /**************************************************************************************************************
     ***************************************************CHANGE LISTENER********************************************
     **************************************************************************************************************/
    private class SlideChangeListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent event)   //called when the value of the slider changes
        {
            Main.paint.drawingPanel.currentToolDetails.setStrokeWidth(strokeSlider.getValue());   //set new stroke
            repaint();
        }
    }
}
