package NIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class testshow {
    @Test
    public void  test1() throws  Exception{
        FileInputStream fis=new FileInputStream("D:/cat.jpg");
        FileOutputStream fos=new FileOutputStream("D:/cat2.jpg");

        FileChannel inchannel=fis.getChannel();
        FileChannel outchannel=fos.getChannel();

        ByteBuffer buffer=ByteBuffer.allocate(1024);

        while (inchannel.read(buffer)!=-1){
            buffer.flip();
            outchannel.write(buffer);

            buffer.clear();
        }
        outchannel.close();
        inchannel.close();
        fis.close();
        fos.close();

    }
}
