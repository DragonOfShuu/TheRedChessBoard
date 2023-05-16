package theredchessboard.utils;

//AWT Library
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// Util Library
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Swing Library
import javax.swing.JComponent;

// ImageIO Library
import javax.imageio.ImageIO;

// IO Library
import java.io.File;
import java.io.IOException;

/**
 * @since Oct 25th, 2022
 * @author Logan Cederlof
 * <h3> All Authors: </h3>
 * <ul>
 *  <li> Logan Cederlof - 2022 </li>
 *  <li> Natalie Watts - 2012 </li>
 *  <li> David D. Riley - 2004 </li>
 *  <li> Sharon DeReamer - 2008 </li>
 * </ul>
 */
public class JLComp extends JComponent {
    /* Object Classes */

    /**
     * An Oval Component that
     * can be added to another
     * component.
     * 
     * @since October, 2022
     * @author David D. Riley - April, 2004
     * @author Logan Cederlof
     */
    public static class Oval extends JComponent {
        /**
         * An oval; give the upper left 
         * coordinates, as well as the 
         * width and height!
         * @param x The x coordinate of the upper left corner.
         * @param y The y coordinate of the upper left corner.
         * @param w The width of the oval.
         * @param h The height of the oval.
         */
        public Oval(int x, int y, int w, int h)  {
            super();
            setBounds(x, y, w, h);
            setBackground(Color.black);
        }

        /**
         * An oval; give the upper left
         * coordinates, the width and 
         * height, as well as the color
         * of the oval!
         * @param x The x coordinate of the upper left corner.
         * @param y The y coordinate of the upper left corner.
         * @param w The width of the oval.
         * @param h The height of the oval.
         * @param backgroundColor The color of the oval.
         */
        public Oval(int x, int y, int w, int h, Color backgroundColor) {
            super();
            setBounds(x, y, w, h);
            setBackground(backgroundColor);
        }

        @Override
        public void paint(Graphics g)  {
            g.setColor( getBackground() );
            g.fillOval(0, 0, getWidth()-1, getHeight()-1);
            paintChildren(g);
        }
    }

    /**
     * A circle component that
     * can be added to another
     * component.
     * (x, y) is the center of the circle
     * 
     * @since October, 2022
     * @author Logan Cederlof
     */
    public static class Circle extends JComponent {

        private void circleInt(int x, int y, int r, Color backgroundColor) {
            setBounds(x-r, y-r, r*2, r*2);
            setBackground(backgroundColor);
        }

        /**
         * A circle; give the center 
         * coordinates of the circle,
         * and the radius!
         * @param x The x coordinate for the center.
         * @param y The y coordinate for the center.
         * @param r The radius of the circle.
         */
        public Circle(int x, int y, int r)  {
            super();
            circleInt(x, y, r, Color.BLACK);
        }

        /**
         * A circle; give the center
         * coordinates of the circle,
         * and the radius, as well as
         * the color!
         * @param x The x coordinate for the center.
         * @param y The y coordinate for the center.
         * @param r The radius of the circle.
         * @param backgroundColor The color of the circle.
         */
        public Circle(int x, int y, int r, Color backgroundColor) {
            super();
            circleInt(x, y, r, backgroundColor);
        }

        @Override
        public void paint(Graphics g)  {
            g.setColor( getBackground() );
            g.fillOval(0, 0, getWidth()-1, getHeight()-1);
            paintChildren(g);
        }
    }

    /**
     * A class for creating
     * multi pointed shapes.
     * 
     * @since October, 2022
     * @author Unkown
     * @author Logan Cederlof
     */
    @Deprecated
    public static class LegacyPolygon extends Container {
        // instance variables
        private int numSides;
        private int startX;
        private int startY;
        private int size;
        private double startAngle;
        private Color myColor;
        /**
         * Constructor for objects of class Polygon
         */
        public LegacyPolygon(int x, int y, int size, int numSides, double startAngle, Color backgroundColor) {
            // initialise instance variables
            setBounds (0, 0, 1200, 700);
            this.numSides = numSides;
            startX = x;
            startY = y;
            this.size = size;
            this.startAngle = startAngle;
            myColor = backgroundColor;
            makePolygon (backgroundColor);
        }

