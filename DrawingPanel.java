/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class DrawingPanel extends JPanel implements  MouseListener, MouseMotionListener
{

    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    public Image OSI;                   // the off-screen image created in createOffScreenImage() used for backing up the contents of the panel
    int OSIWidth, OSIHeight;            // current width and height of OSI, used to check against the size of the window. If the size of the window changes, a new OSI is created
    private int mouseX, mouseY;         // the location of the mouse
    private int prevX, prevY;           // the previous location of the mouse
    private int startX, startY;         // the starting position of the mouse (not used for isDrawing curves)
    private boolean isDrawing;          // this is set to true when the user is isDrawing.
    private Graphics2D dragGraphics;    // A graphics context for the off-screen image, to be used while a drag is in progress.
    public Color brushColor;            // the selectedColor that is used for the figure that is  being drawn.
    int brushPoints[][];                //two-dimensional integer array used to display the brush effect
    protected Boolean mousePressed;     //indicates if the mouse is pressed
    public Tool currentTool;            //indicates the isSelected Tool
    public ToolDetails currentToolDetails;  //isSelected tool details
    public Color backgroundColor;           //background color


    /**************************************************************************************************************
     *****************************************************CONSTRUCTOR***********************************************
     **************************************************************************************************************/
    public DrawingPanel()
    {
        backgroundColor = Color.white;                  //set background color
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(1024, 768));     //set size
        addMouseListener(this);                         //add mouse listener
        addMouseMotionListener(this);                   //add mouse motion listener
        mousePressed = false;                           //set mousePressed to false
        brushColor = Color.black;                       //set initial brush color
        currentTool = ToolFactory.createTool(ToolFactory.PENCILL_TOOL);             //set initial painting tool
        currentToolDetails = new ToolDetails(brushColor, 5, ToolFactory.PENCILL_TOOL);     //set initial painting tool properties
    }


    /**************************************************************************************************************
     **************************************************DRAWING METHODS*********************************************
     **************************************************************************************************************/
    /**
     * Method used to draw shapes in the graphics context
     * The Tool parameter determines which shape will be drawn
     * For a line, a line is drawn from the brushPoints (x1, y1) to brushPoints (x2, y2)
     * For other shapes, brushPoints (x1, y1) and (x2, y2) give two corners of the shape
     * @param graphics2D    Graphics class
     * @param currentTool  isSelected tool
     * @param pointX1     point x1
     * @param pointY1     point y1
     * @param pointX2     point x2
     * @param pointY2     point y2
     */
    private void drawGraphics(Graphics2D graphics2D, Tool currentTool, int pointX1, int pointY1, int pointX2, int pointY2)
    {
        if (currentTool.toolType == ToolFactory.LINE_TOOL)                  //if isSelected tool is LINE
        {                                                                   //set stroke
            graphics2D.setStroke(new BasicStroke(currentToolDetails.strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(pointX1, pointY1, pointX2, pointY2);        // draw the line between the two brushPoints.
            repaint();                                                      //repaint to properly display stroke
            return;
        }

        if (currentTool.toolType == ToolFactory.AIR_BRUSH_TOOL)            //if isSelected tool is BRUSH
        {
                Random rand = new Random();                                //new Random class
                brushPoints = new int[(currentToolDetails.strokeWidth * currentToolDetails.strokeWidth) / 10][2];    //create a new two-dimensional array of variable size
                for (int i = 0; i < (currentToolDetails.strokeWidth * currentToolDetails.strokeWidth) / 10; i++)
                {
                    int pts[] = new int[2];
                        pts[0] = rand.nextInt(currentToolDetails.strokeWidth);      //fill the array with random integers
                        pts[1] = rand.nextInt(currentToolDetails.strokeWidth);
                    graphics2D.drawRect(pointX1 + pts[0], pointY1 + pts[1], 1, 1);  //draw a rectangle to create a brush effect
                    brushPoints[i] = pts;
                }
            repaint();                                                              //repaint to properly display stroke
        }

        int positionX, positionY;   // Top left corner of rectangle that contains the figure.
        int weight, height;         // Width and height of rectangle that contains the figure.
        if (pointX1 >= pointX2)
        {  // pointX2 is left edge
            positionX = pointX2;
            weight = pointX1 - pointX2;
        }
        else
        {   // pointX1 is left edge
            positionX = pointX1;
            weight = pointX2 - pointX1;
        }
        if (pointY1 >= pointY2)
        {  // pointY2 is top edge
            positionY = pointY2;
            height = pointY1 - pointY2;
        }
        else
        {   // pointY1 is top edge
            positionY = pointY1;
            height = pointY2 - pointY1;
        }

        if (currentTool.toolType == ToolFactory.RECTANGLE_TOOL)            //if isSelected tool is RECTANGLE
        {                                                                  //set stroke
            graphics2D.setStroke(new BasicStroke(currentToolDetails.strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawRect(positionX, positionY, weight, height);              //draw rectangle
            repaint();                                                      //repaint to properly display stroke
            return;
        }

        if (currentTool.toolType == ToolFactory.OVAL_TOOL)                  //if isSelected tool is OVAL
        {                                                                   //set stroke
            graphics2D.setStroke(new BasicStroke(currentToolDetails.strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawOval(positionX, positionY, weight, height);               //draw oval
            repaint();                                                      //repaint to properly display stroke
            return;
        }

        if (currentTool.toolType == ToolFactory.ROUND_RECTANGLE_TOOL)        //if isSelected tool is ROUND RECTANGLE
        {                                                                    //set stroke
            graphics2D.setStroke(new BasicStroke(currentToolDetails.strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawRoundRect(positionX, positionY, weight, height, 20, 20);    //draw round rectangle
            repaint();                                                      //repaint to properly display stroke
            return;
        }

        if (currentTool.toolType == ToolFactory.FILLED_OVAL_TOOL)            //if isSelected tool is FILLED OVAL
        {
            graphics2D.fillOval(positionX, positionY, weight, height);      //draw filled oval
            return;
        }

        if (currentTool.toolType == ToolFactory.FILLED_RECTANGLE_TOOL)      //if isSelected tool is FILLED RECTANGLE
        {
            graphics2D.fillRect(positionX, positionY, weight, height);      //draw filled rectangle
            return;
        }

        if (currentTool.toolType == ToolFactory.FILLED_ROUND_RECTANGLE_TOOL) //if isSelected tool is FILLED ROUND RECTANGLE
        {
            graphics2D.fillRoundRect(positionX, positionY, weight, height, 20, 20);   //draw filled round rectangle
        }
    }

    /**
     * Method used to repaint the rectangle
     * @param pointX1  point X
     * @param pointY1  point Y
     * @param pointX2  point X2
     * @param pointY2  point Y2
     */
    private void repaintRectangle(int pointX1, int pointY1, int pointX2, int pointY2)
    {
        int x, y;  // top left corner of rectangle that contains the figure
        int w, h;  // width and height of rectangle that contains the figure
        if (pointX2 >= pointX1)
        {   // pointX1 is left edge
            x = pointX1;
            w = pointX2 - pointX1;
        }
        else
        {   // pointX2 is left edge
            x = pointX2;
            w = pointX1 - pointX2;
        }
        if (pointY2 >= pointY1)
        {   // pointY1 is top edge
            y = pointY1;
            h = pointY2 - pointY1;
        }
        else
        {   // pointY2 is top edge.
            y = pointY2;
            h = pointY1 - pointY2;
        }
        repaint(x, y, w+1, h+1);      //add 1 pixel border along the right and bottom edges to allow for a pen overhang when isDrawing a line
    }

    /**
     * Method used to create the off-screen image
     * the method will create a new off-screen image if the size of the panel changes
     */
    private void createOffScreenImage()
    {
        if (OSI == null || OSIWidth != getSize().width || OSIHeight != getSize().height) {
            // Create the OSI, or make a new one if panel size has changed.
            OSI = null;  // (If OSI already exists, this frees up the memory.)
            OSI = createImage(getSize().width, getSize().height);
            OSIWidth = getSize().width;
            OSIHeight = getSize().height;
            Graphics graphics = OSI.getGraphics();  // Graphics context for isDrawing to OSI.
            graphics.setColor(getBackground());
            graphics.fillRect(0, 0, OSIWidth, OSIHeight);
            graphics.dispose();
        }
    }

    /**
     * Overrides the method in JComponent
     * Copies the off screen image to the screen after checking it exists
     * If a tool other than PENCIL and BRUSH is being used, draw the shape on top of the OSI
     * @param graphics Graphics
     */
    public void paintComponent(Graphics graphics)
    {
        createOffScreenImage();                             //create off-screen image
        Graphics2D graphics2D = (Graphics2D)graphics;       //convert Graphics to Graphics2D
        graphics.drawImage(OSI, 0, 0, this);                //draw image
        if (isDrawing &&                                      //if isDrawing...
                currentTool.toolType != ToolFactory.PENCILL_TOOL &&  //...and isSelected tool is not PENCIL...
                currentTool.toolType != ToolFactory.AIR_BRUSH_TOOL &&
                currentTool.toolType != ToolFactory.ERASER_TOOL)  //...and isSelected tool is not BRUSH
        {
            graphics.setColor(brushColor);                                             //set color
            drawGraphics(graphics2D, currentTool, startX, startY, mouseX, mouseY);     //call the drawGraphics method
        }

    }

    public void setOSImage (BufferedImage image)
    {
        OSI = image;                          //used by load file option to set the OSI to the given image
        repaint();
    }

    public void setImage(BufferedImage image)  //used by load file option to set the OSI to the given image
    {
        int w = image.getWidth();
        int h = image.getHeight();
        OSI = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        OSI = createImage(w, h);
        OSIWidth = getSize().width;
        OSIHeight = getSize().height;
        repaint();
        Graphics graphics = OSI.getGraphics();  // Graphics context for isDrawing to OSI.
        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, OSIWidth, OSIHeight);
        graphics.dispose();
    }

    public void clearImage(BufferedImage image)
    {
        Graphics2D g = image.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        repaint();
    }

    private Color getCurrentColor()             //get the isSelected color from the TollDetails class
    {
        if (currentTool.toolType != ToolFactory.ERASER_TOOL)  //...and isSelected tool is not BRUSH
        {
            return currentToolDetails.getColor();
        }
        else
        {
            return getBackground();
        }
    }

    public void setCurrentColor(Color clr)         //set the isSelected color to the toolDetails class
    {
        brushColor = clr;
        currentToolDetails.setColor(clr);
    }


    /**************************************************************************************************************
     **************************************************MOUSE EVENTS************************************************
     **************************************************************************************************************/

    /**
     * Method called when the user presses the mouse button on the panel
     * begins the draw operation in which the user sketches a curve or draws a shape
     * for PENCIL or BRUSH, a new segment of the curve is drawn each time the user moves the mouse
     * for the other shapes, a "rubber band cursor" is used - the figure is drawn between the starting point
     * and the current mouse location
     * @param evt MouseEvent
     */
    public void mousePressed(MouseEvent evt)
    {
        if (isDrawing)                    // Ignore mouse presses that occur when user is already isDrawing a curve
            return;                     // if the user presses two mouse toolButtons at the same time

        prevX = startX = evt.getX();    // save mouse coordinates.
        prevY = startY = evt.getY();

        brushColor = getCurrentColor();                 //get current color
        dragGraphics = (Graphics2D) OSI.getGraphics();  //convert Graphics
        dragGraphics.setColor(brushColor);              //set color
        dragGraphics.setBackground(getBackground());

        isDrawing = true;                                 //start isDrawing
    }

    /**
     * Method called when the user releases the mouse button
     * if the user was isDrawing a shape, the shape is drawn to the off-screen image
     * @param evt MouseEvent
     */
    public void mouseReleased(MouseEvent evt)
    {
        if (!isDrawing)
            return;             //return if the user isn't isDrawing.
        isDrawing = false;        //set isDrawing to false
        mouseX = evt.getX();    //save mouse coordinates
        mouseY = evt.getY();

        if (currentTool.toolType != ToolFactory.PENCILL_TOOL && currentTool.toolType != ToolFactory.AIR_BRUSH_TOOL && currentTool.toolType != ToolFactory.ERASER_TOOL)
        {
            repaintRectangle(startX, startY, prevX, prevY);
            if (mouseX != startX && mouseY != startY) {
                // Draw the shape only if both its height
                // and width are non-zero.
                drawGraphics(dragGraphics, currentTool, startX, startY, mouseX, mouseY);
                repaintRectangle(startX, startY, mouseX, mouseY);
            }
        }
        dragGraphics.dispose();
        dragGraphics = null;
    }

    /**
     * Method called whenever the user moves the mouse while a mouse button is down
     * If the user is isDrawing a curve, draw a segment of the curve on the off-screen image, and repaint the part
     * and repaint the part of the panel that contains the new line segment.
     * Otherwise, just call repaint and let paintComponent() draw the shape on top of the picture in the off-screen image.
     * @param evt MouseEvent
     */
    public void mouseDragged(MouseEvent evt)
    {
        if (!isDrawing)
            return;  // return if the user isn't isDrawing.

        mouseX = evt.getX();   // x-coordinate of mouse.
        mouseY = evt.getY();   // y=coordinate of mouse.

        if (currentTool.toolType == ToolFactory.PENCILL_TOOL)
        {
            drawGraphics(dragGraphics, ToolFactory.createTool(ToolFactory.LINE_TOOL), prevX, prevY, mouseX, mouseY); // A CURVE is drawn as a series of LINEs.
            repaintRectangle(prevX, prevY, mouseX, mouseY);
        }

        else if (currentTool.toolType == ToolFactory.ERASER_TOOL)
        {
            drawGraphics(dragGraphics, ToolFactory.createTool(ToolFactory.LINE_TOOL), prevX, prevY, mouseX, mouseY);
            repaintRectangle(prevX, prevY, mouseX, mouseY);
        }

        else if (currentTool.toolType == ToolFactory.AIR_BRUSH_TOOL)
        {
            drawGraphics(dragGraphics, ToolFactory.createTool(ToolFactory.AIR_BRUSH_TOOL), prevX, prevY, mouseX, mouseY);
            repaintRectangle(prevX, prevY, mouseX, mouseY);
        }
        else
        {
            repaintRectangle(startX, startY, prevX, prevY);     //repaint the rectangle that contains the previous version of the figure
            repaintRectangle(startX, startY, mouseX, mouseY);   //repaint the rectangle that contains the new version of the figure
        }

        prevX = mouseX;  // Save coordinates for the next call to mouseDragged or mouseReleased.
        prevY = mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {}

}
