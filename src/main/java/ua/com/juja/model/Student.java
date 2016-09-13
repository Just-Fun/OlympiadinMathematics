package ua.com.juja.model;

/**
 * Created by Serzh on 8/14/16.
 */
public class Student {
    private String name;
    private long spentTimeOnTasks;
    private int numberResolvedTasks;

    public Student(String name) {
        this.name = name;
        spentTimeOnTasks = 0;
        numberResolvedTasks = 0;
    }

    public void incrementSpentTimeAndResolvedTasks(long spentTime) {
        spentTimeOnTasks += spentTime;
        numberResolvedTasks++;
    }

    public String getName() {
        return name;
    }

    public long getSpentTimeOnTasks() {
        return spentTimeOnTasks;
    }

    public int getNumberResolvedTasks() {
        return numberResolvedTasks;
    }
}
