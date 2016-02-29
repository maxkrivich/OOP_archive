package consoleTasks;

/**
 *
 * @author maxkrivich
 */
import static java.lang.Math.pow;
import static java.lang.Math.exp;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class MyFunction implements Evaluatable
{

    private double g;
    private double a;

    public MyFunction(double g, double a)
    {
        this.g = g;
        this.a = a;
    }

    public MyFunction()
    {
        this(1, 0);
    }
    @Override
    public double evalf(double x)
    {
        return exp(-pow(x - a, 2) / (2 * g * g)) / (sqrt(2 * PI));
    }

    public double getG()
    {
        return g;
    }

    public void setG(double g)
    {
        this.g = g;
    }

    public double getA()
    {
        return a;
    }

    public void setA(double a)
    {
        this.a = a;
    }

}
