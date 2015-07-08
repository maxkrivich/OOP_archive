
public class Vector {

    private int size;
    private double[] arr;

    public Vector() {
        this(100);
    }

    public Vector(int capacity) {
        assert (this.arr.length > 0);
        this.size = 0;
        this.arr = new double[capacity];
    }

    public Vector(double[] arr) {
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

    public interface Iterator {

        void next();

        double getValue();

        void setValue(double val);

        boolean isValid();
    }

    class Inner implements Iterator {

        int pos = 0;

        public void next() {
            pos++;
        }

        public double getValue() {
            return get(pos);
        }

        public void setValue(double val) {
            set(val, pos);
        }

        public boolean isValid() {
            return (pos < size ? true : false);
        }
    }

    Iterator getForwardIterator() {
        return new Inner();
    }

    Iterator getBackwardIterator() {
        class LocInner implements Iterator {

            int pos = size - 1;

            public void next() {
                pos--;
            }

            public double getValue() {
                return get(pos);
            }

            public void setValue(double val) {
                set(val, pos);
            }

            public boolean isValid() {
                return (pos >= 0 ? true : false);
            }
        }
        return new LocInner();
    }

    public static void main(String[] args) {
        Vector v = new Vector();

        for (int i = 0; i < 10; i++) {
            v.insert(i, i);
        }

        Vector.Iterator it = v.getBackwardIterator();

        while (it.isValid()) {
            System.out.println(it.getValue());
            it.next();
        }
    }

}
