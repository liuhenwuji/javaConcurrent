package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Job1 {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);

        System.out.println("begin:" + System.currentTimeMillis() / 1000);
        for (int i = 0; i < 16; i++) {
            final String log = "" + (i + 1);
//            parseLong(log);
            try {
                queue.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        String log = queue.take();
                        parseLong(log);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void parseLong(String log) {
        System.out.println(log + ":" + System.currentTimeMillis() / 1000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
