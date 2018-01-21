package zad5;

import java.util.concurrent.SynchronousQueue;

public class SyncQueueTest {

    public static void main(String args[]) {
        final SynchronousQueue queue = new SynchronousQueue();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

}
