package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AIOServer {

    private final int port;
    private AsynchronousServerSocketChannel serverSocketChannel;

    public AIOServer(int port) {
        this.port = port;
        try {
            //启动服务器和绑定端口
            this.serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
        } catch (IOException e) {
            System.out.println("服务器启动失败");
            System.exit(1);
        }
    }

    public void start() {
        System.out.println("服务器启动在: " + port + "端口");
        //非常关键的一步, 注册一个CompletionHandler, 这个是java.nio.channels中的一个接口, 专门用于AIO的回调
        //第一个参数是一个attachment, 这个attachment会在后边的两个方法内作为第二个参数被使用
        serverSocketChannel.accept(42, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            //这个是必须实现的方法之一, 用于成功的时候如何做
            final ByteBuffer byteBuffer = ByteBuffer.allocate(2048);

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {

                //由于纯异步, 需要搭配Future对象, 先定义一个要返回给客户端的结果, 然后放在Future对象中

                Future<Integer> writeResult = null;
                byteBuffer.clear();

                //重要, AsynchronousSocketChannel的read方法返回一个Future<Integer>对象, 表示后来读到了几个字节, 因为这个read方法不阻塞.
                //非Asynchronous的Channel比如上一次的SocketChannel的read方法返回一个int, 这是因为read是阻塞的.
                writeResult =  result.read(byteBuffer);

                //睡眠1秒来模拟做其他事情
                try {
                    System.out.println("服务端开始做1秒钟的其他事情...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //只要不调用Future对象的.get()方法, 就不会等待Future对象完成, 所以到这里都不会阻塞
                try {
                    System.out.println("实际读取到了: "+ writeResult.get(100, TimeUnit.SECONDS) + "个字节.");
                    System.out.println("读取到的是: " + Arrays.toString(byteBuffer.array()));
                    byteBuffer.flip();

                    //这个write()方法也一样返回一个Future对象, 然后立刻返回
                    writeResult = result.write(byteBuffer);

                } catch (InterruptedException | TimeoutException | ExecutionException e) {
                    System.out.println("Future结果读取错误.");
                } finally {
                    serverSocketChannel.accept(null, this);
                    //等待写完
                    try {
                        //确认写完, 然后关闭当前连接的AsyncSocketChannel
                        writeResult.get();
                        result.close();

                    } catch (InterruptedException | ExecutionException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            //这个是另外一个必须实现的方法, 用于失败的时候如何做
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("连接失败. 异常是: " + exc);
            }
        });

    }
}