        private void makePolygon(Color color) {
            int newX = startX;
            int newY = startY;
            double deltaAngle = (Math.PI * (numSides - 2.0)) / numSides;
            double newAngle = deltaAngle + startAngle;
            for (int counter = 0; counter < numSides; ) {
                int deltaX = (int) (Math.cos (newAngle) * size);
                int deltaY = (int) (Math.sin (newAngle) * size);
                Line line = new Line (newX, newY, newX + deltaX, newY - deltaY, color);
                line.setBackground(myColor);
                this.add (line, 0);
                newX = newX + deltaX;
                newY = newY - deltaY;
                counter++;
                int ratio = counter%2==0?0:1;
                newAngle = (deltaAngle * (counter + 1)) + Math.PI * ratio + startAngle;
            }
        }
    }

    /**
     * A modern class for
     * multi-pointed shapes.
     * 
     * @since November, 2022
     * @author Logan Cederlof
     */
    public static class Polygon extends Container {
        // instance variables
        private int numSides;
        private int startX;
        private int startY;

        private List<Integer> xPoints;
        private List<Integer> yPoints;

        private int size;
        private double startAngle;

        /**
         * Assembles polygon with vague information.
         * @param x The furthest left of the polygon.
         * @param y The furthest right of the polygon.
         * @param size The size of the polygon.
         * @param numSides The number of sides the polygon has.
         * @param startAngle The angle of the polygon.
         * @param backgroundColor The color of the polygon.
         */
        public Polygon(int x, int y, int size, int numSides, double startAngle, Color backgroundColor) {
            // initialise instance variables
            // setBounds (0, 0, 1200, 700);
            setBounds(x, y, size, size);

            this.numSides = numSides;
            startX = x;
            startY = y;
            this.size = size;
            this.startAngle = startAngle;
            setBackground ( backgroundColor );
        }

        /**
         * Assembles the polygon with direction information
         * @param xPoints The x position of the points.
         * @param yPoints The y position of the points.
         * @param backgroundColor The color of the polygon.
         */
        public Polygon(Integer[] xPoints, Integer[] yPoints, Color backgroundColor) {
            this.xPoints = Arrays.asList(xPoints);
            this.yPoints = Arrays.asList(yPoints);
            setBackground ( backgroundColor );
            makePolygon();
        }

        /**
         * Assembles the vague polygon using the
         * given information.
         */
        private void makePolygon() {
            int newX = startX;
            int newY = startY;

            double deltaAngle = (Math.PI * (numSides - 2.0)) / numSides;
            double newAngle = deltaAngle + startAngle;

            this.xPoints = new ArrayList<Integer>();
            this.yPoints = new ArrayList<Integer>();

            for (int counter = 0; counter < numSides; ) {
                int deltaX = (int) (Math.cos (newAngle) * size);
                int deltaY = (int) (Math.sin (newAngle) * size);

                this.xPoints.add(newX);
                this.yPoints.add(newY);

                newX = newX + deltaX;
                newY = newY - deltaY;
                counter++;
                int ratio = (counter % 2 == 0) ?  0  :  1 ;
                newAngle = (deltaAngle * (counter + 1)) + Math.PI * ratio + startAngle;
            }
        }

        @Override
        public void paint(Graphics g) {
            g.setColor( getBackground() );

            // https://stackoverflow.com/questions/960431/how-can-i-convert-listinteger-to-int-in-java
            int[] xArray = xPoints.stream().mapToInt(i->i).toArray();
            int[] yArray = yPoints.stream().mapToInt(i->i).toArray();

            g.fillPolygon(xArray, yArray, xPoints.size());
        }
    }

    /**
     * A Triangle Component that
     * can be added to another
     * component.
     * 
     * @since October, 2022 
     * @author Sharon DeReamer - November, 2008
     * @author Logan Cederlof
     */
    public static class Triangle extends JComponent  {

        private int [] theXs;
        private int [] theYs;
          
        /** 
         *  bounds are set to x = x, y = y, width = w and height = h
         *  and  color is set to black
         *  and  three corners of the triangle are set to
         *  (x, y), (x + w, y + h), and (x + w/2, y)
         */
        public Triangle (int x, int y, int w, int h, Color color)
        {
            super ();
            setBounds (x, y, w, h);
            setBackground( color );
            
            theXs = new int [3];
            theXs[0] = 0;
            theXs[1] =  w;
            theXs[2] = w / 2;
            
            theYs = new int [3]; 
            theYs[0] = h;
            theYs[1] =  h;
            theYs[2] = 0;  
        }
    
