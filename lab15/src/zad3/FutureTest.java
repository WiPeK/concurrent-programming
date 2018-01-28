package zad3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    private static Runnable task = () -> {
        System.out.println("Watek startuje");
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Watek wykonany");
        } catch (InterruptedException e) {
            System.out.println("Watek zostal zatrzymany");
        }
        System.out.println("Watek zakonczony");
    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(task);
        TimeUnit.SECONDS.sleep(1);
        submit.cancel(true);
        executorService.shutdown();
    }
}
