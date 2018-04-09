import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatchOrder = new CountDownLatch(1);
        final CountDownLatch countDownLatchAnswer = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "正准确接受命令");
                    countDownLatchOrder.await();
                    System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
                    Thread.sleep((long) (Math.random() * 1000));
                    countDownLatchAnswer.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
        }

        try {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("Thread " + Thread.currentThread().getName() +
                    "即使发布命令");
            countDownLatchOrder.countDown();
            System.out.println("Thread " + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            countDownLatchOrder.await();
            System.out.println("Thread " + Thread.currentThread().getName() +
                    "已收到所有响应的结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
