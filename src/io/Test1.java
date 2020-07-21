package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Test1 {

    public static void main(String[] args) throws IOException {
        //从目标文件中创面
        FileInputStream fileInputStream = new FileInputStream("D:\\downloads\\music\\王菲\\2000《寓言》内地引进版\\寓言 内地引进版.cue");
        FileChannel channel = fileInputStream.getChannel();



        //旧文件
        FileChannel newChannel = new FileOutputStream(new File("new.txt")).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(100);

        while (channel.read(buffer) != -1) {
            //做好写入准备
            buffer.flip();
            newChannel.write(buffer);
            //写入完成后清除文件
            buffer.clear();
        }

        channel.close();
        newChannel.close();

    }

}
