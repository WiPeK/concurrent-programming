package zad4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PoisonTest {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        try {
            queue.put("T");

            queue.put("E");
            queue.put("S");
            queue.put("T");
            new Thread(new PoisonPill(queue)).start();
            queue.put("PI");
            queue.put("LL");
            queue.put(PoisonPill.EXIT_MSG);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }
}

class PoisonPill implements Runnable {
    public static final String EXIT_MSG = "EXIT";
    private BlockingQueue<String> queue;

    public PoisonPill(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            String msg;
            try {
                msg = queue.take();
                System.out.println(msg);
                if (msg.equals(EXIT_MSG)) {
                    System.out.println("Wiadomosc zakonczenia");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
