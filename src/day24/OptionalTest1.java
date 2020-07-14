package day24;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest1 {

    /*
    Optional类：
    为解决Java的空指针异常问题，在java.util.Optional，可以保存类型T的值
    也可以存null值


     */

    @Test
    public void test1(){
        Boy boy = new Boy();
        Optional<Boy> op = Optional.of(boy);//创建一个Optional实例，参数必须非空
        Optional<Girl> op1 = Optional.empty();//创建一个null的Optional实例


    }

    public String getBoyName(Girl girl){
        return girl.getBoy().getName();//容易有空指针异常
    }

    public String getBoyName1(Girl girl){
        if(girl == null){
            return null;
        }
        return girl.getBoy().getName();
    }

    public String getBoyName2(Girl girl){
        Optional<Girl> op = Optional.ofNullable(girl);//可传空
        op.orElse(new Girl(new Boy()));//如果为空返回参数
        op.get().getBoy().setName("test");
        return op.get().getBoy().getName();
    }


}

class Boy{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Girl{
    private Boy boy;

    public Girl(Boy boy) {
        this.boy = boy;
    }

    public Boy getBoy() {
        return boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }
}

