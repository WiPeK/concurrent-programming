package zad5;

import java.util.concurrent.SynchronousQueue;

public class Producer implements Runnable {

    private SynchronousQueue queue;

    public Producer(SynchronousQueue queue) {
        this.queue = queue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        String event = "SYNCHRONOUS_EVENT";
        String another_event = "ANOTHER_EVENT";

        try {
            int i = 10;
            while(i > 0) {
                i--;

                queue.put(event);
                System.out.printf("[%s] published event : %s %n", Thread
                        .currentThread().getName(), event);
//                queue.put(another_event);
//
//                System.out.printf("[%s] published event : %s %n", Thread
//                        .currentThread().getName(), another_event);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
