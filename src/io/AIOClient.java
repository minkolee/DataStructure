package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AIOClient {

    private final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();

    public AIOClient() throws IOException {
    }


    public void connect(int port) {
        //关键的connect函数, 第二个参数是attachment, 最后是一个回调对象
        client.connect(new InetSocketAddress("localhost", port), null, new CompletionHandler<Void, Object>() {

            //连接成功后执行这个方法
            @Override
            public void completed(Void result, Object attachment) {
                System.out.println("连接成功");
                //内部对于写入, 有一个参数的返回Future对象的方法, 也有三参数就像这里的, 继续回调的方法, 因为连接成功后需要发送数据, 所以继续回调
                client.write(ByteBuffer.wrap("Hello!".getBytes(StandardCharsets.UTF_8)), null, new CompletionHandler<Integer, Object>() {

                    //到这里是写入成功, 所以继续回调, 要进行读取
                    @Override
                    public void completed(Integer result, Object attachment) {
                        System.out.println("写入成功");
                        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
                        byteBuffer.clear();
                        client.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
                            //运行到这里说明读取成功
                            @Override
                            public void completed(Integer result, Object attachment) {
                                System.out.println("读取成功");
                                byteBuffer.flip();
                                System.out.println("from server: " + Arrays.toString(byteBuffer.array()));
                                //读取成功后关闭客户端连接
                                try {
                                    client.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            //读取失败
                            @Override
                            public void failed(Throwable exc, Object attachment) {
                                System.out.println("从服务器读取失败");

                            }
                        });
                    }

                    //这里是写入失败
                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("向服务器写入失败");
                    }
                });

            }

            //这里是连接失败
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("连接失败");
            }
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AIOClient client = new AIOClient();
        client.connect(8000);
        while (true) {
            System.out.println("做其他事情");
            Thread.sleep(1000);
        }

    }

}
