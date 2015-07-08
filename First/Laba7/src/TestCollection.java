
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

interface Some {

    long timeTest1();

    long timeTest2();

    void print();
}

class lists implements Some {

    private List arr;
    private final int n = 100000;
    private Random random = new Random();

    ;
 
    lists(List arr) {
        this.arr = arr;
    }

    public long timeTest1() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arr.add(0, i);
        }
        long b = System.currentTimeMillis();
        arr.clear();
        return b - a;
    }

    public long timeTest2() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arr.add(arr.size(), i);
        }
        long b = System.currentTimeMillis();
        arr.clear();
        return b - a;
    }

    public long timeTest3() {
        long a = System.currentTimeMillis();
        arr.add(0, 3);
        for (int i = 0; i < n - 1; i++) {
            arr.add(random.nextInt(arr.size()), i);
        }
        long b = System.currentTimeMillis();
        return b - a;
    }

    public long timeTest4() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arr.set(Math.abs(random.nextInt(arr.size())), i);
        }
        long b = System.currentTimeMillis();
        arr.clear();
        return b - a;
    }

    public void print() {
        long a, b, c, d;
        a = timeTest1();
        b = timeTest2();
        c = timeTest3();
        d = timeTest4();
        System.out.println(" Time test1: " + a + " ms");
        System.out.println(" Time test2: " + b + " ms");
        System.out.println(" Time test3: " + c + " ms");
        System.out.println(" Time test4: " + d + " ms");
        System.out.println("TOTAL TIME: " + (a + b + c + d) + " ms");
        System.out.println("-------------------------");
        System.out.println();
    }
}

class deq implements Some {

    private Deque arr;
    private final int n = 100000;

    deq(Deque arr) {
        this.arr = arr;
    }

    public long timeTest1() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arr.addFirst(i);
        }
        long b = System.currentTimeMillis();
        arr.clear();
        return b - a;
    }

    public long timeTest2() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arr.addLast(i);
        }
        long b = System.currentTimeMillis();
        arr.clear();
        return b - a;
    }

    public void print() {
        long a, b;
        a = timeTest1();
        b = timeTest2();
        System.out.println(" Time test1: " + a + " ms");
        System.out.println(" Time test2: " + b + " ms");
        System.out.println("TOTAL TIME: " + (a + b) + " ms");
        System.out.println("-------------------------");
        System.out.println();
    }

}

public class TestCollection {

    static Deque arr;
    static List arr1;
    static Some s;

    public static void main(String[] args) {
        boolean t = true;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("1. ArrayList");
            System.out.println("2. LinkedList");
            System.out.println("3. ArrayDeuqe");
            System.out.println("4. Exit");
            int n = in.nextInt();
            System.out.println("-------------------------");
            switch (n) {
                case 1: {
                    arr1 = new ArrayList();
                    s = new lists(arr1);
                    System.out.println("ArrayList:");
                    s.print();
                    break;
                }
                case 2: {
                    arr1 = new LinkedList();
                    s = new lists(arr1);
                    System.out.println("LinkedList:");
                    s.print();
                    break;
                }
                case 3: {
                    arr = new ArrayDeque();
                    s = new deq(arr);
                    System.out.println("ArrayDeque:");
                    s.print();
                    break;
                }
                case 4: {
                    t = false;
                    in.close();
                    break;
                }
            }
        } while (t);
    }

}
