package lab13.zad1.hashset;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMyHashSet implements Runnable {
    private int nr;
    private MyHashSet myHashSet;

    public TestMyHashSet(int nr, MyHashSet myHashSet) {
        this.nr = nr;
        this.myHashSet = myHashSet;
    }


    @Override
    public void run() {
        if ((nr&1) != 0) {
            myHashSet.add(new Random().nextInt(10));
        } else {
            System.out.println(myHashSet);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyHashSet myHashSet = new MyHashSet();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestMyHashSet(i, myHashSet));
        }
        executorService.shutdown();
    }
}
