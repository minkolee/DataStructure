package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MuiltThreadEchoServer {

    //服务器端口与构造器
    int port;

    public MuiltThreadEchoServer(int port) {
        this.port = port;
    }

    //服务器的线程池
    private final ExecutorService pool = Executors.newCachedThreadPool();

    //处理每个客户端连接的线程类
    static class HandleMsg implements Runnable {
        //私有变量与构造器, 保存当前Socket连接
        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        //核心的run()方法
        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;

            //设置好reader和writer
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                //尝试读出客户端发来的内容, 然后原样写入
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = reader.readLine()) != null) {
                    writer.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend: " + (e - b) + " ms");

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (writer != null) {
                    writer.close();
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void start() {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        //创建服务端Socket, 绑定8000端口
        try{
            echoServer = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
            System.exit(1);
        }

        while (true) {
            try {
                //新进来连接
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connected.");
                pool.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

