package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) {
        Setup setup = new Setup();
        setup.main();
    }
}


class Producer implements Runnable {
    private final BlockingQueue queue;

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                queue.put(produce());
                System.out.println("put      total " + queue.size());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    Object produce() {
        return new Object();
    }
}

class Consumer implements Runnable {
    private final BlockingQueue queue;

    Consumer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
                System.out.println("get total " + queue.size() );
            }
        } catch (InterruptedException ex) {

        }
    }

    void consume(Object x) {

    }
}

class Setup {
    void main() {
        BlockingQueue q = new ArrayBlockingQueue(3);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}