/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

//Class which serves for creating the appropriate tool class
public class ToolFactory
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    private static Tool brushTool;                    //variables to hold different tool class instances
    private static Tool pencillTool;
    private static Tool eraserTool;
    private static Tool ovalTool;
    private static Tool lineTool;
    private static Tool rectangleTool;
    private static Tool fillerTool;
    private static Tool roundRectangleTool;
    private static Tool filledRectangleTool;
    private static Tool filledOvalTool;
    private static Tool filledRoundRectangleTool;
    private static Tool airBrushTool;
    private static Tool currentTool;

    public static final int BRUSH_TOOL = 0;             //static constant variables used to differentiate the tool classes
    public static final int PENCILL_TOOL= 1;
    public static final int ERASER_TOOL= 2;
    public static final int FILLER_TOOL= 3;
    public static final int ROUND_RECTANGLE_TOOL = 4;
    public static final int OVAL_TOOL= 5;
    public static final int RECTANGLE_TOOL = 6;
    public static final int LINE_TOOL= 7;
    public static final int AIR_BRUSH_TOOL= 8;
    public static final int FILLED_RECTANGLE_TOOL= 9;
    public static final int FILLED_OVAL_TOOL= 10;
    public static final int FILLED_ROUND_RECTANGLE_TOOL= 11;

    /**************************************************************************************************************
     *************************************************FACTORY METHOD***********************************************
     **************************************************************************************************************/
    /**
     * Creates a new instance of the Tool (depending on the given parameter), passing in the tool type parameter
     * Sets the  currentTool view to that of the newly created class
     * @param toolTYpe  String parameter defining the type of the tool that is to be created
     * @return   currentTool
     */
    public static Tool createTool(int toolTYpe)
    {
        switch (toolTYpe)
        {
            case 0 :   if (brushTool == null  )                //if class is null
                brushTool =  new Tool(BRUSH_TOOL);             //create new class instance
                currentTool = brushTool;                       //set currentTool to that class instance
                break;
            case 1 :   if (pencillTool == null  )
                pencillTool =  new Tool(PENCILL_TOOL);
                currentTool = pencillTool;
                break;
            case 2 :   if (eraserTool == null)
                eraserTool =  new Tool(ERASER_TOOL);
                currentTool = eraserTool;
                break;
            case 3 :   if (fillerTool == null)
                fillerTool =  new Tool(FILLER_TOOL);
                currentTool = fillerTool;
                break;
            case 4 :   if (roundRectangleTool == null)
                roundRectangleTool =  new Tool(ROUND_RECTANGLE_TOOL);
                currentTool = roundRectangleTool;
                break;
            case 5 :   if (ovalTool == null)
                ovalTool =  new Tool(OVAL_TOOL);
                currentTool = ovalTool;
                break;
            case 6 :   if (rectangleTool == null)
                rectangleTool =  new Tool(RECTANGLE_TOOL);
                currentTool = rectangleTool;
                break;
            case 7 :   if (lineTool == null)
                lineTool =  new Tool(LINE_TOOL);
                currentTool = lineTool;
                break;
            case 8 :   if (airBrushTool == null)
                airBrushTool =  new Tool(AIR_BRUSH_TOOL);
                currentTool = airBrushTool;
                break;
            case 9 :   if (filledRectangleTool == null)
                filledRectangleTool =  new Tool(FILLED_RECTANGLE_TOOL);
                currentTool = filledRectangleTool;
                break;
            case 10 :    if (filledOvalTool == null)
                filledOvalTool =  new Tool(FILLED_OVAL_TOOL);
                currentTool = filledOvalTool;
                break;
            case 11 :   if (filledRoundRectangleTool == null)
                filledRoundRectangleTool =  new Tool(FILLED_ROUND_RECTANGLE_TOOL);
                currentTool = filledRoundRectangleTool;
                break;
        }
        return currentTool;
    }
}
