package multithread.FutureMode;

import java.util.concurrent.Callable;

public class MyTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            stringBuilder.append(i);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
