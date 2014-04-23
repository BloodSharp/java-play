/**
 *   WEEK 8 HIA APPLICATION
 *   Ivana Zuber
 *   March 2013
 *   The University of Liverpool. UK
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton implements ActionListener
{

    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    public JLabel label;
    public Tool tool;

    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
    public ToolButton(Icon icon, Tool tool)
    {
        label = new JLabel(icon);
        setLayout(new BorderLayout());
        add(label);
        this.tool = tool;
        addActionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    /**************************************************************************************************************
     **********************************************ACTION EVENT****************************************************
     **************************************************************************************************************/
    public void actionPerformed(ActionEvent event)
    {
        Main.paint.drawingPanel.currentTool = tool;
        Main.paint.repaint();
    }
}
