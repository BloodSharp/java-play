/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Class used to display a palette of 92 different selectedColor boxes
public class ColorPalette extends JPanel
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    protected ColorButton colorButtons[];     //array of ColorButton objects
    public static JPanel selectedColorDisplay;    //JPanel displaying the currently isSelected color
    protected Color colors[];                 //array of colors
    public static Color selectedColor;            //isSelected selectedColor

    /**************************************************************************************************************
     *******************************************************CONSTRUCTOR********************************************
     **************************************************************************************************************/
    public ColorPalette()
    {
        setBackground(Color.darkGray);                //set the background of the palette
        setLayout(new BorderLayout());                //set the layout of the palette
        colors = new Color[92];                       //create a Color array of 92 length for holding 92 colors
        for(int i = 0; i < 92; i++)
        {                                             //fill the array with 92 different colors
            colors[i] = Color.getHSBColor((float) i / (float) 92, 0.85f, 1.0f);
        }

        selectedColor = Color.black;                                    //set initial selectedColor to black
        selectedColorDisplay = new JPanel();                            //create a new JPanel for holding the selectedColor
        selectedColorDisplay.setPreferredSize(new Dimension(180, 92));   //set the size of the panel
        selectedColorDisplay.addMouseListener(new MouseAdapter() {      //add a mouse listener

            public void mousePressed(MouseEvent event) {                //when the user presses the mouse button
                {                                                       //display the JColorChooser window
                    selectedColorDisplay.setBackground(JColorChooser.showDialog(Main.paint, "Change Color", Main.paint.drawingPanel.brushColor));
                    selectedColor = selectedColorDisplay.getBackground();                                   //change the isSelected color
                    Main.paint.drawingPanel.currentToolDetails.setColor(selectedColorDisplay.getBackground());     //change the ToolDetails color
                    Main.paint.drawingPanel.setCurrentColor(selectedColor);                                   //change the DrawingPanel brushColor
                }
            }
        });
        JPanel colorButtonsGrid = new JPanel();                     //create the ColorButton grid
        colorButtonsGrid.setBackground(Color.darkGray);
        colorButtonsGrid.setLayout(new GridLayout(4, 16, 6, 6));
        colorButtons = new ColorButton[colors.length];              //add the created colors to the ColorButton grid
        for (int i = 0; i < colorButtons.length; i++) {
            colorButtons[i] = new ColorButton(colors[i]);
            colorButtonsGrid.add(colorButtons[i]);
        }

        ColorPanel colorButtonsPanel = new ColorPanel(Color.darkGray);      //create the ColorPanel for holding the ColorButtons
        colorButtonsPanel.setLayout(new BorderLayout(6, 6));
        colorButtonsPanel.add(selectedColorDisplay, "West");
        colorButtonsPanel.add(colorButtonsGrid, "Center");
        JPanel colorButtonRows = new JPanel();                              //create the ColorButton panels
        colorButtonRows.setLayout(new BorderLayout());
        colorButtonRows.add(new ColorPanel(Color.darkGray), "West");
        colorButtonRows.add(new ColorPanel(Color.darkGray), "East");
        colorButtonRows.add(new ColorPanel(Color.darkGray), "South");
        colorButtonRows.add(new ColorPanel(Color.darkGray), "North");
        colorButtonRows.add(colorButtonsPanel, "Center");
        add(colorButtonRows, "Center");
    }

    /**************************************************************************************************************
     *******************************************************METHODS************************************************
     **************************************************************************************************************/
    public void deselectAll()
    {                                                            //deselect all color boxes
        for (ColorButton colorButton : colorButtons) colorButton.isSelected = false;
    }

    public void paintComponent(Graphics g)                      //overrides method in JComponent
    {
        super.paintComponent(g);
    }
}