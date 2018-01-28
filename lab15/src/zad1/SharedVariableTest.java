package zad1;

import java.util.concurrent.TimeUnit;

public class SharedVariableTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            while (!SharedVariable.isCancel()) {
                System.out.println(i++);
            }
            System.out.println("Watek zatrzymany przez zmienna dzielona");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        SharedVariable.setCancel(true);
    }
}



class SharedVariable {
    private static boolean cancel = false;

    public static boolean isCancel() {
        return cancel;
    }

    public static void setCancel(boolean cancel) {
        SharedVariable.cancel = cancel;
    }
}