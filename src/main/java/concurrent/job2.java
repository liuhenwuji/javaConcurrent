package concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class Job2 {
    public static void main(String[] args) {
        //有人take，才能够put
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    String input = queue.take();
                    String output = TestDone.doSome(input);
                    System.out.println(Thread.currentThread().getName() + ":" + output);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("begin:" + System.currentTimeMillis() / 1000);
        for (int i = 0; i < 10; i++) {  //不动
            String input = i + "";      //不动
//            String output = TestDo.doSome(input);
//            System.out.println(Thread.currentThread().getName() + ":" + output);
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}

//不动
class TestDone {
    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + System.currentTimeMillis() / 1000;
        return output;
    }
}