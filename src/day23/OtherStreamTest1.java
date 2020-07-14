package day23;

import org.junit.Test;

import java.io.*;

public class OtherStreamTest1 {

    /*
    1，标准输入输出流
        System.in:InputStream:控制输入流，默认从键盘输入
        System.out:PrintWriter：标准输出流，输出到控制台
        1.1System的setIn(InputStream is)/setOut(PrintWriter pw)方法重新制定了输入和输出流
    2，打印流
        1，PrintStream和PrintWriter不会抛出IOException
        2，有自动flush功能
        3，提供了一些列重载的print和println方法

    3，数据流
        DataInputStream,DataOutputStream
        1，作用时操作基本数据类型和String
        2，读取数据的顺序要与写入的顺序一致，否则会抛异常
        3，写了需要刷新

    4，对象流
        ObjectInputStream,ObjectOutputStream
        1，可以存和读基本数据类型或对象
        2，序列化，ObjectOutputStream，把对象写出去
        3，反序列化，ObjectInputStream，把对象读进来
        4，对象必须是可序列化的
        5，类必须实现以下任意一个接口
            Serializable
            Externalizable
        6，类必须提供一个常量：serialVersionUID用于提供类标识，不加会出问题
            如果不写会自动生成，但是若类的实例变量做了修改，就可能出问题
            java序列化机制是通过SerialVersionUID来验证版本一致性，序列化时会
            把字节流中的UID和本地实体类的UID比较，若相同则可以反序列化，否则会出异常
            (InvalidCastException)，
            对于自动生成的UID，当对类进行修改后，系统会生成新的UID
        7，对于序列化的类，需要保证其内部的 属性是可序列化的，基本数据类型都是可以序列化的
        8，static和transient修饰的值是无法序列化的，反序列化之后会变成默认值

    5，随机存取文件流
        1，RandomAccessFile，既可以做一个输入流也可以作为输出流
        2，直接继承Object，实现了DateInput和DataOutput接口
        3，RandomAccessFile作为一个输出流，写出到的文件不存在则创建
            若存在，则对原有内容从开头开始覆盖，能盖多少算多少
    6，ByteArrayOutputStream 相当于一个缓冲

     */

    @Test
    public void test1(){
        //idea不支持单元测试的键盘输入
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while((str = br.readLine()) != null){
                System.out.println(str);
                if(str.equals("e")){
                    break;
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(br != null){
                    br.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }


    @Test
    public void test2(){
        DataOutputStream dos = null;

        try{
            dos = new DataOutputStream(new FileOutputStream(new File("test.txt")));
            dos.write(1);
            dos.flush();
            dos.writeFloat(1.1f);
            dos.flush();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(dos != null){
                    dos.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }


    @Test
    public void test3(){

        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");

        ObjectOutputStream oos = null;

        try{
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(new String("test"));
            oos.flush();
            oos.writeObject(new Per());
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(oos != null){
                    oos.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void test4(){

        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");

        ObjectInputStream ois = null;

        try{
            ois = new ObjectInputStream(new FileInputStream(file));
            Object o = ois.readObject();
            if(o instanceof String){
                System.out.println(o);
            }
            o = ois.readObject();
            if(o instanceof Per){
                System.out.println(o);
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(ois != null){
                    ois.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void test5(){

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;

        try{
            raf1 = new RandomAccessFile(new File("test.txt"),"r");
            raf2 = new RandomAccessFile(new File("new.txt"),"rw");
            //r:只读 rw读写 rwd读写加内容实时更新 rws读写加内容和元数据实时更新

            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = raf1.read(buffer))!= -1){
                raf2.write(buffer,0,len);
            }
            raf2.seek(3);//从第三个位置开始覆盖
            raf2.write(2);
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(raf1 != null){
                    raf1.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

            try{
                if(raf2 != null){
                    raf2.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }



    }

}


class Per implements Serializable{

    public static final long serialVersionUID = 1324543456734L;

    @Override
    public String toString() {
        return "Per{}";
    }
}