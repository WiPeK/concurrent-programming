package zad5;

import java.util.concurrent.SynchronousQueue;

public class Consumer implements Runnable {
    private SynchronousQueue queue;

    public Consumer(SynchronousQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int i = 10;
             do{
                i--;
                String event = (String) queue.take();
                System.out.printf("[%s] consumed event : %s %n", Thread
                        .currentThread().getName(), event);
            }while (i < 10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
