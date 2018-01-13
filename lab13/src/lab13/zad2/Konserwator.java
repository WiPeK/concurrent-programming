package lab13.zad2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Konserwator implements Runnable {
    private BlockingQueue<Wiadomosc> blockingQueue;

    public Konserwator(BlockingQueue<Wiadomosc> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            Random random = new Random();
            Wiadomosc wiadomosc = null;
            try {
                wiadomosc = blockingQueue.take();
                System.out.println("Odpbieram zgłoszenie: " + wiadomosc);
                TimeUnit.SECONDS.sleep(random.nextInt(Ustawienia.MAX_CZAS_NAPRAWY));
                System.out.println("Awaria naprawiona: " + wiadomosc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
