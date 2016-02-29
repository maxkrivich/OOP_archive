package consoleTasks;

/**
 *
 * @author maxkrivich
 */
import java.io.*;
import static java.lang.Math.pow;
import static java.lang.Math.cosh;

public class LeftHand implements Evaluatable
{

    private double a;

    public LeftHand(double a)
    {
        this.a = a;
    }

    public LeftHand()
    {
        this(.0);
    }

    public double getA()
    {
        return a;
    }

    public void setA(double a)
    {
        this.a = a;
    }

    @Override
    public double evalf(double x)
    {
        return 1. / pow(cosh(x), 2) - a * x;
    }

    public static void main(String[] args) throws IOException
    {
        LeftHand fun = new LeftHand();
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.print("a: ");
        double a = in.nextDouble();
        fun.setA(a);
        System.out.print("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.print("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.print("xStep: ");
        double xStep = in.nextDouble();
        System.out.println("Параметр a: " + fun.getA());
        java.io.PrintWriter out = new java.io.PrintWriter(
                new java.io.FileWriter("LeftHand_A=" + a + ".dat"));
        for (double x = xBeg; x <= xEnd; x += xStep)
        {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
            out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }
    }
}
