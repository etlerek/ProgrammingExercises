package company;

public class Polar2DInheritence extends Vector2D{

    public Polar2DInheritence(double x, double y) {
        super(x, y);
    }

    public double getAngle() {
        double[] tab = getComponents();
        return Math.toDegrees(Math.atan2(tab[1], tab[0]));
    }
}
