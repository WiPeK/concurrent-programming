package zad5;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private static Runnable reader = () -> {
        while(true) {
            System.out.println("Czytam " + SharedAtomic.getAtomicInteger().getAndIncrement());
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private static Runnable writer = () -> {
        Random random = new Random();
        while(true) {
            Integer toWrite = random.nextInt(50);
            SharedAtomic.getAtomicInteger().set(toWrite);
            System.out.println("Zapisuje " + toWrite);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(reader);
        service.execute(reader);
        service.execute(reader);
        service.execute(writer);
        service.shutdown();
    }
}

class SharedAtomic {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public static void setAtomicInteger(AtomicInteger atomicInteger) {
        SharedAtomic.atomicInteger = atomicInteger;
    }
}
