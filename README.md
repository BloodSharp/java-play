# Java - Play

## Description

This is a fully functional desktop drawing application, which allows the user to draw any kind of graphical image and save it on the hard drive.

![](/images/java.PNG)

1. DrawingPanel – represents the panel for drawing
2. PaintToolPanel–contains tools used for drawing
3. StrokeToolPanel–contains a JSlider used to set the width of the brush
4. StrokePanel–shows the current brush width
5. ColorPalette–contains 92 different colors the user can choose from
6. ColorButton–represents the color button the use can click on to select that color
7. ColorPanel–shows  the  current  color  and  opens  a  JColorChooser  for  selecting  additional colors
8. MenuBar–represents the application’s menu bar with several different options
9. ToolButton–represents the tool button the user can click on to select the desired tool for drawing

The sourcecode of the application consists of numerous classes and inner classes, as shown in the UML diagram below.

![](/images/java-uml.PNG)

## Source Code Overview

The Main class starts the application and creates a new instance of the PaintApplication class.

The PaintApplication class  extends the JFrame class, and  adds  the  named  GUI  classes  to  the  main application window. In addition, the PaintApplication sets the starting color of the ColorPalette to black.

The DrawingPanelclass  contains  all  the  drawing  logic,  and  communicates  with  the ToolFactory class to get the currently selected ToolButton(containing an instance of the Tool class) and draw a corresponding shape on the screen.

The ToolFactory creates a Toolclass instance, depending on the given parameter. It also provides eleven different Tool static variables, used to distinguish the Tool instances. The caller choses from one of those constant variables, and passes it to the ToolFactory’s createTool method. In response, the ToolFactory returns the corresponding instance of the Tool class.

The PaintToolPanel class creates instances of all the ToolButton classes, and adds them to the panel. In addition it adds a mouse listener to the colorPickerButton, so that the button opens a JColorChooser on mouse click.

The PaintToolPanel also contains a JComboBox and its action event, for choosing the type of drawing shapes. If the value of the combo box is set to 0 (empty), empty shape buttons are added to the panel and the corresponding Tool classes’instantiated. If the value of the combo box is set to 1 (filled), filled shape buttons are added to the panel, and the corresponding Tool classes’instantiated.

The ToolButton class  extends  JButton  and  implements  ActionListener. Its constructor  receives  a Tool  class  instance  which  is  passed  to  the  DrawingPanel’s currentTool upon   triggering   the actionPerformed method (when the tool button is clicked).The ToolDetails class extends the Tool class and provides methods for customizing the color and the stroke of the brush.

The StrokeToolPanel class  creates  instances  of  the StrokePanel(used  to  display  the  current stroke) and JSlider(used to change the current stroke) classes, and adds them to the panel.