package JUC_test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSecurityMapTest1 {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();

        map = Collections.synchronizedMap(map);
        //小规模可以

        map = new ConcurrentHashMap<String, String>();


        for(int i = 0; i < 3; i++){

            Map<String,String> finalMap = map;
            new Thread(()->{
                finalMap.put(UUID.randomUUID().toString().substring(0,8),"test");
                System.out.println(finalMap);
            }).start();
        }


    }
}
