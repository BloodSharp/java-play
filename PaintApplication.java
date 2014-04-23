/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import java.awt.*;

public class PaintApplication extends JFrame
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    public DrawingPanel drawingPanel;
    protected MenuBar menuBar;
    protected ColorPalette colorPalette;
    public PaintToolPanel paintToolPanel;

    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
    public PaintApplication()
    {
        super("JAVA DRAWING");  //overriding JFrame's title

        ImageIcon ImageIcon = getIcon("/images/IMG_BOJA_48.png");
        Image Image = ImageIcon.getImage();

        drawingPanel = new DrawingPanel();                              //create drawing panel
        menuBar = new MenuBar();                                        //create menu bar
        colorPalette = new ColorPalette();                              //create color palette panel
        paintToolPanel = new PaintToolPanel(new StrokeToolPanel(5));   //create drawing tool panel
        add(menuBar, "North");                                         //add panels to the main JFrame
        add(colorPalette, "South");
        add(paintToolPanel, "West");
        add(new JScrollPane(drawingPanel), "Center");

        this.setIconImage(Image);    //setting JFrame's icon image
        this.setSize(1024, 768);     //set size of the application
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //set default close operation
        this.setLocationRelativeTo(null);                               //set locating to the middle of the screen
        this.setVisible(true);                                          //set visible
        setStaringColor();                                             //set the starting color
    }

    /**************************************************************************************************************
     ***************************************************METHODS****************************************************
     **************************************************************************************************************/
    public void setStaringColor()     //set starting color to be used for drawing
    {
        ColorPalette.selectedColorDisplay.setBackground(Color.black);
        ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();
        drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());
        drawingPanel.brushColor = ColorPalette.selectedColor;
    }

    /**
     * Method used to return the ImageIcon object that has the path
     * equal to the one given to the method as an input parameter.
     * In case the inputted imagePath parameter is not valid
     * an exception will be caught in the try catch block
     * and the default image used in the JOptionPane
     * @param imagePath  class path
     * @return ImageIcon
     */
    public static ImageIcon getIcon(String imagePath)
    {
        try
        {
            return new ImageIcon(PaintApplication.class.getResource(imagePath));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid image path");
            return null;
        }
    }
}
