package NIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {

    public static void main(String[] args) {
        String [] words={"haha","heihei","hehe"};

        File file=new File("D:/a.txt");

        FileOutputStream fos=null;

        FileChannel fileChannel=null;


        try {
            fos=new FileOutputStream(file);
            fileChannel=fos.getChannel();
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            for(String s:words){
                buffer.put(s.getBytes());
            }
            buffer.flip();
            fileChannel.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                fileChannel.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
