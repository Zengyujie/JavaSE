package day23;

import org.junit.Test;

import java.io.*;

public class TransformStreamTest1 {
    /*
    转换流：
    1，实现字符流和字节流的转换
    2，InputStreamReader:字节流转换为字符的输入流
    3，OutputStreamWriter:将字符的输出流转换为字节的输出流
    4，字符集

     */


    @Test
    public void test1(){

        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        InputStreamReader isr = null;

        File file1 = new File(path,"test.txt");
        OutputStreamWriter osw = null;

        try{
            isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
            //参数二指明了字符集，根据文件的存储形式决定
            osw = new OutputStreamWriter(new FileOutputStream(file1),"gbk");
            char[] buffer = new char[16];
            int len = 0;

            while((len = isr.read(buffer)) != -1){
                //System.out.println(new String(buffer,0,len));
                osw.write(buffer,0,len);
            }


        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(isr != null){
                    isr.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

            try{
                if(osw != null){
                    osw.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }


    }
}
