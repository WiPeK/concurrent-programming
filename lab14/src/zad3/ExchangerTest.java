package zad3;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        ExchangerTask exchangerTask =
                new ExchangerTask(exchanger, "A");

        ExchangerTask exchangerTask1 =
                new ExchangerTask(exchanger, "B");

        new Thread(exchangerTask).start();
        new Thread(exchangerTask1).start();
    }
}
