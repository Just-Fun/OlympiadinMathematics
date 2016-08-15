package ua.com.juja.version_2;

/**
 * Created by Serzh on 8/14/16.
 */
public class Student2 {
    private String name;
    private int tasks;
    private int time;

    public Student2(String name, int tasks, int time) {
        this.name = name;
        this.tasks = tasks;
        this.time = time;
    }

    public int getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }


}
