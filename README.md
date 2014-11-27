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

The Mainclass starts the application and creates a new instance of the PaintApplicationclass. The PaintApplicationclass  extendsthe JFrameclass, and  adds  the  named  GUI  classes  to  the  main application window. In addition, the PaintApplicationsets the starting color of the ColorPaletteto black.The DrawingPanelclass  contains  all  the  drawing  logic,  and  communicates  with  the ToolFactoryclass to get the currently selected ToolButton(containing an instance of the Toolclass) and draw a corresponding shape on the screen.The ToolFactorycreates a Toolclass instance, depending on the given parameter. It also provides eleven different Toolstatic variables, used to distinguish the Toolinstances. The caller chosesfrom one of those constant variables, and passes it to the ToolFactory’screateToolmethod. In response, the ToolFactoryreturns the corresponding instance of the Toolclass.The PaintToolPanelclass creates instances of all the ToolButtonclasses, and adds them to the panel. In additionit adds a mouse listener to the colorPickerButton, so that the button opensa JColorChooser on mouse click. The PaintToolPanelalso contains a JComboBox and its action event, for choosing the type of drawing shapes. If the value of the combo box is set to 0 (empty), empty shape buttons are added to the panel and the corresponding Tool classes’instantiated. If the value of the combo box is set to 1 (filled), filled shape buttons are added to the panel, and the corresponding Tool classes’instantiated.The ToolButtonclass  extends  JButton  and  implements  ActionListener. Itsconstructor  receives  a Tool  class  instance  which  is  passed  to  the  DrawingPanel’s currentToolupon   triggering   the actionPerformed method (when the tool button is clicked).The ToolDetailsclass extends the Toolclass and provides methods for customizing the color and the stroke of the brush.