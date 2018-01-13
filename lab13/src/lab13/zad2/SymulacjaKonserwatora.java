package lab13.zad2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SymulacjaKonserwatora {
    public static void main(String[] args) {
        BlockingQueue<Wiadomosc> blockingDeque = new ArrayBlockingQueue<Wiadomosc>(1024);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Konserwator(blockingDeque));
        for (int i = 0; i < Ustawienia.ILOSC_MIESZKANCOW; i++) {
            executorService.execute(new Mieszkaniec(blockingDeque, i));
        }
        executorService.shutdown();
    }
}
