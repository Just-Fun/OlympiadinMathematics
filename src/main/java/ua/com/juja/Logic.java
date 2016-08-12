package ua.com.juja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Logic {
    private File input;
    private File output;

    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();
    List<Thread> threads = new ArrayList<>();

    static int nextTask = 0;
    int taskLength = tasks.size();

    public void run(File input, File output) {
        this.input = input;
        this.output = output;
        createNamesAndTasksFromFile(input, names);
        makeThread();
        startThreads();
    }

    private void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void createNamesAndTasksFromFile(File input, List<String> names) {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            boolean switchOnTasks = false;
            while ((line = br.readLine()) != null) {
                if (line.equals("#")) {
                    switchOnTasks = true;
                }
                if (!switchOnTasks) {
                    names.add(line);
                } else {
                    if (!line.equals("#")) {
                        tasks.add(line);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void makeThread() {
        for (String name : names) {
            Thread thread = new Thread(() -> {
                synchronized (Logic.class) {
                    while (nextTask++ < taskLength) {
                        String stringInFile = getAndResolveTask(name);
                        writeResultInFile(output.getAbsolutePath(), stringInFile);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threads.add(thread);
        }
    }

    private String getAndResolveTask(String name) {
        String task = tasks.get(nextTask);
        String countResult = count(task);
        return name + ";" + task + ";" + countResult;
    }

    private String count(String task) {
        // calculate
        return "";
    }

    public static void writeResultInFile(String file, String str) {
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
