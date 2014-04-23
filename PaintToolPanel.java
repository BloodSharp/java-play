/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintToolPanel extends JPanel
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    protected ToolButton toolButtons[];
    public StrokeToolPanel strokeToolPanel;
    private JComboBox fillerType;
    private Icon pencil = new ImageIcon(getClass().getResource("images/IMG_OLOVKA_48.png"));
    private Icon roundRectangle = new ImageIcon(getClass().getResource("images/polygon.png"));
    private Icon filledRoundRectangle = new ImageIcon(getClass().getResource("images/filled-polygon.png"));
    private Icon oval = new ImageIcon(getClass().getResource("images/oval.png"));
    private Icon filledOval = new ImageIcon(getClass().getResource("images/filled-oval.png"));
    private Icon rectangle = new ImageIcon(getClass().getResource("images/rectangle.png"));
    private Icon filledRectangle = new ImageIcon(getClass().getResource("images/filled-rectangle.png"));
    private Icon lineTool = new ImageIcon(getClass().getResource("images/line-tool.png"));
    private Icon paintBrush = new ImageIcon(getClass().getResource("images/paint-brush.png"));
    private Icon eraser = new ImageIcon(getClass().getResource("images/eraser.png"));
    private JPanel toolPanel = new JPanel();
    private ToolButton colorPickerButton;


    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
    public PaintToolPanel(StrokeToolPanel strokeToolPanel)
    {
        setBackground(Color.darkGray);                          //customize the panel
        setPreferredSize(new Dimension(200, 0));
        setLayout(new BorderLayout(8, 8));

        toolPanel.setLayout(new GridLayout(4, 2));              //customize the tool panel
        toolPanel.setBackground(Color.darkGray);
        toolPanel.setPreferredSize(new Dimension(200, 300));
        this.strokeToolPanel = strokeToolPanel;                 //set stroke panel
        toolButtons = new ToolButton[8];                        //create new array of buttons

        String[] fillerTypes = {"EMPTY", "FILLED"};             //create String array for the combo box
        fillerType = new JComboBox(fillerTypes);                //create the combo box
        ComboBoxHandler handler = new ComboBoxHandler();        //creating a new instance of the ComboBoxHandler inner class
        fillerType.addActionListener(handler);                  //add combo box listener
        fillerType.setFont(new Font("Cambria", Font.BOLD, 16)); //set font

        //****************************************COLOR PICKER BUTTON TOOL***************************************************************************************
        Icon colorPicker = new ImageIcon(getClass().getResource("images/IMG_BOJA_48.png"));
        colorPickerButton = new ToolButton(colorPicker, ToolFactory.createTool(ToolFactory.PENCILL_TOOL));
        colorPickerButton.addMouseListener(new MouseAdapter() {      //add a mouse listener

            public void mousePressed(MouseEvent event) {                //when the user presses the mouse button
                {                                                       //display the JColorChooser window
                    ColorPalette.selectedColorDisplay.setBackground(JColorChooser.showDialog(Main.paint, "Change Color", Main.paint.drawingPanel.brushColor));
                    ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();                                   //change the isSelected color
                    Main.paint.drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());     //change the ToolDetails color
                    Main.paint.drawingPanel.setCurrentColor(ColorPalette.selectedColor);                                   //change the DrawingPanel brushColor
                }
            }
        });


        addBasicToolButtons();                //fill basic button tools
        addEmptyShapeToolButtons();           //fill empty shape button tools
        for (ToolButton toolButton : toolButtons) toolPanel.add(toolButton);    //add buttons to tool panel

        add(toolPanel, "North");
        add(fillerType, "Center");
        add(strokeToolPanel, "South");
    }

    private void addBasicToolButtons()
    {
        toolButtons[0] = new ToolButton(pencil, ToolFactory.createTool(ToolFactory.PENCILL_TOOL));
        toolButtons[1] = new ToolButton(paintBrush, ToolFactory.createTool(ToolFactory.AIR_BRUSH_TOOL));
        toolButtons[2] = new ToolButton(eraser, ToolFactory.createTool(ToolFactory.ERASER_TOOL));
        toolButtons[3] = colorPickerButton;
        toolButtons[4] = new ToolButton(lineTool, ToolFactory.createTool(ToolFactory.LINE_TOOL));
    }

    private void addEmptyShapeToolButtons()
    {
        toolButtons[5] = new ToolButton(oval, ToolFactory.createTool(ToolFactory.OVAL_TOOL));
        toolButtons[6] = new ToolButton(roundRectangle, ToolFactory.createTool(ToolFactory.ROUND_RECTANGLE_TOOL));
        toolButtons[7] = new ToolButton(rectangle, ToolFactory.createTool(ToolFactory.RECTANGLE_TOOL));
    }

    private void addFilledShapeToolButtons()
    {
        toolButtons[5] = new ToolButton(filledOval, ToolFactory.createTool(ToolFactory.FILLED_OVAL_TOOL));
        toolButtons[6] = new ToolButton(filledRoundRectangle, ToolFactory.createTool(ToolFactory.FILLED_ROUND_RECTANGLE_TOOL));
        toolButtons[7] = new ToolButton(filledRectangle, ToolFactory.createTool(ToolFactory.FILLED_RECTANGLE_TOOL));
    }

    /**************************************************************************************************************
     **********************************************COMBO BOX HANDLER************************************************
     **************************************************************************************************************/
    private class  ComboBoxHandler implements ActionListener  //combo box (filling type) even handling
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            fillerType = (JComboBox)e.getSource();
            int selectedValue = fillerType.getSelectedIndex();
            if (selectedValue ==0)          //if empty
            {
                for (ToolButton toolButton1 : toolButtons) toolPanel.remove(toolButton1);  //remove buttons
                revalidate();
                repaint();
                addBasicToolButtons();                      //add basic buttons
                addEmptyShapeToolButtons();                 //add empty shape buttons
                for (ToolButton toolButton : toolButtons) toolPanel.add(toolButton);
            }
            else if (selectedValue == 1 )  //if filled
            {
                for (ToolButton toolButton1 : toolButtons) toolPanel.remove(toolButton1); //remove all buttons
                revalidate();
                repaint();
                addBasicToolButtons();                     //add basic buttons
                addFilledShapeToolButtons();               //add filled buttons
                for (ToolButton toolButton : toolButtons) toolPanel.add(toolButton);
            }
        }
    }
}
