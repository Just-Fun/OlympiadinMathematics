package ua.com.juja;

import ua.com.juja.solvers.ExampleSolver;
import ua.com.juja.solvers.ExpressionParser;
import ua.com.juja.solvers.Solver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Logic {
    private File output;
    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();

    int taskLength;
    static volatile int nextTask = 0;

    public synchronized int getNextTask() {
        return nextTask++;
    }

    public void run(File input, File output) {
        this.output = output;
        clearFile(output);
        createNamesAndTasksFromFile(input, names, tasks);
        createThreadsAndStartEachOne(names);
    }

    public void clearFile(File output) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(output, false));
            out.write("");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNamesAndTasksFromFile(File input, List<String> names, List<String> tasks) {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            boolean switchOnTasks = false;
            while ((line = br.readLine()) != null) {
                if (line.equals("#")) {
                    switchOnTasks = true;
                } else {
                    if (!switchOnTasks) {
                        names.add(line);
                    } else {
                        tasks.add(line);
                    }
                }
            }
            taskLength = tasks.size();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void createThreadsAndStartEachOne(List<String> names) {
        for (String name : names) {
            new Thread(() -> {
                int index = getNextTask();
                while (index < taskLength) {
                    String task = tasks.get(index);
                    System.out.println(name + " взял задание: " + task);
                    String stringInFile = getAndResolveTask(name, task);
                    writeResultInFile(output.getAbsolutePath(), stringInFile);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    index = getNextTask();
                }
            }).start();
        }
    }

// Разные реализации :)
    private String getAndResolveTask(String name, String task) {
        String countResult = Solver.calculate(task);
//        String countResult = ExpressionParser.run(task);
//        String countResult = ExampleSolver.count(task);
        System.out.println(name + " решил задание, ответ: " + countResult);
        return name + ";" + task + ";" + countResult + "\n";
    }

    public static void writeResultInFile(String file, String str) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(str);
            writer.flush();
            System.out.print("Записано: " + str);
        } catch (IOException e1) {
            throw new RuntimeException("Не вышло записать в файл", e1.getCause());
        }
    }
}
