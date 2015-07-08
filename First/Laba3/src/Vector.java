
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
        if (size - 1 < pos) {
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

    public static void main(String[] args) {
        double arr[] = {6, 5, 4, 3, 2, 1};
        Vector va = new Vector(arr);

        System.out.println(va.getSize());
        va.print();
        System.out.println(va.getCapacity() >= va.getSize());

        arr[3] = 100;
        va.print();

        Vector v = new Vector(18);
        System.out.println(v.getSize() + " " + v.getCapacity());

        v.insert(5.5, 0);
        v.print();
    }

}
