package pl.com.bottega.photostock.sales.misc;

public class ThreadTest {

    private static int x = 0;

    public static void main(String[] args) {
        String synchronizator = "x";
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                //incX();
                synchronized (synchronizator) {
                    x += 1;
                    try {
                        synchronizator.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Jestem w wątku " + x);
            }
        });
        thread.start();

        for (int i = 0; i < 30; i++) {
            //incX();
            synchronized (synchronizator) {
                x += 1;
                synchronizator.notifyAll();
            }
            System.out.println("Jestem pozam wątkiem " + x);
        }
    }

    private static synchronized void incX() {
        x += 1;
    }

}
