package consoleTasks;

import java.io.IOException;
import java.util.Arrays;
import javax.swing.*;
import org.math.plot.*;

/**
 *
 * @author maxkrivich
 */
public class Graphs extends JFrame
{

    private Plot2DPanel panel;
    private FileListInterpolation flsi;

    public Graphs(Evaluatable e) throws IOException
    {
        super("Graphs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        panel = new Plot2DPanel();
        flsi = new FileListInterpolation();
        init(e);
        flsi.sort();
        double yy[] = flsi.getYY(), x[][] = toDouble(), xx[] = new double[flsi.numPoints()];
        loadData(e.getClass().getSimpleName(), x);
        for (int i = 0; i < x.length; i++)
            xx[i] = x[i][0];
        Arrays.sort(yy);
        loadData(e.getClass().getSimpleName()+"'", xx, yy);
        this.setContentPane(panel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init(Evaluatable e) throws IOException
    {
        flsi.readFromFile(e.getClass().getSimpleName() + ".dat",true);
    }

    private double[][] toDouble()
    {
        double a[][] = new double[flsi.numPoints()][2];
        for (int i = 0; i < flsi.numPoints(); i++)
        {
            a[i][0] = flsi.getPoint(i).getX();
            a[i][1] = flsi.getPoint(i).getY();
        }
        return a;
    }

    public void loadData(String name, double[] x, double[] y)
    {
        panel.addLinePlot(name, x, y);
    }

    public void loadData(String name, double[][] x)
    {
        panel.addLinePlot(name, x);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    new Graphs(new MyFunction());
                } catch (IOException ex)
                {

                }
            }
        });

    }
}
