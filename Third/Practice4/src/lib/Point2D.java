package lib;

/**
 *
 * @author maxkrivich
 */
public class Point2D extends Point implements Comparable<Point2D>
{

    public Point2D(double x, double y)
    {
        super(2);
        setCoord(1, x);
        setCoord(2, y);
    }

    public Point2D()
    {
        this(0, 0);
    }

    public double getX()
    {
        return getCoord(1);
    }

    public void setX(double x)
    {
        setCoord(1, x);
    }

    public double getY()
    {
        return getCoord(2);
    }

    public void setY(double y)
    {
        setCoord(2, y);
    }

    @Override
    public int compareTo(Point2D obj)
    {
        return Double.compare(this.getX(), obj.getX());
    }

    @Override
    public String toString()
    {
        return String.format("(%f, %f)", getX(), getY());
    }

}
