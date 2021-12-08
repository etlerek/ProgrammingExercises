package company;

public class Vector3DDecorator implements IVector {

    private IVector srcVector;
    private double z;

    Vector3DDecorator(double x, double y, double z){
        this.srcVector = new Vector2D(x, y);
        this.z = z;
    }

    @Override
    public double abs() {
        return Math.sqrt(srcVector.getComponents()[0]*srcVector.getComponents()[0]+srcVector.getComponents()[1]*srcVector.getComponents()[1]+z*z);
    }

    @Override
    public double cdot(IVector param) {
        double[] paramVector = param.getComponents();
        double[] thisVector = getComponents();
        if (thisVector.length != paramVector.length)
            return thisVector[0]*paramVector[0] + thisVector[1]*paramVector[1];
        return thisVector[0]*paramVector[0] + thisVector[1]*paramVector[1]+ thisVector[2]*paramVector[2];
    }

    @Override
    public double[] getComponents() {
        return new double[]{srcVector.getComponents()[0], srcVector.getComponents()[1], z};
    }

    public Vector3DInheritance cross(IVector param){
        double x,y,z;
        if(getComponents().length == param.getComponents().length){
            x = getComponents()[1]*param.getComponents()[2] - getComponents()[2]*param.getComponents()[1];
            y = getComponents()[2]*param.getComponents()[0] - getComponents()[0]*param.getComponents()[2];
            z = getComponents()[0]*param.getComponents()[1] - getComponents()[1]*param.getComponents()[0];
        }

        else{
            x = getComponents()[1]*0 - getComponents()[2]*param.getComponents()[1];
            y = getComponents()[2]*param.getComponents()[0] - getComponents()[0]*0;
            z = getComponents()[0]*param.getComponents()[1] - getComponents()[1]*param.getComponents()[0];
        }

        return new Vector3DInheritance(x, y, z);
    }

    public IVector getSrcV(){
        return srcVector;
    }
}
