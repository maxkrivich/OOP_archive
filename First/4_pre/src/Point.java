
public class Point {

    protected double x, y;

    protected Point() {
        this(0, 0);
    }

    protected Point(double x, double y) {
        System.out.println("Point()");
        this.x = x;
        this.y = y;
    }

    void print() {
        System.out.println("(" + x + ", " + y + ")");
    }

    public static void main(String[] args) {
        Point3d p2 = new Point3d();
        p2.print();
    }

}

class Point3d extends Point {

    private double z;

    Point3d() {
        super(0, 0);
        this.z = 0;
        System.out.println("Point3d()");
    }

    public Point3d(double x, double y, double z) {
        super(x, y);
        this.z = z;
        System.out.println("Point3d()");

    }

    public void print() {
        System.out.println("(" + x + ", " + y + ", " + z + ")");
    }
}
