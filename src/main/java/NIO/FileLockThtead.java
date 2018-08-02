package NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileLockThtead {
    public static void main(String[] args) {
        FileChannel channel=null;

        try {
            RandomAccessFile file=new RandomAccessFile("D:/java.pdf","rw");

            channel=file.getChannel();

            MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY,0,channel.size());
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            byte [] bytes=new byte[1024];
            long length=file.length();
            long beginTime=System.currentTimeMillis();
            for (int i=0;i<length; i+=1024){
                if (length-i>1024){
                    buffer.get(bytes);
                }else {
                    buffer.get(new byte[(int)(length-i)]);
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.println("使用内存映射时间："+(endTime-beginTime));
            System.out.println("*************************************");
            beginTime=System.currentTimeMillis();
            while (channel.read(byteBuffer)>0){
                byteBuffer.flip();
                byteBuffer.clear();
            }
            endTime=System.currentTimeMillis();
            System.out.println("使用普通io时间;"+(endTime-beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
