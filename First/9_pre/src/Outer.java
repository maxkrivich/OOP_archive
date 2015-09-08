
public class Outer {

    private int x;
    private int d = 42;

    public Outer(int x) {
        this.x = x;
    }

    class Inner {

        public int getX() {
            return x;
        }

        int get() {
            return Outer.this.d;
        }
    }

    public static void main(String[] args) {
        Outer o = new Outer(9);
        Outer o1 = new Outer(99);
        Outer o3 = new Outer(11);

        Outer.Inner i = o.new Inner();
        Outer.Inner i1 = o1.new Inner();

        System.out.println(i1.get());
        System.out.println(i.getX());
        System.out.println(i1.getX());

    }
}
