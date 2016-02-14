/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoleTasks;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author maxkrivich
 */
public class DerivativeApplication
{

    public static void main(String[] args) throws IOException
    {
        Evaluatable functs[] = new Evaluatable[3];
        functs[0] = new FFunction(0.5);
        functs[1] = new SolveEqFunction();
        functs[2] = new FileListInterpolation();
        ((SolveEqFunction) functs[1]).setRootApprox(0.7);
        try
        {
            ((FileListInterpolation) functs[2]).readFromFile("TblFunc.dat");
        } catch (IOException ex)
        {
            ex.printStackTrace();
            System.exit(-1);
        }
        String fileName = "";
        for (Evaluatable f : functs)
        {
            System.out.println("Функция: " + f.getClass().getSimpleName());
            fileName = f.getClass().getSimpleName() + ".dat";
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            for (double x = 1.5; x <= 6.5; x += 0.05)
            {
                System.out.println("x: " + x + "\tf: " + f.evalf(x) + "\tf': "
                        + NumMethods.der(x, 1.0e-4, f));
                out.printf("%16.6e%16.6e%16.6e\n", x, f.evalf(x),
                        NumMethods.der(x, 1.0e-4, f));
            }
            System.out.println("\n");
            out.close();
        }
    }
}
