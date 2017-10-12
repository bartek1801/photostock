package pl.com.bottega.photostock.sales.misc;

public class ThreadTest {

    private static int x = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(() ->{
            for (int i = 0; i < 30; i++){
                //incX();
                synchronized (ThreadTest.class){
                    x += 1  ;
                }


                System.out.println("jestem w wątku " + x);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        thread.start();

        for (int i = 0; i<30; i++){
            incX();
            synchronized (ThreadTest.class){
                x += 1  ;
            }

            System.out.println("Jestem poza wątkiem " + x);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    private static synchronized void incX(){
        x += 1;
    }

}