        /** 
         * <strong>Creates the triangle point by point.</strong>
         * <p>
         * For example, x1 and y1 will be the first
         * point on the triangle (x1, y1).
         * </p>
         * @param color The color of the triangle
         */
        public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color)  {
            super();
            int highestX = highest (x1, x2, x3);
            int highestY = highest (y1, y2, y3);
            
            int lowestX = lowest (x1, x2, x3);
            int lowestY = lowest (y1, y2, y3);
            
            setBounds(lowestX, lowestY, highestX - lowestX, highestY - lowestY);
            setBackground(color);
            
            theXs = new int [3]; 
            theXs[0] = x1 - lowestX;
            theXs[1] =  x2 - lowestX;
            theXs[2] = x3 - lowestX;
            
            theYs = new int [3]; 
            theYs[0] = y1 - lowestY;
            theYs[1] =  y2 - lowestY;
            theYs[2] = y3 - lowestY;
        }
        
        /**
         * Takes three ints
         * @return the lowest of the three
         */
         private int lowest (int i1, int i2, int i3)
         {
            int lowest = i1;
            if (lowest > i2 || lowest > i3)
            {
                lowest = i2;
                if (lowest > i3)
                {
                    lowest = i3;
                }
            }
            return lowest;
        }
         
        /**
         * Takes three ints 
         * @return the highest of the three
         */
         private int highest (int i1, int i2, int i3)
         {
            int highest = i1;
            if (highest < i2 || highest < i3)
            {
                highest = i2;
                if (highest < i3)
                {
                    highest = i3;
                }
            }
            return highest;
        }
    
