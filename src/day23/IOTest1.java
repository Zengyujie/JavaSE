package day23;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class IOTest1 {
    /*
    1，File类的一个对象，代表一个文件或一个文件目录(文件夹)
    常见方法：
    String getAbsolutePath()
    String getPath()
    String getName()
    String getParent()
    long length()//字节数，不能获取目录的字节数
    long lastModified()//最后一次修改的时间
    String[] list():获取目录下的名字
    Files[] listFiles():获取目录下的文件
    boolean renameTo(File f):文件重命名，要想返回true，需要调用的file存在，f不存在
        重命名时可以移动文件
    boolean isDirectory()
    boolean isFile()
    boolean exists():文件是否存在
    boolean canRead()
    boolean canWrite()
    boolean isHidden()
    boolean createNewFile():创建文件，若已存在则不创建，返回false
    boolean mkdir():创建目录，若已存在则返回false，如果上层目录不存在就不创建了
    boolean mkdirs():创建目录，若目录已存在则不创建返回false，若上层目录不存在则一起创建
    boolean delete()：删除File，不走回收站，要删除目录时需要保证目录中不能有文件或者子目录

    2，File只设计文件或目录的增删改查等方法，如果要读写内容需要使用IO流
     */
    @Test
    public void test1(){
        //相对路径
        File file =  new File("hello.txt");
        //绝对路径：包含盘符
        File file1 = new File("D:" + File.separator +"workspace\\day23\\he.txt");
        File file3 = new File("D:\\workspace","test1\\");
        File file4 = new File(file3,"ht.txt");



    }

    @Test
    public void test2(){
        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Stack<File> stack = new Stack<>();
        stack.add(path);
        while(stack.empty()){
            File temp = stack.pop();
            if(temp.isDirectory()){
                File[] files = temp.listFiles();
                for(File f : files){
                    stack.push(f);
                }
            }else{
                System.out.println(temp);
                if(temp.getName().endsWith("jpg")){
                    temp.delete();
                }
            }
        }
    }

}
