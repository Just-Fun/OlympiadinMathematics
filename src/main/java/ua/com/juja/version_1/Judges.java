package ua.com.juja.version_1;

import java.util.Map;

/**
 * Created by Serzh on 8/14/16.
 */
public class Judges {
    Logic logic;

    public Judges(Logic logic) {
        this.logic = logic;
    }

    public String getWinners(){
        Map<String, Integer> sortedMap = MapUtil.sortByValue(logic.solvedTasks);
        String result = "";
        for (int i = 0; i < 3; i++) {
            String name = sortedMap.keySet().toArray()[i].toString();
            String numberOfTasks = sortedMap.values().toArray()[i].toString();
            Long spentTime = logic.spentTime.get(name);
            result += name + ";" + numberOfTasks + ";" + String.valueOf(spentTime) + "\n";
        }
        System.out.println("Тройка победителей: \n" + result);
        return result;
    }
}
