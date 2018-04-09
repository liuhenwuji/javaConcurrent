package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TraditionalThreadSynchronized {
    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    class Outputer {
        private Lock lock = new ReentrantLock();

        public void output(String name) {
//            synchronized (this) {
//                for (int i = 0; i < name.length(); i++) {
//                    System.out.print(name.charAt(i));
//                }
//                System.out.println();
//            }

            lock.lock();
            try {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("helloWorld");
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("touchair");
                }
            }
        }).start();
    }
}
