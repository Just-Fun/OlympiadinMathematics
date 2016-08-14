package ua.com.juja;

/**
 * Created by Serzh on 8/14/16.
 */
public class Participants {
    String name;
    long psentTimeOnTasks;
    int numberResolvedTasks;

    public Participants(String name) {
        this.name = name;
        psentTimeOnTasks = 0;
        numberResolvedTasks = 0;
    }
}
