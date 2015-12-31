import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
/**
 * Created by Максим on 31.12.2015.
 */
public final class Main {
    private double[][] arr;

    public Main(int n, int m) {
        arr = new double[n][m];
    }

    public void generateArray() {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = r.nextDouble();
    }

    public double getArithmeticMean(double[] a) {
        double sum = 0;
        for (double i : a)
            sum += i;
        return a.length == 0 ? Double.MIN_VALUE : sum / a.length;
    }

    public void sort(int pos) {
        Arrays.sort(arr[pos]);
        for (int i = 0; i < arr[pos].length / 2; i++) {
            double tmp = arr[pos][i];
            arr[pos][i] = arr[pos][arr[pos].length - 1 - i];
            arr[pos][arr[pos].length - 1 - i] = tmp;
        }
    }

    public void print(boolean flag) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.printf(Locale.US,"%f\t", arr[i][j]);
            if (flag)
                System.out.printf(Locale.US,"\tAVERAGE:\t%f\t", getArithmeticMean(arr[i]));
            System.out.printf("\n");
        }
    }

    public void doLaba() {
        generateArray();
        System.out.printf("\nBefore Matrix:\n");
        print(false);
        for (int i = 0; i < arr.length; i++)
            sort(i);
        System.out.printf("\nAfter Matrix:\n");
        print(true);
    }

    public static void main(String[] args) {
        Main m = new Main(500, 500);
        m.doLaba();

    }
}
