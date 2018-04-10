package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreadCommunicationCondition {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }

    static class Business {
        private boolean bShouldSub = true;
        private Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void main(int i) {
            lock.lock();
            try {
                while (bShouldSub) {
                    try {
//                    this.wait();
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 2; j++) {
                    System.out.println("main " + j + " of loop " + i);
                }
                bShouldSub = true;
//            this.notify();
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub(int i) {
            lock.lock();
            try {
                while (!bShouldSub) {
                    try {
//                        this.wait();
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 2; j++) {
                    System.out.println("sub " + j + " of loop " + i);
                }
                bShouldSub = false;
//                this.notify();
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

