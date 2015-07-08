
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

    public static void main(String[] args) {
        Stack s = new Stack();
        Vector v = s;    // just test
        System.out.println("--- STACK ---");

        s.push(9);
        System.out.println("Pushed 9");
        s.push(8);
        System.out.println("Pushed 8");
        s.push(7);
        System.out.println("Pushed 7");
        System.out.println("Size = " + s.getSize());
        System.out.println();

        System.out.println("Popped " + s.pop());
        System.out.println("Popped " + s.pop());
        System.out.println("Size = " + s.getSize());
        System.out.println();

        s.push(50.5);
        System.out.println("Pushed 50.5");
        System.out.println("Popped " + s.pop());
        System.out.println("Popped " + s.pop());

        System.out.println("Final size = " + s.getSize());
        System.out.println();

        Queue q = new Queue();
        v = q;    // test 
        System.out.println("--- QUEUE ---");

        q.enqueue(9);
        System.out.println("Added 9");
        q.enqueue(8);
        System.out.println("Added 8");
        q.enqueue(7);
        System.out.println("Added 7");
        System.out.println("Size = " + q.getSize());
        System.out.println();

        System.out.println("Got " + q.dequeue());
        System.out.println("Got " + q.dequeue());
        System.out.println("Size = " + q.getSize());
        System.out.println();

        q.enqueue(50.5);
        System.out.println("Added 50.5");
        System.out.println("Got " + q.dequeue());
        System.out.println("Got " + q.dequeue());

        System.out.println("Final size = " + q.getSize());
    }

}

class Stack extends Vector {

    public Stack() {

    }

    public void push(double val) {
        super.insert(val, super.getSize());
    }

    public double pop() {
        double m = super.get(super.getSize() - 1);
        super.erase(super.getSize() - 1);
        return m;

    }

    public void clear() {
        super.clear();
    }
}

class Queue extends Vector {

    public Queue() {
    }

    public void enqueue(double val) {
        super.insert(val, super.getSize());
    }

    public double dequeue() {
        double m = super.get(0);
        super.erase(0);
        return m;
    }

    public void clear() {
        super.clear();
    }

    public int getSize() {
        return super.getSize();
    }
}
