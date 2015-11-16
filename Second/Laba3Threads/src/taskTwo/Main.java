package taskTwo;

public class Main {

    static double Pi = 1;

    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
        while (true) {
//Продолжить вычисления.
//Приостановить вычисления.
//Посмотреть текущий результат.
//Узнать суммарное время, затраченное на вычисления.
//Выход.
            switch (new java.util.Scanner(System.in).nextInt()) {
                case 1: r.suspend();
                case 2: r.resume();
                case 3:
                    System.out.println(Pi);
                    break;
                case 5:
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }
}
