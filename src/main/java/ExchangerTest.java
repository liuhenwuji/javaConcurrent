import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        executorService.execute(() -> {
                    try {
                        String data1 = "zxx";
                        System.out.println("Thread " + Thread.currentThread().getName()+
                        "正在把数据" + data1 + "换出去");
                        Thread.sleep((long)(Math.random()*10000));
                        String data2 = (String) exchanger.exchange(data1);
                        System.out.println("Thread " + Thread.currentThread().getName()+
                        "换回的数据为" + data2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        executorService.execute(() -> {
                    try {
                        String data1 = "lhm";
                        System.out.println("Thread " + Thread.currentThread().getName()+
                                "正在把数据" + data1 + "换出去");
                        Thread.sleep((long)(Math.random()*10000));
                        String data2 = (String) exchanger.exchange(data1);
                        System.out.println("Thread " + Thread.currentThread().getName()+
                                "换回的数据为" + data2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        executorService.shutdown();
    }
}
