package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreadCommunicationConditionThree {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.sub1(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.sub2(i);
                }
            }
        }).start();

        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }

    static class Business {
        private Lock lock = new ReentrantLock();

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        private int shouldSub = 1;

        public void main(int i) {
            lock.lock();
            try {
                while (shouldSub != 1) {
                    condition1.await();
                }
                for (int j = 1; j <= 2; j++) {
                    System.out.println("main " + j + " of loop " + i);
                }
                shouldSub = 2;
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void sub1(int i) {
            lock.lock();
            try {
                while (shouldSub != 2){
                    condition2.await();
                }
                for (int j = 1; j <= 2; j++) {
                    System.out.println("sub1 " + j + " of loop " + i);
                }
                shouldSub = 3;
                condition3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void sub2(int i) {
            lock.lock();
            try {
                while (shouldSub != 3) {
                    condition3.await();
                }
                for (int j = 1; j <= 2; j++) {
                    System.out.println("sub2 " + j + " of loop " + i);
                }
                shouldSub = 1;
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}

