import java.util.Random;

public class TreadScopeShareDataThreadLocal {
    private static int data = 0;
    private static ThreadLocal<Integer> threadData = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int data = new Random().nextInt();
                    threadData.set(data);
                    System.out.println(Thread.currentThread().getName()
                            + " ahs put data : " + data);
                    MyThreadScopeData.getThreadInstance().setName("name:" + data);
                    MyThreadScopeData.getThreadInstance().setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }


    }

    static class A {
        public void get() {
            int data = threadData.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data : " + data);
            MyThreadScopeData instance = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get name : " + instance.getName() + " get age : " + instance.getAge());
        }
    }

    static class B {
        public void get() {
            int data = threadData.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data : " + data);

            MyThreadScopeData instance = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get name : " + instance.getName() + " get age : " + instance.getAge());
        }
    }
}

class MyThreadScopeData {
    private static ThreadLocal<MyThreadScopeData> threadScopeData = new ThreadLocal<MyThreadScopeData>();

    private MyThreadScopeData() {
    }

    public static MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = threadScopeData.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            threadScopeData.set(instance);
        }
        return instance;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}