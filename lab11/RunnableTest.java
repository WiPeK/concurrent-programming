package pw.lab11;

public class RunnableTest {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("Hello world")).start();
    }
}
