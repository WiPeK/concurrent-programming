package lab13.zad1.skiplist;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SkipListTest implements Runnable {
    private int nr;
    private ConcurrentSkipListSet<Integer> set;

    public SkipListTest(int nr, ConcurrentSkipListSet<Integer> set) {
        this.nr = nr;
        this.set = set;
    }

    @Override
    public void run() {
        if ((nr&1) != 0) {
            int value = new Random().nextInt(10);
            set.add(value);
            System.out.println("Adding: " + value);
        } else {
            System.out.println(set);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new SkipListTest(i, set));
        }
        executorService.shutdown();
    }
}
