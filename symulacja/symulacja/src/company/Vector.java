package company;

import java.util.Random;

public class Vector implements IVector{

    Random random = new Random();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected int x;
    protected int y;
    protected int dy;
    protected int dx;

    public Vector(Vector vector){
        int[] xy = vector.getComponents();
        this.x = xy[0];
        this.y = xy[1];
        int[] dxy = vector.getVectors();
        this.dx = dxy[0];
        this.dy = dxy[1];
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = random.nextInt(5)-2;
        this.dy = random.nextInt(5)-2;
    }

    @Override
    public double abs() {
        return Math.sqrt(x*x+y*y);
    }

    @Override
    public double cdot(IVector param) {
        int[] tab = param.getComponents();
        return x*tab[0] + y*tab[1];
    }

    @Override
    public int[] getComponents() {
        return new int[]{x, y};
    }

    public int[] getVectors() {
        return new int[]{dx, dy};
    }

    @Override
    public double distanceBetween(IVector param) {
        int[] tab = param.getComponents();
        return Math.sqrt((x-tab[0])*(x-tab[0]) + (y-tab[1])*(y-tab[1]));
    }

    @Override
    public void moveBy(int n, int m) {
        if(this.x <= 0 || this.x >= n)
            if(random.nextInt(100)<50)
                dx *= -1;
        if(dx == 0)
            dx += 1;
        if(this.y <= 0 || this.y >= n)
            if(random.nextInt(100)<50)
                dy *= -1;
        if(dy == 0)
            dy += 1;
        this.x += dx;
        this.y += dy;
    }

    @Override
    public double getAngle() {
        int[] tab = getComponents();
        return Math.toDegrees(Math.atan2(tab[1], tab[0]));
    }
}
