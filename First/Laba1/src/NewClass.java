
import java.util.Arrays;
import java.util.Scanner;

public class NewClass {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int cnt = 3;
        Runner[] arr = new Runner[in.nextInt()];
        for (int i = 0; i < arr.length; i++) 
            arr[i] = new Runner(in.next(), in.nextDouble());
        in.close();
        Arrays.sort(arr);
        for (int i = 0; i < cnt; i++) 
            System.out.println((i + 1) + " " + arr[i].toString());
    }
}

class Runner implements Comparable<Runner> {

    private String name;
    private double time;

    public Runner(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return getName() + " " + getTime();
    }

    @Override
    public int compareTo(Runner obj) {
        if (this.getTime() < obj.getTime()) 
            return -1;
         else if (this.getTime() > obj.getTime()) 
            return 1;
        return 0;
    }

}
