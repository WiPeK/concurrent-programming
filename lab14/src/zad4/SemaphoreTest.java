package zad4;

import java.util.concurrent.Semaphore;

class Task extends Thread {

    private String name;
    private Semaphore semaphore;

    public Task(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " : available Semaphore slots before: " + semaphore.availablePermits());
            System.out.println(name + " : acquiring lock.");
            semaphore.acquire();
            System.out.println(name + " : got the free slot!");

            Thread.sleep(1000);

            System.out.println(name + " : releasing lock.");
            semaphore.release();
            System.out.println(name + " : available Semaphore splots after: " + semaphore.availablePermits());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        System.out.println("Total available slots: " + semaphore.availablePermits());

        new Task("Thread 1", semaphore).start();
        new Task("Thread 2", semaphore).start();
        new Task("Thread 3", semaphore).start();
        new Task("Thread 4", semaphore).start();
        new Task("Thread 5", semaphore).start();
    }
}
