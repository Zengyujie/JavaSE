package JUC_test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadSecuritySetTest1 {
    /*


     */

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();

        set = Collections.synchronizedSet(set);
        //同理，小规模可以

        set = new CopyOnWriteArraySet();
        set = new ConcurrentSkipListSet();



        for(int i = 0; i < 3; i++){

            Set<String> finalSet = set;
            new Thread(()->{
                finalSet.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(finalSet);
            }).start();
        }


    }
}
