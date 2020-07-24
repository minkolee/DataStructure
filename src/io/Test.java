package io;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {

//        new NIOEchoServer(8000).startServer();

        //纯异步, 直接结束
        new AIOServer(8000).start();

        //这里让主线程等待, 后台服务器已经在另外一个线程中开启. 由于所有方法都不阻塞, 主线程退出的时候服务器也关闭了.
        while (true) {
            Thread.sleep(1000);
        }
    }
}
