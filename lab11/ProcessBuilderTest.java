package pw.lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("net", "start");

        Process process = processBuilder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String result = null;
        while ((result = bufferedReader.readLine()) != null) {
            if (result.length() > 0) {
                System.out.println(result);
            }
        }
        process.waitFor();
    }
}
