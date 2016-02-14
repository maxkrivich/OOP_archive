/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoleTasks;

/**
 *
 * @author maxkrivich
 */
public abstract class Interpolator implements Evaluatable
{

    abstract public void clear();

    abstract public int numPoints();

    abstract public void addPoint(Point2D pt);

    abstract public Point2D getPoint(int i);

    abstract public void setPoint(int i, Point2D pt);

    abstract public void removeLastPoint();

    abstract public void sort();

    @Override
    public double evalf(double x)
    {
        double res = 0.0;
        int numData = numPoints();
        double numer, denom;
        for (int k = 0; k < numData; k++)
        {
            numer = 1.0;
            denom = 1.0;
            for (int j = 0; j < numData; j++)
            {
                if (j != k)
                {
                    numer *= (x - getPoint(j).getX());
                    denom *= (getPoint(k).getX() - getPoint(j).getX());
                }
            }
            res += (getPoint(k).getY() * numer / denom);
        }
        return res;
    }

}
