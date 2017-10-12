package pl.com.bottega.photostock.sales.misc;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducentConsumerProblem {

    static class Producent implements Runnable {

        private Warehause2 warehause;

        public Producent(Warehause2 warehause) {
            this.warehause = warehause;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000 + (long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String product = String.valueOf((int)(Math.random() * 100));
                System.out.println("Wyprodukowałem: " + product);
                warehause.put(product);
            }
        }
    }

    static class Warehause {

        private Queue<String> products = new LinkedList<>();

        public synchronized void put(String product) {
            products.add(product);
            notify();
        }

        public synchronized String take() {
            if (products.isEmpty())
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            String product = products.poll();
            return product;
        }
    }


    static class Warehause2 {

        private BlockingDeque<String> products = new LinkedBlockingDeque<>();

        public  void put(String product) {
          products.add(product);
        }

        public String take() {
            try {
                return products.take();
            } catch (InterruptedException e) {
                return take();
            }
        }
    }

    static class Consumer implements Runnable {

        private Warehause2 warehause;

        public Consumer(Warehause2 warehause) {
            this.warehause = warehause;
        }

        @Override
        public void run() {
            while (true) {
                String product = warehause.take();
                try {
                    Thread.sleep(1000 + (long) (Math.random() * 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Skonsumowałem: " + product);
            }
        }

    }

    private static final int PRODUCENT_COUNT = 10;
    private static final int CONSUMERS_COUNT = 5;

    public static void main(String[] args) {
        Warehause2 warehause = new Warehause2();
        for (int i = 0; i < PRODUCENT_COUNT; i++) {
            new Thread(new Producent(warehause)).start();
        }
        for (int i = 0; i < CONSUMERS_COUNT; i++) {
            new Thread(new Consumer(warehause)).start();
        }

    }


}
