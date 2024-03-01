import java.awt.*;
import java.awt.geom.*;
import part01.MyFinishWindow2;

public class GeneralPathRectangle extends Frame {
    
    GeneralPathRectangle()
    {
        //nutup jendela saat klik close
        addWindowListener(new MyFinishWindow2());
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //ini biar antialiasing -> garis lebih halus
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 50;
        int y = 50;
        int width = 200;
        int height = 100;
        int arcWidth = 30;
        int arcHeight = 30;
        
        // Create a GeneralPath object
        GeneralPath path = new GeneralPath();
        
        // Move to the starting point
        path.moveTo(x + arcWidth, y);
        
        // Draw the top line
        path.lineTo(x + width - arcWidth, y);
        
        // Draw the top-right corner
        path.quadTo(x + width, y, x + width, y + arcHeight);
        
        // Draw the right line
        path.lineTo(x + width, y + height - arcHeight);
        
        // Draw the bottom-right corner
        path.quadTo(x + width, y + height, x + width - arcWidth, y + height);
        
        // Draw the bottom line
        path.lineTo(x + arcWidth, y + height);
        
        // Draw the bottom-left corner
        path.quadTo(x, y + height, x, y + height - arcHeight);
        
        // Draw the left line
        path.lineTo(x, y + arcHeight);
        
        // Draw the top-left corner
        path.quadTo(x, y, x + arcWidth, y);
        
        // Close the path
        path.closePath();
        
        // Render the path
        g2.draw(path);

        // Memanggil metode drawSimpleCoordinateSystem() untuk menggambar garis koordinat
        drawSimpleCoordinateSystem(300, 200, g2);
    }

  /**
  * Draws a coordinate system (according to the window coordinates).
  *
  * @param xmax     x-coordinate to which the x-axis should extend.
  * @param ymax     y-coordinate to which the y-axis should extend.
  * @param g2d      Graphics2D object for drawing.
  */
  public void drawSimpleCoordinateSystem(int xmax, int ymax,
                                                Graphics2D g2d)
  {
    int xOffset = 30;
    int yOffset = 50;
    int step = 20;
    String s;
    //Remember the actual font.
    Font fo = g2d.getFont();
    //Use a small font.
    g2d.setFont(new Font("sansserif",Font.PLAIN,9));
    //x-axis.
    g2d.drawLine(xOffset,yOffset,xmax,yOffset);
    //Marks and labels for the x-axis.
    for (int i=xOffset+step; i<=xmax; i=i+step)
    {
      g2d.drawLine(i,yOffset-2,i,yOffset+2);
      g2d.drawString(String.valueOf(i),i-7,yOffset-7);
    }

    //y-axis.
    g2d.drawLine(xOffset,yOffset,xOffset,ymax);

    //Marks and labels for the y-axis.
    s="  "; //for indention of numbers < 100
    for (int i=yOffset+step; i<=ymax; i=i+step)
    {
      g2d.drawLine(xOffset-2,i,xOffset+2,i);
      if (i>99){s="";}
      g2d.drawString(s+String.valueOf(i),xOffset-25,i+5);
    }

    //Reset to the original font.
    g2d.setFont(fo);
  }
    
    public static void main(String[] args) {
        Frame frame = new GeneralPathRectangle();
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
