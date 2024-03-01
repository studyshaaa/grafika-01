import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class SolarSystemAnimation extends JPanel {
    private double sunRadius = 20; // Radius nya matahari
    private double planetRadius = 10; // Radius planet
    private double orbitRadius = 200; // Radius orbit planet
    private double planetAngle = 0; // Initial angle planet
    private double planetRotationSpeed = 365; // jumlah rotasi planet di orbit
    private double orbitAngle = 0; // Initial angle orbit planet

    public SolarSystemAnimation() {
        // mulai loop animasi
        Timer timer = new Timer(50, e -> {
            // Update posisi planet
            orbitAngle += 2 * Math.PI / 360; // Increment orbit planet ->(simulate movement)
            planetAngle += 2 * Math.PI / planetRotationSpeed; // Increment angle orbit planet

            // Repaint the panel to reflect the updated positions
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // pindah origin jadi center panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2d.translate(centerX, centerY);

        // matahari
        g2d.setColor(Color.YELLOW);
        g2d.fill(new Ellipse2D.Double(-sunRadius, -sunRadius, 2 * sunRadius, 2 * sunRadius));

        // posisi planet
        double planetX = orbitRadius * Math.cos(orbitAngle);
        double planetY = orbitRadius * Math.sin(orbitAngle);

        // apply rotasi planet
        AffineTransform transform = AffineTransform.getRotateInstance(planetAngle, planetX, planetY);
        Shape planetShape = transform.createTransformedShape(new Ellipse2D.Double(planetX - planetRadius, planetY - planetRadius, 2 * planetRadius, 2 * planetRadius));

        // Draw planet
        g2d.setColor(Color.BLUE);
        g2d.fill(planetShape);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        SolarSystemAnimation animation = new SolarSystemAnimation();
        frame.add(animation);

        frame.setVisible(true);
    }
}
