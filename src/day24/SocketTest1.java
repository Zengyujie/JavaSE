package day24;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class SocketTest1 {
    /*
    端口号分类：
    公认端口：0-1023
    注册端口：1024-49151
    动态/私有端口：49152-65535

     */

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName("192.168.0.1");
            System.out.println(ia);
            ia = InetAddress.getByName("https://www.baidu.com");
            System.out.println(ia);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception{
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet,51233);
        OutputStream os = socket.getOutputStream();
        os.write("你好".getBytes());
        socket.shutdownOutput();
        os.close();
        socket.close();
    }


    @Test
    public void test2() throws Exception{
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        ServerSocket server = new ServerSocket(51233);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[64];
        while((len = is.read(buffer))!= -1){
            bos.write(buffer,0,len);
        }
        System.out.println(bos.toString());
        bos.close();
        is.close();
        socket.close();
        server.close();
    }

    @Test
    public void test3() throws Exception{
        DatagramSocket socket = new DatagramSocket();

        String str = "test";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);
        socket.send(packet);
        socket.close();
    }

    @Test
    public void test4() throws Exception{
        DatagramSocket socket = new DatagramSocket(9090);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        socket.close();
    }


    @Test
    public void test5(){

    }
}
