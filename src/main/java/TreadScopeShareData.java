import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TreadScopeShareData {
    private static int data = 0;
    private static Map<Thread,Integer> threadData = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int data = new Random().nextInt();
                    threadData.put(Thread.currentThread(), data);
                    System.out.println(Thread.currentThread().getName()
                            + " ahs put data : " + data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }


    }

    static class A {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data : " + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data : " + data);
        }
    }
}
