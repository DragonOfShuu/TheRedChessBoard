package theredchessboard.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ClickableBackgroundImage extends JLComp.Rectangle implements MouseListener {
    protected Image content;
      
    // Constructor methods
        // public ClickableImage()  {
        //     super(0, 0, 10, 10);
        //     setBounds(0, 0, 10, 10);
        //     addMouseListener(this);
        // } 
    
        public ClickableBackgroundImage(int x, int y, int w, int h, Color color)  {
            super(x, y, w, h, color);
            addMouseListener(this);
        } 
    
        // public ClickableImage(int x, int y, int w, int h, String s)  {
        //     super();
        //     setBounds(x, y, w, h);
        //     setImage(s);
        //     addMouseListener(this);
        // } 
    
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
