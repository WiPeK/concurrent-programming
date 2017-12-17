package pw.lab11;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> "Hello world");
        System.out.println(future.get());
        executorService.shutdown();
    }
}
