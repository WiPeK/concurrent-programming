package zad6;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Reader(lock));
        service.execute(new Reader(lock));
        service.execute(new Reader(lock));
        service.execute(new Writer(lock));
        service.shutdown();
    }
}

class Reader extends Thread {
    private Lock lock;

    public Reader(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                System.out.println("Czytam " + Shared.getInteger());
            } finally {
                lock.unlock();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Writer extends Thread {
    private Lock lock;

    public Writer(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(true) {
            lock.lock();
            try {
                Integer toWrite = random.nextInt(50);
                Shared.setInteger(toWrite);
                System.out.println("Zapisuje " + toWrite);;
            } finally {
                lock.unlock();
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Shared {
    private static Integer integer = 0;

    public static Integer getInteger() {
        return integer;
    }

    public static void setInteger(Integer integer) {
        Shared.integer = integer;
    }
}
