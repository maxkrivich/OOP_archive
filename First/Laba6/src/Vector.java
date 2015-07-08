
interface Container {

    void put(double val);

    double get();

    int getSize();

    void clear();
}

class Stack implements Container {

    private Vector v;

    public Stack() {
        v = new Vector();
    }

    public void put(double val) {
        v.insert(val, v.getSize());
    }

    public double get() {
        double m = v.get(v.getSize() - 1);
        v.erase(v.getSize() - 1);
        return m;

    }

    public int getSize() {
        return v.getSize();
    }

    public void clear() {
        v = new Vector();
    }
}

class Queue implements Container {

    private Vector v;

    public Queue() {
        v = new Vector();
    }

    public void put(double val) {
        v.insert(val, v.getSize());
    }

    public double get() {
        double m = v.get(0);
        v.erase(0);
        return m;
    }

    public void clear() {
        v = new Vector();
    }

    public int getSize() {
        return v.getSize();
    }
}

public class Vector {

    private int size;
    private double[] arr;

    public Vector() {
        this.size = 0;
        this.arr = new double[100];
    }

    public Vector(int capacity) {
        assert (capacity > 0);
        this.size = 0;
        this.arr = new double[capacity];
    }

    public Vector(double[] arr) {
        assert (arr.length > 0);
        this.arr = new double[arr.length];
        this.size = arr.length;
        System.arraycopy(arr, 0, this.arr, 0, size);

    }

    @Override
    public Vector clone() {
        return new Vector(this.arr);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return arr.length;
    }

    public double get(int pos) {
        assert (pos >= 0 && pos < size);
        return arr[pos];
    }

    public void insert(double val, int pos) {
        assert (pos >= 0 && pos <= size);
        if (arr.length > size) {
            arr = in(val, pos);
        }
        if (arr.length == size) {
            double[] arrt = new double[arr.length * 2];
            System.arraycopy(arr, 0, arrt, 0, arr.length);
            arr = arrt;
            arr = in(val, pos);
        }
        size++;
    }

    public double[] in(double val, int pos) {
        if (size < pos) {
            arr[size] = val;
        } else {
            System.arraycopy(arr, pos, arr, pos + 1, size - pos);
            arr[pos] = val;
        }
        return arr;
    }

    public void set(double val, int pos) {
        assert (pos >= 0 && pos < size);
        arr[pos] = val;
    }

    public void print() {
        if (size == 0) {
            System.out.println("Empty");
        } else if (size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

    }

    public void clear() {
        size = 0;
        arr = new double[0];
    }

    public void erase(int pos) {
        assert (pos >= 0 && pos < size);
        System.arraycopy(arr, pos + 1, arr, pos, arr.length - 1 - pos);
        size--;
    }
}

class Test {

    public static void main(String[] args) {
        // stack
        Container c = new Stack();
        c.put(1);
        c.put(2);
        // reverse order
        System.out.println(c.get());
        System.out.println(c.get());

        // queue
        c = new Queue();
        c.put(10);
        c.put(20);
        // same order
        System.out.println(c.get());
        System.out.println(c.get());
    }
}
