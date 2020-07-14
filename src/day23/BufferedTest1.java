package day23;

import org.junit.Test;

import java.io.*;

public class BufferedTest1 {

    /*
    缓冲流：

    1，一般在开发中不直接用FileInputStream等四个，因为效率低
    2，缓冲流的作用就是提高读取速度，作为处理流接在节点流上提升效率
    3，缓冲流快的原因：内部提供了一个缓冲区


     */

    @Test
    public void test1() {


        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"捕获.PNG");
        BufferedInputStream bis = null;

        File file1 = new File(path,"复制.PNG");
        BufferedOutputStream bos = null;

        try{
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(new FileOutputStream(file1));
            byte[] buffer = new byte[16];
            int len;

            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0, len);
                //bos.flush();
                //没必要加，因为write会调用
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try{
                //在关闭时先关闭外侧的流再关闭内测的流
                //说明：再调用外侧关闭时也会调用该内测的关闭
                //再手动关闭内层也可以，但是没有必要了
                if(bis != null){
                    bis.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }

            try{
                if(bos != null){
                    bos.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }


    }

    @Test
    public void test2(){

        File path = new File("D:\\java\\idea_workspace\\silicon_java\\src\\day23\\IOFileWareHouse");
        File file = new File(path,"haha.txt");
        BufferedReader br = null;

        File file1 = new File(path,"test.txt");
        BufferedWriter bw = null;

        try{
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(file1));
            char[] buffer = new char[16];
            int len = 0;
//            while((len = br.read(buffer)) != -1){
//                    //包含了换行
//                bw.write(buffer,0,len);
//            }

            //或者
            String data = null;
            while((data = br.readLine()) != null){
                bw.write(data);
                //readLine不包含换行符，所以要加上
                bw.write("\n");
                //或者
                //bw.newLine();

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

            try{
                if(bw != null){
                    bw.close();
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }


    }
}
