package lab13.zad1.synchset;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSynchSet implements Runnable {

    private int nr;
    private Set<Integer> set;

    public TestSynchSet(int nr, Set<Integer> set) {
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
        Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestSynchSet(i, set));
        }
        executorService.shutdown();
    }
}
