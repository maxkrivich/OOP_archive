package taskTwo;

public class MyRunnable implements Runnable {

    private boolean flag = false;

    void suspend() {
        this.flag = true;
    }

    synchronized void resume() {
        flag = false;
        this.notify();
    }

    @Override
    public void run() {
        long n = 2;
        boolean f = true;
        while (true) {
            synchronized (this) {
                if (flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {}
                }
                    Main.Pi += 4 * ((f = !f) ? (1 / (2 * n++ + 1)) : -(1 / (2 * n++ - 1)));
            }
        }

    }
}
