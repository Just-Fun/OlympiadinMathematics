package ua.com.juja;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Users {
    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();
    List<Thread> threads = new ArrayList<>();
    static int nextTask = 0;
    int taskLength = tasks.size();

    public Users(List<String> names, List<String> tasks) {
        this.names = names;
        this.tasks = tasks;
    }

    public void makeThread() {
        for (String name : names) {
            Thread thread = new Thread(() -> {
                someLogic(name);
            });
            threads.add(thread);
        }
    }

    private synchronized void someLogic(String name) {
        synchronized (Users.class) {
            while (nextTask++ < taskLength) {
                String task = tasks.get(nextTask);
                StringToFile(name, task);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String StringToFile(String name, String task) {
        String result = count(task);
        return name + ";" + task + ";" + result;
    }

    private String count(String task) {
        // calculate
        return "";
    }
}
