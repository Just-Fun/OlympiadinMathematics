package ua.com.juja;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/14/16.
 */
public class Participants {
    String name;
    long spentTimeOnTasks;
    int numberResolvedTasks;
    List<String> tasks;

    public Participants(String name) {
        this.name = name;
        spentTimeOnTasks = 0;
        numberResolvedTasks = 0;
        tasks = new ArrayList<>();
    }
}
