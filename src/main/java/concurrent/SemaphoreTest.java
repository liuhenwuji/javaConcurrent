package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<10;i++){
            Runnable runnable = () -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " enter, have " +
                        (3-semaphore.availablePermits()) + " 并发");
                try {
                    Thread.sleep((long) (Math.random()*10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread " + Thread.currentThread().getName() + " will be out ");
                semaphore.release();
                System.out.println("Thread " + Thread.currentThread().getName() + " enter, have been out " +
                        (3-semaphore.availablePermits()) + " 并发" );
            };
            executorService.execute(runnable);
        }
    }
}
