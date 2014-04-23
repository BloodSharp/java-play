/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Class used to display the application's menu bar
public class MenuBar extends JMenuBar
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    public JMenu file, view, options, help;
    public JMenuItem quit, newFile, openFile, saveFile;
    public JMenuItem howToPaint, about;
    public JFileChooser fileChooser = null;


    /**************************************************************************************************************
     *****************************************************CONSTRUCTOR***********************************************
     **************************************************************************************************************/
    public MenuBar()
    {
        MenuOptionsHandler itemHandler = new MenuOptionsHandler();   //create the Menu ActionListener
        file = new JMenu("File");                                    //create the menu tabs and options
        help = new JMenu("Help");
        view = new JMenu("View");
        options = new JMenu("Tools");
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        quit = new JMenuItem("Exit");
        newFile.addActionListener(itemHandler);                      //add actionListeners
        openFile.addActionListener(itemHandler);
        saveFile.addActionListener(itemHandler);
        quit.addActionListener(itemHandler);
        file.add(newFile);                                           //add options to the menu
        file.add(openFile);
        file.add(saveFile);
        file.addSeparator();
        file.add(quit);
        howToPaint = new JMenuItem("Help...");
        howToPaint.addActionListener(itemHandler);
        about = new JMenuItem("About...");
        about.addActionListener(itemHandler);
        help.add(howToPaint);
        view.add(about);
        add(file);
        add(view);
        add(options);
        add(help);
    }

    /**************************************************************************************************************
     *****************************************************FILE METHODS*********************************************
     **************************************************************************************************************/

    public JFileChooser getFileChooser()
    {
        if (fileChooser ==null)
        {
            fileChooser = new JFileChooser();                        //create file chooser
            fileChooser.setFileFilter(new PNGFileFilter());         //set file extension to .png
        }
        return fileChooser;
    }

    public static BufferedImage getScreenShot(Component component)    //used to get the current image drawn on the screen
    {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());   // paints into image's Graphics
        return image;
    }

    /**************************************************************************************************************
     **************************************************ACTION EVENTS************************************************
     **************************************************************************************************************/
    private class MenuOptionsHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == quit)          //if Exit application
            {
                Main.paint.dispose();
                System.exit(0);
            }
            if (event.getSource() == newFile)       //if New File
            {

                BufferedImage bi = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);   //create new BufferedImage
                Main.paint.drawingPanel.clearImage(bi);                                         //clear current image
                Main.paint.drawingPanel.setImage(bi);                                           //set image to new blank image
            }
            if (event.getSource() == saveFile)      //if Save file
            {
                JFileChooser jFileChooser = getFileChooser();                                            //open file chooser
                int result = jFileChooser.showSaveDialog(Main.paint.drawingPanel);
                if (result==JFileChooser.APPROVE_OPTION )
                {
                    try
                    {
                        File selectedFile = jFileChooser.getSelectedFile();
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".png");      //get isSelected file
                        BufferedImage img = getScreenShot(Main.paint.drawingPanel);            //get current image screenshot
                        ImageIO.write(img, "png", selectedFile);                               //write the image to the isSelected file
                    } catch (IOException ioe)
                    {
                        JOptionPane.showMessageDialog(null, "Could not save the file");
                    }
                }
            }
            if (event.getSource() == openFile)       //if Open file
            {
                JFileChooser ch = getFileChooser();                                            //open file chooser
                int result = ch.showOpenDialog(Main.paint.drawingPanel);
                if (result==JFileChooser.APPROVE_OPTION )                                      //if OK
                {
                    try
                    {
                        Main.paint.drawingPanel.setOSImage(ImageIO.read(ch.getSelectedFile())); //set current image to isSelected image
                    } catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Could not open file");
                    }
                }
            }
            if (event.getSource() == howToPaint)       //if Help
            {
                JOptionPane.showMessageDialog(null, "Use the tool buttons on the left to paint components on the screen.\n" +
                        "Change the color of the components by selecting a color from the palette.\n" +
                        "Change the stroke of the components by moving the slider on the left.");
            }
            if (event.getSource() == about)         //if About
            {
                JOptionPane.showMessageDialog(null, "This application was made for the purpose of the Java Module WEEK 8 HIA assignment.\n\n" +
                        "Created by: Ivana Zuber\nCreated date: 03 March 2013");
            }
        }
    }

    /**************************************************************************************************************
     **************************************************FILE EXTENSION CLASS****************************************
     **************************************************************************************************************/
    private static class PNGFileFilter extends FileFilter
    {
        public boolean accept(File file)             //filer files to display
        {
            return file.getName().toLowerCase().endsWith(".png") || file.isDirectory();
        }

        public String getDescription()
        {
            return "PNG image  (*.png) ";
        }
    }

}