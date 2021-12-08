package company;

public interface IVector {
    double abs();
    double cdot(IVector param);
    double getAngle();
    int[] getComponents();
    double distanceBetween(IVector param);
    void moveBy(int n, int m);
}
