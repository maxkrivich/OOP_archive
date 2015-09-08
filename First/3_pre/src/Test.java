
public class Test {

    static double[] data = new double[10];
    static int size = 0;

    static void add(double val) {
        if (size < data.length) {
            data[size] = val;
        } else {
            double[] datatmp = new double[data.length * 2];
            System.arraycopy(data, 0, datatmp, 0, size);
            data = datatmp;
            data[size] = val;
        }
        size++;
    }

    public static double get(int pos) {
        assert (pos >= 0 && pos < size);
        return data[pos];
    }

    public static void main(String[] args) {
        Test.add(2);
        Test.add(3);

        try {
            System.out.println(Test.get(3));
        } catch (AssertionError ae) {
            System.out.println(ae);
            ae.printStackTrace(System.err);
        }
    }

}
