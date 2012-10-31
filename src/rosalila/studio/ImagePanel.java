/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.studio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    public BufferedImage original_image;
    public BufferedImage scaled_image;
    public int x;
    public int y;
    public double scale;
    Graphics g;

    public ImagePanel() {
        x=0;
        y=0;
        scale=1;
       try {                
          original_image = ImageIO.read(new File("sprites/1.png"));
       } catch (IOException ex) {
            // handle exception...
       }
       
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myScale();
        g.drawImage(scaled_image, x-scaled_image.getWidth()/2,y-scaled_image.getHeight()/2, null); // see javadoc for more info on the parameters            
    }
    
    void myScale()
    {
        scaled_image = new BufferedImage((int)((double)original_image.getWidth()*scale),(int)((double)original_image.getHeight()*scale), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaled_image.createGraphics();
        AffineTransform xform = AffineTransform.getScaleInstance(scale, scale);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.drawImage(original_image, xform, null);
        graphics2D.dispose();
    }

}