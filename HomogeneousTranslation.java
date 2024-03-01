import java.awt.geom.*;

public class HomogeneousTranslation {
    public static void main(String[] args) {
        // Definetranslasi vector
        double dx = 3;
        double dy = 2;

        // Create AffineTransform for traslasi
        AffineTransform transform = new AffineTransform();
        transform.translate(dx, dy);

        // Print transformation matrix
        double[] matrix = new double[6];
        transform.getMatrix(matrix);
        System.out.println("Transformation Matrix:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i * 3 + j] + " ");
            }
            System.out.println();
        }

        // Test the translation by applying it to a point
        Point2D.Double point = new Point2D.Double(1, 1);
        transform.transform(point, point);
        System.out.println("Translated Point: (" + point.getX() + ", " + point.getY() + ")");
    }
}