package concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
    public static void main(String[] args) throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        },5000,3000);
        while (true){
            System.out.println(new Date().getSeconds());
            Thread.sleep(1000);
        }
    }
}
