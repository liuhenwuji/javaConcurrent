import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i=0;i<3;i++){
            Runnable runnable = ()->{
                try {
                    Thread.sleep((long)(Math.random()*1000));
                    System.out.println("Thread Assembly point 1 " + Thread.currentThread().getName() + " have " + cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.await();

                    Thread.sleep((long)(Math.random()*1000));
                    System.out.println("Thread Assembly point 2 " + Thread.currentThread().getName() + " have " + cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.await();

                    Thread.sleep((long)(Math.random()*1000));
                    System.out.println("Thread Assembly point 3 " + Thread.currentThread().getName() + " have " + cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
