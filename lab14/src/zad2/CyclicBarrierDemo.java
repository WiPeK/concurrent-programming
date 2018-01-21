package zad2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String args[]) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("\nAll threads finished level\n"));

        new Thread(new Test(cyclicBarrier), "Thread 1").start();
        new Thread(new Test(cyclicBarrier), "Thread 2").start();
        new Thread(new Test(cyclicBarrier), "Thread 3").start();
        new Thread(new Test(cyclicBarrier), "Thread 4").start();
    }
}

class Test implements Runnable {

    private CyclicBarrier cyclicBarrier;

    Test(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " finish level 1");

            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " finish level 2");

            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " finish level 3");

            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " finish level 4");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
