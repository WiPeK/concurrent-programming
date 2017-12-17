package pw.lab11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable runable = () -> System.out.println("Hello world");
        executorService.execute(runable);
        executorService.execute(runable);
        executorService.execute(runable);
        executorService.execute(runable);
        executorService.execute(runable);
        executorService.execute(runable);
        executorService.shutdown();
    }
}
