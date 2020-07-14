package day23;

import org.junit.Test;

import java.io.*;

public class IOTest2 {
    /*
    1，流的分类：
        按照数据单位的不同：字节流(8bit，byte，适合),字符流(16bit，char，适合文本数据)
        按照流向分：输入流，输出流
        按照角色分：节点流，处理流
    2，四个抽象基类：
                    字节流         字符流
          输入流   InputStream     Reader
          输出流   OutputStream     Writer
       这四个类派生出来的子类名称都是以其父类名作为子类名后缀

    3，IO体系
    分类          字节流输入           字节流输出           字符流输入           字符流输出
  抽象基类       InputStream          OutputStream         Reader               Writer
  访问文件       FileInputStream      FileOutputStream     FileReader           FileWriter      (节点流)
  访问数据     ByteArrayInputStream ByteArrayOutputStream  CharArrayReader      CharArrayWriter
  访问管道     PipedInputStream       PipedOutputStream    PipedReader          PipedWriter
  访问字符串                                               StringReader         StringWriter
  缓冲流       BufferedInputStream  BufferedOutputStream   BufferedReader       BufferedWriter  (处理流)
  转换流                                                   InputStreamReader    OutputStreamWriter
  对象流       ObjectInputStream    ObjectOutputStream
               FilterInputStream    FilterOutputStream     FilterReader         FilterWriter
  打印流                             PrintStream                                PrintWriter
  推回输出流    PushbackInputStream                         PushbackReader
  特殊流        DataInputStream       DataOutputStream

    2，读入文件的过程：
        1，File类实例化
        2，FileReader流的实例化
        3，读入的操作
        4，资源的关闭

    3，写出文件的过程:
        1，提供File对象
        2，提供FileWriter对象
        3，写出操作
        4，关闭流

    4，说明：
        1,读的时候文件不存在抛异常
        2,写的时候如果文件不存在就创建，如果存在可以通过构造器指定
                在末尾添加或覆盖，FileWriter(fileName, false)//表示覆盖，true表示添加
        3,文本文件(txt,c,java,cpp)使用字符流处理，非文本文件(doc,mp3,...)使用字节流处理
            使用字节流处理字符文件可能出现乱码，使用字符流处理字节文件会无法打开
        4,文本文件如果只复制，用字节流复制，不在命令行读取也可以

     */


    public static void main(String[] args) {
        File file = new File("hello.txt");//相较于当前工程下
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void test2() {
        //File file = new File("hello.txt");//相较于当前目录下
        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        FileReader fr = null;
        try {
            //读入的文件不存在会报FileNotFoundException
            fr = new FileReader(file);
            int data;
            //read():返回读入的一个字符，如果到达文件末尾则返回-1
            while((data = fr.read()) != -1){
                System.out.println((char)data);
        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            //流关闭，一般jvm不会为流关闭，不关闭容易造成内存泄漏
            //需要用finally来处理
            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("test2 end");

    }

    @Test
    public void test1(){

        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        FileReader fr = null;
        char[] buffer = new char[16];
        int len = 0;
        try {
            fr = new FileReader(file);
            while((len = fr.read(buffer)) != -1){

                //错误的写法一：
                //for(int i = 0; i < buffer.length;i++)
                //错误的写法二：
                //System.out.println(new String(buffer));
                //原因，例如第一次读入abcd，第二次读入ef
                //此时buffer中为efcd
                //正确的写法1

                for(int i = 0; i < len; i++){
                    System.out.print(buffer[i]);
                }
                //正确的写法2
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void test3(){
        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        FileWriter fw = null;

        try{

            fw = new FileWriter(file,false);
            fw.write("test write\n");
            fw.write("end");


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fw != null){
                try{
                    fw.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test4(){
        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        FileReader fr = null;

        File file1 = new File(path,"test.txt");
        FileWriter fw = null;

        try{
            fr = new FileReader(file);
            fw = new FileWriter(file1,false);

            char[] buffer = new char[16];
            int len = 0;
            while((len = fr.read(buffer)) != -1){
                fw.write(buffer, 0, len);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fw != null){
                try{
                    fw.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            //就算上面有异常，下面也会执行
            if(fr != null){
                try{
                    fr.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test5(){
        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        FileInputStream fis = null;



        try{
            fis = new FileInputStream(file);

            byte[] buffer = new byte[16];
            int len = 0;
            while((len = fis.read(buffer)) != -1){
                System.out.println(new String(buffer, 0, len));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void test6(){
        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"捕获.PNG");
        FileInputStream fis = null;

        File file1 = new File(path,"复制.PNG");
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file1,false);

            byte[] buffer = new byte[16];
            int len = 0;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            //就算上面有异常，下面也会执行
            if(fos != null){
                try{
                    fos.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
