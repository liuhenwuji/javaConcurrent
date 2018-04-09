public class TreadCommunicationTraditional {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }
}

class Business {
    private boolean bShouldSub = true;

    public synchronized void main(int i) {
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 2; j++) {
            System.out.println("main " + j + " of loop " + i);
        }
        bShouldSub = true;
        this.notify();
    }

    public synchronized void sub(int i) {
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 2; j++) {
            System.out.println("sub " + j + " of loop " + i);
        }
        bShouldSub = false;
        this.notify();
    }
}