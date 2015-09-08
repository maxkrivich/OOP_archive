
public class Rectangle {

    private double width;
    private double height;

    public Rectangle(double w, double h) {
        setWidth(w);
        setHeight(h);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return width * 2 + height * 2;
    }

    public void print() {
        System.out.println(getWidth() + " x " + getHeight());
    }

    public static void main(String[] args) {
        Square s = new Square(2);
        s.print();
        System.out.println(s.getArea() + " " + s.getPerimeter());
    }

}

class Square {

    Rectangle r;

    public Square(double side) {
        r = new Rectangle(side, side);
    }

    public double getSide() {
        return r.getHeight();
    }

    public void setSide(double s) {
        r.setWidth(s);
        r.setHeight(s);
    }

    public double getArea() {
        return r.getArea();
    }

    public double getPerimeter() {
        return r.getPerimeter();
    }

    public void print() {
        System.out.println(getSide() + " x " + getSide());
    }
}
