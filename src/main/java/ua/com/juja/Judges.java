package ua.com.juja;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serzh on 8/14/16.
 */
public class Judges {
//    Map<String, Map<Integer, Long>> map = new HashMap<>();
    Logic logic;

    public Judges(Logic logic) {
        this.logic = logic;
    }

    public void getWinners(){
        Map<String, Integer> sortedMap = MapUtil.sortByValue(logic.solvedTasks);

    }
}
