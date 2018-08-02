package NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelBuffer {

    public static void main(String[] args) {
        File fromFile=new File("D:/a.txt");
        File toFile=new File("D:/b.txt");

        FileInputStream fileInputStream=null;
        FileOutputStream  fileOutputStream=null;

        FileChannel inputChannel=null;
        FileChannel outChannel=null;

        try {
            fileInputStream=new FileInputStream(fromFile);
            fileOutputStream=new FileOutputStream(toFile);

            inputChannel=fileInputStream.getChannel();

            outChannel=fileOutputStream.getChannel();

            ByteBuffer buffer=ByteBuffer.allocate(1024);

            int num=0;
            while ((num=inputChannel.read(buffer))!=-1){
                buffer.flip();

                outChannel.write(buffer);
                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outChannel.close();
                inputChannel.close();

                fileOutputStream.close();
                fileInputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
