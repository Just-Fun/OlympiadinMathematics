package ua.com.juja;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serzh on 8/14/16.
 */
public class ForTests {
    public static void main(String[] args) {

        Map<String, Integer> solvedTasks = new HashMap<>();
        solvedTasks.put("Vasia", new Integer(0));
        Integer integer = solvedTasks.get("Vasia");
        Integer integer1 = new Integer(integer + 1);
        System.out.println(integer1);
        System.out.println(Arrays.asList(solvedTasks));

    }
}
