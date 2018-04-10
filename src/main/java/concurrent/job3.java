package concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Job3 extends Thread {
    private String key;
    private String value;
    private TestDo testDo;

    public Job3(String key, String key2, String value) {
        this.testDo = TestDo.getInstance();
        this.key = key + key2;
        this.value = value;
    }

    public static void main(String[] args) {
        Job3 a = new Job3("1", "", "1");
        Job3 b = new Job3("1", "", "2");
        Job3 c = new Job3("3", "", "3");
        Job3 d = new Job3("4", "", "4");
        System.out.println("begin:" + System.currentTimeMillis() / 1000);
        a.start();
        b.start();
        c.start();
        d.start();
    }

    public void run() {
        testDo.doSome(key, value);
    }

}

class TestDo {
    private TestDo() {
    }

    private static TestDo instance = new TestDo();

    public static TestDo getInstance() {
        return instance;
    }

    private CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<>();

    public void doSome(Object key, String value) {
        Object object = key;
        if (!keys.contains(object)) {
            keys.add(object);
        } else {
            for (Object o : keys) {
                if (o.equals(object)) {
                    object = o;
                }
            }
        }

        synchronized (object) {
            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":" + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}