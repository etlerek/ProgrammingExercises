package company;

/*
    METODY I ZMIENNE JAK W DIAGRAMIE
*/

public class Vector2D implements IVector {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double abs() {
        return Math.sqrt(x*x+y*y);
    }

    @Override
    public double cdot(IVector param) {
        double[] tab = param.getComponents();
        return x*tab[0] + y*tab[1];
    }

    @Override
    public double[] getComponents() {
        return new double[]{x, y};
    }
}
