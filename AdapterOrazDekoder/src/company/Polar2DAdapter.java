package company;

public class Polar2DAdapter implements IPolar2D, IVector{

    Vector2D srcVector = null;

    public Polar2DAdapter(Vector2D obj){
        this.srcVector = obj;
    }

    @Override
    public double getAngle() {
        double[] tab = getComponents();
        return Math.toDegrees(Math.atan2(tab[1], tab[0]));
    }

    @Override
    public double abs() {
        return srcVector.abs();
    }

    @Override
    public double cdot(IVector param) {
        return srcVector.cdot(param);
    }

    @Override
    public double[] getComponents() {
        return srcVector.getComponents();
    }
}