        /** 
         *  <strong>Draws a filled Triangle</strong>
         *  <p>
         *  The corners of the triangle are determined the the
         *  array that holds the x values and the array that
         *  holds the y values of the three points.
         *  </p>
         */
        public void paint(Graphics g)  {
            g.setColor( getBackground() );
            g.fillPolygon(theXs, theYs, 3);
       }
    
    }

    /**
     * A Line Component that
     * can be added to another
     * component.
     * 
     * @since October, 2022
     * @author Unknown
     * @author Logan Cederlof
     */
    public static class Line extends JComponent  {
        private boolean isMajorDiagonal;
        
        /**
         * Draw a basic line!
         * @param x1 The x coordinate of the first point.
         * @param y1 The y coordinate of the first point.
         * @param x2 The x coordinate of the second point.
         * @param y2 The y coordinate of the second point.
         * @param color The color of the line.
         */
        public Line(int x1, int y1, int x2, int y2, Color color)  {
            super();
            setBounds(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2)+1, Math.abs(y1-y2)+1);
            setBackground(color);
            isMajorDiagonal = (x1==Math.min(x1,x2) && y1==Math.min(y1,y2)) || (x2==Math.min(x1,x2) && y2==Math.min(y1,y2));         
        }
    
        public void paint(Graphics g)  {
            g.setColor( getBackground() );
            if (isMajorDiagonal)
                g.drawLine(0, 0, getWidth()-1, getHeight()-1);
            else
                g.drawLine(0, getHeight()-1, getWidth()-1, 0);
       }
    }

    /**
     * A Rectangle Component that
     * can be added to another
     * component.
     * 
     * @since October, 2022
     * @author Natalie Watts, 2012
     * @author Logan Cederlof
     */
    public static class Rectangle extends JComponent  {

        /**
         * <strong>Make a basic rectangle!</strong>
         * <p>
         *  x and y are the coordinates of the upper left
         *  corner.
         * </p>
         * @param x The x coordinate of the upper left corner.
         * @param y The y coordinate of the upper left corner.
         * @param w The width of the rectangle.
         * @param h The height of the rectangle.
         */
        public Rectangle(int x, int y, int w, int h)  {
            super();
            setBounds(x, y, w, h);
            setBackground(Color.black);
        }
        
        /**    
         * <strong>Make a basic rectangle!</strong>
         * <p>
         *  x and y are the coordinates of the upper left
         *  corner.
         * </p>
         * @param x The x coordinate of the upper left corner.
         * @param y The y coordinate of the upper left corner.
         * @param w The width of the rectangle.
         * @param h The height of the rectangle.
         * @param c The color of the rectangle.
         */
        public Rectangle(int x, int y, int w, int h, Color c)  {
            super();
            setBounds(x, y, w, h);
            setBackground(c);
        }
        
        /**
         * @return The color of the rectangle.
         */
        public Color getContainerColor()
        {
            return this.getParent().getBackground();
        }
        
        public int getContainerX()
        {
            return this.getParent().getX();
        }
        
        public int getContainerY()
        {
            return this.getParent().getY();
        }
        
        public int getContainerWidth()
        {
            return this.getParent().getWidth();
        }
        
        public int getContainerHeight()
        {
            return this.getParent().getHeight();
        }
        
        public void setContainerBackground(Color c)
        {
            this.getParent().setBackground(c);
        }
        
        public void paint(Graphics g)  {
            g.setColor( getBackground() );
            g.fillRect(0, 0, getWidth()-1, getHeight()-1);
            paintChildren(g);
        }
    }

    /**
     * An Image Panel Component
     * to be added to another
     * component.
     * 
     * @since October, 2022
     * @author Unknown
     * @author Logan Cederlof
     */
    public static class ImagePanel extends JComponent {
        private Image img;

        /**
         * A component for an image!
         * @param img The image to display.
         */
        public ImagePanel(Image img)
        {
          this.img = img;
          Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
          setSize(size);
        }
    
        @Override
        public void paint(Graphics g) {
          g.drawImage(img, 0, 0, null);
        }
    }

    public static class ClickableImage extends JComponent implements MouseListener {
        protected Image content;
      
    // Constructor methods
        public ClickableImage()  {
            super();
            setBounds(0, 0, 10, 10);
            addMouseListener(this);
        } 
    
        public ClickableImage(int x, int y, int w, int h)  {
            super();
            setBounds(x, y, w, h);
            addMouseListener(this);
        } 
    
        public ClickableImage(int x, int y, int w, int h, String s)  {
            super();
            setBounds(x, y, w, h);
            setImage(s);
            addMouseListener(this);
        } 
    
    // ----- will set the image to a new picture named s -------------------------------    
        public void setImage(String s)  {
            java.net.URL url = getClass().getResource(s);  
            if (url == null)   {
                    url = getClass().getResource("/"+s);
                    if (url == null)
                       try {  
                            content = ImageIO.read(new File(s));
                        } catch(IOException ioe) {
                            ioe.printStackTrace();
                        }
                    else
                    content = getToolkit().getImage(url);
                    } else
                    content = getToolkit().getImage(url);
        }
        public void paint(Graphics g)  {
            g.drawImage(content, 0, 0, getWidth(), getHeight(), this);
            paintChildren(g);
        }
    
        public void mouseClicked(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }

    /*
     * ----------------------------------------
     * Finally, the actual JLComponent methods.
     * ----------------------------------------
     */

     /**
      * The basic initialization of 
      * the JLComponent.
      * @param x The x coordinate of the upper left corner.
      * @param y The y coordinate of the upper left corner.
      * @param width The width of the component.
      * @param height The height of the component.
      * @param background The background color of the component.
      */
    private void JLComponentInit( int x, int y, int width, int height, Color background ) {
        super.setLayout(null);
        setBounds(x, y, width, height);
        setBackground( background );
    }

    /**
     * The JLComponent! Built in
     * container and shapes!
     * @param x The x coordinate of the upper left corner.
     * @param y The y coordinate of the upper left corner.
     * @param width The width of the component.
     * @param height The height of the component.
     */
    public JLComp( int x, int y, int width, int height ) {
        super();
        JLComponentInit(x, y, width, height, Color.white);
    }

    /**
     * The JLComponent! Built in
     * container and shapes!
     * @param x The x coordinate of the upper left corner.
     * @param y The y coordinate of the upper left corner.
     * @param width The width of the component.
     * @param height The height of the component.
     * @param background The background color of the component
     */
    public JLComp( int x, int y, int width, int height, Color background ) {
        super();
        JLComponentInit(x, y, width, height, background);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth()-1, getHeight()-1);
        paintChildren(g);
    }
}
