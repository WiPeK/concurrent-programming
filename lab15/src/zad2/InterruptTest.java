package zad2;

import java.util.concurrent.TimeUnit;

public class InterruptTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Watek anulowany");
            }
            System.out.println("Watek zakonczony");
        });
        thread.start();
        thread.interrupt();
    }
}


