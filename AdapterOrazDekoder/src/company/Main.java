package company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Vector2D vector2D = new Vector2D(-2,2);

        Polar2DInheritence polar2DInheritence = new Polar2DInheritence(5, 10);
        Polar2DInheritence polar2DInheritence2 = new Polar2DInheritence(1, 12);
        Polar2DAdapter polar2DAdapter = new Polar2DAdapter(vector2D);

        System.out.println("Współrzędne kartezjańskie wektora polar2DInheritence: " + Arrays.toString(polar2DInheritence.getComponents()));
        System.out.println("Współrzędne biegunowe wektora polar2DInheritence: " + polar2DInheritence.getAngle() + " " + polar2DInheritence.abs());
        System.out.println();
        System.out.println("Współrzędne kartezjańskie wektora polar2DInheritence2: " + Arrays.toString(polar2DInheritence2.getComponents()));
        System.out.println("Współrzędne biegunowe wektora polar2DInheritence2: " + polar2DInheritence2.getAngle() + " " + polar2DInheritence2.abs());
        System.out.println();
        System.out.println("Współrzędne kartezjańskie wektora polar2DAdapter: " + Arrays.toString(polar2DAdapter.getComponents()));
        System.out.println("Współrzędne biegunowe wektora polar2DAdapter: " + polar2DAdapter.getAngle() + ", " + polar2DAdapter.abs());
        System.out.println();
        System.out.println(("polar2DAdapter * polar2DInheritence: " + polar2DAdapter.cdot(polar2DInheritence)));
        System.out.println(("polar2DAdapter * polar2DInheritence2: " + polar2DAdapter.cdot(polar2DInheritence2)));
        System.out.println(("polar2DInhertence2 * polar2DInheritence: " + polar2DInheritence2.cdot(polar2DInheritence)));
        System.out.println();

        Vector3DInheritance vector3DInheritance = new Vector3DInheritance(5, 6, 10);
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(12,-5,  3);

        System.out.println("Współrzędne kartezjańskie wektora vector3DInheritance: " + Arrays.toString(vector3DInheritance.getComponents()));
        System.out.println("Współrzędne kartezjańskie wektora vector3DDecorator: " + Arrays.toString(vector3DDecorator.getComponents()));
        System.out.println();
        System.out.println("vector3DIngeritance x vector3DDecorator: " + Arrays.toString((vector3DInheritance.cross(vector3DDecorator)).getComponents()));
        System.out.println("vector3DInheritence x polar2DAdapter: " + Arrays.toString((vector3DInheritance.cross(polar2DAdapter)).getComponents()));
        System.out.println("vector3DDecorator x polar2DAdapter: " + Arrays.toString((vector3DDecorator.cross(polar2DAdapter)).getComponents()));


      /*  System.out.println(vector2D.abs());
        System.out.println(polar2DAdapter.abs());
        System.out.println(polar2DInheritence.abs());
        System.out.println();
        System.out.println(Arrays.toString(vector2D.getComponents()));
        System.out.println(Arrays.toString(polar2DAdapter.getComponents()));
        System.out.println(Arrays.toString(polar2DInheritence.getComponents()));
        System.out.println();
        System.out.println();
        System.out.println(polar2DAdapter.getAngle());
        System.out.println(polar2DInheritence.getAngle());
        System.out.println();
        System.out.println(vector3DInheritance.abs());
        System.out.println(vector3DInheritance.cdot(vector2D));
        System.out.println(Arrays.toString(vector3DInheritance.getComponents()));
        System.out.println(vector3DInheritance.getSrcV());
        System.out.println();
        System.out.println(vector3DDecorator.abs());
        System.out.println(vector3DDecorator.cdot(vector2D));
        System.out.println(Arrays.toString(vector3DDecorator.getComponents()));
        System.out.println(vector3DDecorator.getSrcV());
*/
    }
}
