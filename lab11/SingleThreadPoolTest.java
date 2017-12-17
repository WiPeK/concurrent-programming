package pw.lab11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
