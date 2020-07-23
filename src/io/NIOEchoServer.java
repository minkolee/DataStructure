package io;

import alog4e.chapter02.sort01.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOEchoServer {

    private Selector selector;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final Map<Socket, Long> time = new HashMap<>();

    int port;

    public NIOEchoServer(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
    }

    public void startServer() throws IOException {
        //创建TCP服务器Channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定到端口
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", port));
        //这个套路都知道了, 注册给selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //服务器主循环
        while (true) {
            //阻塞在此, 监听channel
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            long e = 0;

            //这里其实就一个Channel, 只要有连接或者发送信息, 就是出现响应
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                //这个判断是有新连接进来
                if (key.isAcceptable()) {
                    doAccept(key);
                }

                //可读, 相当于客户端发送信息过来, 记录时间后执行读取动作
                else if (key.isValid() && key.isReadable()) {
                    //向记录时间的Map中写入时间
                    if (!time.containsKey(((SocketChannel) key.channel()).socket())) {
                        time.put(((SocketChannel) key.channel()).socket(), System.currentTimeMillis());
                    }
                    doRead(key);

                //可写, 相当于客户端发完了信息, 在等待响应
                } else if (key.isValid() && key.isWritable()) {
                    doWrite(key);
                    e = (System.currentTimeMillis());
                    //从记录时间的map中取出对应的时间, 然后比较一下耗时
                    long b = time.remove(((SocketChannel) key.channel()).socket());
                    System.out.println("耗时: " + (e - b) + " ms");

                }
            }
        }
    }

    private void doWrite(SelectionKey key) throws IOException {
        //注意,  进到这里的SelectionKey不是ServerSocketChannel的Key, 而是SocketChannel的Key, 这是因为能够监听到READ事件的是在Accept()方法里注册SocketChannel的Key
        SocketChannel clientSocket = (SocketChannel) key.channel();
        ClientData data = (ClientData) key.attachment();

        LinkedList<ByteBuffer> buffers = data.getOutQueue();

        ByteBuffer lastBuffer = buffers.getLast();

        try {
            int len = clientSocket.write(lastBuffer);
            if (len == -1) {
                disconnect(key);
                return;
            }

            //完整的写了一个Buffer, 就移除
            if (lastBuffer.remaining() == 0) {
                buffers.removeLast();
            }
        } catch (IOException e) {
            System.out.println("向客户端写入失败.");
            e.printStackTrace();
            disconnect(key);
        }

        //很重要, 如果全部写完, 就要取消OP_WRITE事件监听
        if (buffers.size() == 0) {
            key.interestOps(SelectionKey.OP_READ);
        }

    }

    private void doRead(SelectionKey key) throws IOException {
        //注意,  进到这里的SelectionKey不是ServerSocketChannel的Key, 而是SocketChannel的Key, 这是因为能够监听到READ事件的是在Accept()方法里注册SocketChannel的Key
        SocketChannel clientSocket = (SocketChannel) key.channel();
        //创建8K大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);

        int len;

        //尝试读取
        try {
            len = clientSocket.read(byteBuffer);
            //如果读取结果是-1, 说明读取完毕, 结束
            if (len < 0) {
                disconnect(key);
                return;
            }
        } catch (IOException e) {
            //出错也关闭channel
            System.out.println("Failed to read from client.");
            e.printStackTrace();
            disconnect(key);
        }

        byteBuffer.flip();

        pool.execute(new HandleMsg(key, byteBuffer));

    }

    private void disconnect(SelectionKey key) throws IOException {
        SelectableChannel channel = key.channel();
        channel.close();
    }

    private void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientSocket = serverSocketChannel.accept();

        //凡是支持selectableChannel的, 都要设置成非阻塞
        clientSocket.configureBlocking(false);

        //这里很关键, 把获取的具体ClientChannel, 也注册到同一个selector上, 注册事件为可读, 此时已经连接完毕, 下一个事件就是可读了.
        //这样同一个selector, 就可以既监听TCP进来连接, 也可以监听已经创建的连接了, 前边的startServer()中的方法的三种分支就有用武之地了.
        SelectionKey clientKey = clientSocket.register(selector, SelectionKey.OP_READ);

        //由于客户端不是一次性发完全部数据, 而是慢慢发送, 因此给key attach一个对象, 就像是MVC里的model一样, 以后再使用这个key, 都可以共享这个数据对象
        ClientData data = new ClientData();
        clientKey.attach(data);

        //doAccept()的作用就是获取TCP连接, 将连接加入到Selector的监听中, 然后附加上一个数据对象用于和客户端的通信, 之后就结束了.
    }


    private class HandleMsg implements Runnable {
        ByteBuffer byteBuffer;
        SelectionKey key;
        public HandleMsg(SelectionKey key, ByteBuffer byteBuffer) {
            this.key = key;
            this.byteBuffer = byteBuffer;
        }

        @Override
        public void run() {
            ClientData data = (ClientData) key.attachment();
            data.enqueue(byteBuffer);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }
}
