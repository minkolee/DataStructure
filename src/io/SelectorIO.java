package io;

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
import java.util.Iterator;
import java.util.Set;

public class SelectorIO {

    public static void main(String[] args) throws IOException {

        //新创建一个ServerSocketChannel, 也就是TCP服务端
        ServerSocketChannel serverSocketChannelAt8000 = ServerSocketChannel.open();

        //很重要, 将其设置为异步模式, 否则还是同步模式
        serverSocketChannelAt8000.configureBlocking(false);

        //只有了TCP服务端Channel, 还需要从中获取其内部包装的ServerSocket对象用来绑定端口
        ServerSocket socket = serverSocketChannelAt8000.socket();
        InetSocketAddress address = new InetSocketAddress(8000);
        socket.bind(address);

        //继续创建一个绑定7000, 6000端口的channel
        ServerSocketChannel serverSocketChannelAt7000 = ServerSocketChannel.open();
        serverSocketChannelAt7000.socket().bind(new InetSocketAddress(7000));

        serverSocketChannelAt7000.configureBlocking(false);

        ServerSocketChannel serverSocketChannelAt8888 = ServerSocketChannel.open();
        serverSocketChannelAt8888.socket().bind(new InetSocketAddress(8888));

        serverSocketChannelAt8888.configureBlocking(false);
        //创建一个selector用于监听
        Selector selector = Selector.open();

        //channel调用自己的register方法向selector中注册, 并得到一个SelectionKey对象, 当然此时这个Key没有什么用, 实际用的是每个连接进来的描述符对应的Key
        //将三个Channel都注册到selector中
        SelectionKey key8000 = serverSocketChannelAt8000.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key7000 = serverSocketChannelAt7000.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key8888 = serverSocketChannelAt8888.register(selector, SelectionKey.OP_ACCEPT);

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
                //这里要注意, 处理完一个之后, 需要立刻将其从迭代中去除, 否则下一次还会继续监听到这个端口
                iterable.remove();
                if (selectionKey.isAcceptable()) {
                    //从SelectionKey中获取channel对象, 因为知道类型, 所以强制转换, 然后可以从其中获取连接的信息
                    ServerSocketChannel newChannel = (ServerSocketChannel) selectionKey.channel();
                    System.out.println("接受连接来自: "+ newChannel.socket().getLocalPort());
                    //从ServerSocketChannel中获取SocketChannel. 也就是TCP连接
                    SocketChannel socketChannel = newChannel.accept();

                    //将内容一次性读入到2048长度的字节中
                    ByteBuffer byteBufferIn = ByteBuffer.allocate(2048);
                    socketChannel.read(byteBufferIn);
                    byteBufferIn.flip();

                    //将其按照UTF-8进行解码然后放到CharBuffer中, 打印出来
                    Charset utf8 = Charset.forName("UTF-8");
                    CharsetDecoder decoder = utf8.newDecoder();

                    CharBuffer charBufferin = decoder.decode(byteBufferIn);

                    System.out.println(charBufferin.array());

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
