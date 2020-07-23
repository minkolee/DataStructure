package io;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        new NIOEchoServer(8000).startServer();

    }
}
