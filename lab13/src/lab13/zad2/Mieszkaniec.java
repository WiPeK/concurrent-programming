package lab13.zad2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Mieszkaniec implements Runnable {

    private BlockingQueue<Wiadomosc> blockingQueue;
    private int nr;

    public Mieszkaniec(BlockingQueue<Wiadomosc> blockingQueue, int nr) {
        this.blockingQueue = blockingQueue;
        this.nr = nr;
    }

    @Override
    public void run() {
        while (true) {
            float prawodopodobienstwo = Ustawienia.PRAWDOPODOBIENSTWO_AWARII;
            Random random = new Random();
            int maxValue = 10/Math.round(prawodopodobienstwo * 10);

            int randVal = random.nextInt(maxValue);
            if (randVal == 0) {
                String opis = "Awaria u mieszkanca " + nr;
                try {
                    blockingQueue.put(new Wiadomosc(opis));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Mieszkaniec " + nr + " awaria");
            }
            try {
                TimeUnit.SECONDS.sleep(Ustawienia.CZAS_CZEKANIA_MIESZKANCA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
