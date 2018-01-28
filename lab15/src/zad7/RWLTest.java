package zad7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Reader(lock.readLock()));
        service.execute(new Reader(lock.readLock()));
        service.execute(new Reader(lock.readLock()));
        service.execute(new Writer(lock.readLock()));
        service.shutdown();
    }
}

class Reader extends Thread {
    final private ReentrantReadWriteLock.ReadLock readLock;

    public Reader(ReentrantReadWriteLock.ReadLock readLock) {
        this.readLock = readLock;
    }

    @Override
    public void run() {
        while(true) {
            readLock.lock();
            try {
                System.out.println("Czytam " + Shared.getInteger());
            } finally {
                readLock.unlock();
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
    final private ReentrantReadWriteLock.ReadLock readLock;

    public Writer(ReentrantReadWriteLock.ReadLock readLock) {
        this.readLock = readLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(true) {
            readLock.lock();
            try {
                Integer toWrite = random.nextInt(50);
                Shared.setInteger(toWrite);
                System.out.println("Zapisuje " + toWrite);;
            } finally {
                readLock.unlock();
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
