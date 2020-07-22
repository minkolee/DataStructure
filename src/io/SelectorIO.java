package io;

import alog4e.chapter02.sort01.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class SelectorIO {

    public static void main(String[] args) throws IOException {

        //新创建一个ServerSocketChannel, 也就是TCP服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //很重要, 将其设置为异步模式, 否则还是同步模式
        serverSocketChannel.configureBlocking(false);

        //只有了TCP服务端Channel, 还需要从中获取其内部包装的ServerSocket对象
        ServerSocket socket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8000);
        socket.bind(address);


        //创建一个selector用于监听
        Selector selector = Selector.open();

        //channel调用自己的register方法向selector中注册, 并得到一个SelectionKey对象, 当然此时这个Key没有什么用, 实际用的是每个连接进来的描述符对应的Key
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        //主循环
        while (true) {

            //Selector进行监听, 返回一个int, 这个指有几个Channel出现了事件, 即IO可用
            //注意这个方法是阻塞的, 也就是如果执行过了此行, 一定会有可用的IO出现
            int number = selector.select();

            //获取所有可用的keys, 类似于获取所有就绪的Channel
            Set<SelectionKey> availableKeys = selector.selectedKeys();

            //获取迭代器, 用于所有就绪的Channel
            Iterator<SelectionKey> iterable = availableKeys.iterator();

            while (iterable.hasNext()) {
                SelectionKey selectionKey = iterable.next();
                iterable.remove();
                if (selectionKey.isAcceptable()) {
                    System.out.println("接受连接");
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();

                    ByteBuffer byteBufferIn = ByteBuffer.allocate(2048);
                    System.out.println(socketChannel.read(byteBufferIn));
                    byteBufferIn.flip();

                    Charset utf8 = Charset.forName("UTF-8");
                    CharsetEncoder encoder = utf8.newEncoder();
                    CharsetDecoder decoder = utf8.newDecoder();

                    CharBuffer charBufferin = decoder.decode(byteBufferIn);

                    System.out.println(charBufferin.array());

                    String s = "HTTP/1.1 200 OK\n" +
                            "Server: nginx/1.14.2\n" +
                            "Date: Wed, 22 Jul 2020 05:37:16 GMT\n" +
                            "Content-Type: text/html; charset=UTF-8\n" +
                            "Transfer-Encoding: chunked\n" +
                            "Connection: keep-alive\n" +
                            "X-Powered-By: PHP/7.2.15\n" +
                            "Expires: Wed, 11 Jan 1984 05:00:00 GMT\n" +
                            "Cache-Control: no-cache, must-revalidate, max-age=0\n" +
                            "Link: <https://conyli.cc/wp-json/>; rel=\"https://api.w.org/\"\n" +
                            "Content-Encoding: gzip";

                    ByteBuffer byteBufferOut = ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));

                    socketChannel.write(byteBufferOut);
                    socketChannel.close();

                } else if (selectionKey.isValid() && selectionKey.isReadable()) {
                    System.out.println("可以读");
                } else if (selectionKey.isValid() && selectionKey.isWritable()) {
                    System.out.println("可以写");
                }
            }
        }
    }
}
