/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Class that represents one selectedColor button (box) located in the ColorPalette
public class ColorButton extends JPanel
{
    Color color;
    boolean isSelected;

    /**************************************************************************************************************
     *******************************************************CONSTRUCTOR********************************************
     **************************************************************************************************************/
    public ColorButton(Color clr)
    {
        color = clr;                                //set selectedColor of the box
        isSelected = false;                         //set isSelected to false
        setBackground(color);                       //set background to the given selectedColor
        addMouseListener(new MouseHandler());       //add mouse event
    }

    /**************************************************************************************************************
     *******************************************************PAINT METHOD*******************************************
     **************************************************************************************************************/
    public void paintComponent(Graphics g)     //overrides method in JComponent
    {
        super.paintComponent(g);               //call base method
        g.setColor(Color.lightGray);           //set selectedColor of
        g.drawRect(0, 0, getWidth(), getHeight());   //draw rectangle representing the selectedColor box
        if(isSelected)
        {                                      //change the appearance if the selectedColor box is isSelected
            g.setColor(Color.white);
            g.drawRect(1, 1, getWidth(), getHeight());
            g.drawRect(-1, -1, getWidth(), getHeight());
        }
    }

    /**************************************************************************************************************
     *******************************************************MOUSE EVENTS*******************************************
     **************************************************************************************************************/
    private class MouseHandler extends MouseAdapter
    {
        public void mousePressed(MouseEvent event)          //when the mouse button is pressed
        {
            Main.paint.colorPalette.deselectAll();          //deselect all colors
            isSelected = true;                                //set the current selectedColor to isSelected
            Main.paint.drawingPanel.setCurrentColor(color);      //set the brush selectedColor of the draw panel to the isSelected selectedColor
            ColorPalette.selectedColorDisplay.setBackground(color);  //set the selectedColor of the selectedColorDisplay
            Main.paint.repaint();                            //repaint the main application window
        }
        public void mouseReleased(MouseEvent mouseevent){ }

        public void mouseClicked(MouseEvent mouseevent) {}

        public void mouseEntered(MouseEvent mouseevent){ }

    }
}
