package multithread.chapter04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    private final static ThreadLocal<SimpleDateFormat> data = new ThreadLocal<>();

    public static class ParseDate implements Runnable {

        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (data.get() == null) {
                    data.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date t = data.get().parse("2020-07-07 17:02:" + i % 60);
                data.remove();
                System.out.println(i + ":" + t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new ParseDate(i));
        }
        executorService.shutdown();
    }

}
