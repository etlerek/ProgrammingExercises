package company;

public class Vector3DInheritance extends Vector2D{

    private double z;

    public Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z = z;
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


    @Override
    public double abs() {
        return Math.sqrt(super.getComponents()[0]*super.getComponents()[0]+super.getComponents()[1]*super.getComponents()[1]+z*z);
    }

    @Override
    public double cdot(IVector param) {
        double[] paramVector = param.getComponents();
        double[] thisVector = getComponents();
        if (thisVector.length != paramVector.length)
            return thisVector[0]*paramVector[0] + thisVector[1]*paramVector[1]+ thisVector[2]*0;
        return thisVector[0]*paramVector[0] + thisVector[1]*paramVector[1]+ thisVector[2]*paramVector[2];
    }

    @Override
    public double[] getComponents() {
        return new double[]{super.getComponents()[0], super.getComponents()[1], z};
    }

    public IVector getSrcV(){
        return (Vector2D)this;
    }
}
