package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TreadCommunicationBlockingQueue {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                business.sub(i);
            }
        }).start();

        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }

    static class Business {
        private BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
        private BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);

        {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 2; j++) {
                System.out.println("main " + j + " of loop " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public void sub(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 2; j++) {
                System.out.println("sub " + j + " of loop " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

