package pw.lab11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
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
