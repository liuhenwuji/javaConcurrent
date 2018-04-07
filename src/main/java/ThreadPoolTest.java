import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=1;i<=10;i++){
            final int task = i;
            executorService.execute(new Runnable() {
                public void run() {
                    for(int j=1;j<=10;j++){
                        System.out.println(Thread.currentThread().getName() + " loop " + j + " of " + task);
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
