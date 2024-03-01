import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RealTimeHeartAnimation extends JPanel implements ActionListener {
    private int x = 0; // Initial koor- x 
    private int y = 100; // Fixed koor- y
    private int direction = 1; // Initial direction of movement
    private int heartbeatCount = 0; // detak jantung
    private Timer timer;

    public RealTimeHeartAnimation() {
        timer = new Timer(50, this); // update animasi setiap 50 mili sec
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // moving jantung
        drawHeart(g, x, y);
    }

    private void drawHeart(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //bentuk jantung
        int[] xPoints = {x, x + 15, x + 30, x + 45, x + 60, x + 75, x + 90};
        int[] yPoints = {y + 20, y, y + 40, y - 20, y + 20, y + 40, y};
        int nPoints = 7;

        // simbol heart beat jantung
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolyline(xPoints, yPoints, nPoints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update koor- x oanimasi jantung
        x += direction;

        // simulasi hear tbeat rhythm EKG
        if (heartbeatCount < 10) {
            y -= 2;
            heartbeatCount++;
        } else if (heartbeatCount < 20) {
            y += 2;
            heartbeatCount++;
        } else {
            // Reset heartbeat count setelah 1 cycle
            heartbeatCount = 0;
        }

        // Reverse direction ketika sampai tepi gui
        if (x >= getWidth() - 90 || x <= 0) {
            direction *= -1;
        }

        // Repaint display updated animasi
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Real-Time Heartbeat Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);

        RealTimeHeartAnimation animation = new RealTimeHeartAnimation();
        frame.add(animation);

        frame.setVisible(true);
    }
}
