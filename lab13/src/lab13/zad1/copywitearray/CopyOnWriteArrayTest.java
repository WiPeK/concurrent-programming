package lab13.zad1.copywitearray;

import lab13.zad1.skiplist.SkipListTest;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayTest implements Runnable {
    private int nr;
    private CopyOnWriteArraySet<Integer> copyOnWriteArraySet;

    public CopyOnWriteArrayTest(int nr, CopyOnWriteArraySet<Integer> copyOnWriteArraySet) {
        this.nr = nr;
        this.copyOnWriteArraySet = copyOnWriteArraySet;
    }

    @Override
    public void run() {
        if ((nr&1) != 0) {
            int value = new Random().nextInt(10);
            copyOnWriteArraySet.add(value);
            System.out.println("Adding: " + value);
        } else {
            System.out.println(copyOnWriteArraySet);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new CopyOnWriteArrayTest(i, set));
        }
        executorService.shutdown();
    }
}
