import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class LetterMorphing extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int NUM_POINTS = 4; // Number of control points

    private Point2D.Double[] letterYPoints;
    private Point2D.Double[] letterAPoints;
    private Point2D.Double[] morphedPoints;

    public LetterMorphing() {
        // Define point huruf Y
        letterYPoints = new Point2D.Double[NUM_POINTS];
        letterYPoints[0] = new Point2D.Double(150, 100);
        letterYPoints[1] = new Point2D.Double(250, 200);
        letterYPoints[2] = new Point2D.Double(200, 200);
        letterYPoints[3] = new Point2D.Double(200, 300);

        // Define point huruf A
        letterAPoints = new Point2D.Double[NUM_POINTS];
        letterAPoints[0] = new Point2D.Double(300, 100);
        letterAPoints[1] = new Point2D.Double(350, 300);
        letterAPoints[2] = new Point2D.Double(400, 100);
        letterAPoints[3] = new Point2D.Double(325, 200);

        // Initialize morphed points
        morphedPoints = new Point2D.Double[NUM_POINTS];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Interpolate huruf Y dan A
        for (int i = 0; i < NUM_POINTS; i++) {
            double x = interpolate(letterYPoints[i].getX(), letterAPoints[i].getX(), 0.5);
            double y = interpolate(letterYPoints[i].getY(), letterAPoints[i].getY(), 0.5);
            morphedPoints[i] = new Point2D.Double(x, y);
        }

        // Draw huruf Y
        drawLetter(g2d, letterYPoints);

        // Draw huruf A
        // drawLetter(g2d, letterAPoints);

        // Draw the morphed letter
        drawLetter(g2d, morphedPoints);
    }

    private void drawLetter(Graphics2D g2d, Point2D.Double[] points) {
        GeneralPath path = new GeneralPath();
        path.moveTo(points[0].getX(), points[0].getY());
        for (int i = 1; i < points.length; i++) {
            path.lineTo(points[i].getX(), points[i].getY());
        }
        g2d.draw(path);
    }

    private double interpolate(double start, double end, double fraction) {
        return start + (end - start) * fraction;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Letter Morphing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        LetterMorphing panel = new LetterMorphing();
        frame.add(panel);
        frame.setVisible(true);
    }
}
