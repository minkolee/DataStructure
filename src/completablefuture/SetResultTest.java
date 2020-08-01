package completablefuture;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SetResultTest {

    private static class Test extends Thread {

        CompletableFuture<Integer> future;

        public Test(CompletableFuture<Integer> future) {
            this.future = future;
        }

        @Override
        public void run() {
            boolean got = false;

            while (!got) {
                try {
                    int result = future.get(2, TimeUnit.SECONDS);
                    System.out.println("成功获取结果: " + result);
                    got = true;
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        new Test(future).start();

        Scanner scanner = new Scanner(System.in);

        System.out.print("随便输入点什么: ");
        int l = scanner.nextLine().length();
        System.out.println("将结果设置为输入的长度: "+l);
        future.complete(l);



    }


}
