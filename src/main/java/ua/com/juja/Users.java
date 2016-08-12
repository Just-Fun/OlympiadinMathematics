package ua.com.juja;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Users {
    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();
    private File output;
    List<Thread> threads = new ArrayList<>();
    static int nextTask = 0;
    int taskLength = tasks.size();

    public Users(List<String> names, List<String> tasks, File output) {
        this.names = names;
        this.tasks = tasks;
        this.output = output;
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

    private void StringToFile(String name, String task) {
        String result = name + ";" + task + ";" + count(task);
        write(output.getAbsolutePath(), result);
    }



    private String count(String task) {
        // calculate
        return "";
    }

    public static void write(String file, String str) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(str);
            writer.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
