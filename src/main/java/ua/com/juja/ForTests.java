package ua.com.juja;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serzh on 8/14/16.
 */
public class ForTests {
    public static void main(String[] args) {
        Map<String, Integer> test = new HashMap<>();
        test.put("Vasia", new Integer(4));
        test.put("Petia", new Integer(1));
        test.put("Dima", new Integer(2));
        test.put("Olia", new Integer(5));
        test.put("Misha", new Integer(4));

        Map<String, Long> spentTime = new HashMap<>();
        spentTime.put("Vasia", new Long(4356));
        spentTime.put("Petia", new Long(124366));
        spentTime.put("Dima", new Long(223));
        spentTime.put("Olia", new Long(5234));
        spentTime.put("Misha", new Long(4567));


        Map<String, Integer> sortedMap = MapUtil.sortByValue(test);
        for (int i = 0; i < 3; i++) {
            Object name = sortedMap.keySet().toArray()[i];
            Object numberOfTasks = sortedMap.values().toArray()[i];
            System.out.print(name);
            System.out.print(";");
            System.out.print(numberOfTasks);
            System.out.print(";");
            System.out.println(spentTime.get(name));
        }

        System.out.println(Arrays.asList(sortedMap));

        /*Map.Entry<String,Integer> entry=sortedMap.entrySet().iterator().next();
        String key= entry.getKey();
        Integer value=entry.getValue();*/
    }
}
