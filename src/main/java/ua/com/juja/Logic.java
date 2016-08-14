package ua.com.juja;

import ua.com.juja.solvers.SimpleSolver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serzh on 8/12/16.
 */
public class Logic {
    private File output;
    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();
    Map<String, Long> spentTime = new HashMap<>();
    Map<String, Integer> solvedTasks = new HashMap<>();
    ThreadGroup tg = new ThreadGroup("threadGroup");

    int taskLength;
    static volatile int nextTask = 0;
    private Object monitor;

    public Logic(Object monitor) {
        this.monitor = monitor;
    }

    public synchronized int getNextTask() {
        return nextTask++;
    }

    public void run(File input, File output) {
        this.output = output;
        clearFile(output);
        createNamesAndTasksFromFile(input, names, tasks);
        createMapsSolvedTaskAndSpentTime();
        createAndStartThreads(names);
        winners();
    }

    private void winners() {
        boolean end = false;
        while (!end) {
            if (tg.activeCount() == 0) {
                Judges judges = new Judges(this);
                System.out.println("судьи считают");
                judges.getWinners();
                end = true;
            }
        }
    }

    private void createMapsSolvedTaskAndSpentTime() {
        for (String name : names) {
            spentTime.put(name, new Long(0));
            solvedTasks.put(name, new Integer(0));
        }
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

    public void createAndStartThreads(List<String> names) {
        for (String name : names) {
            Thread thread = new Thread(tg, () -> {
                int index = getNextTask();
                while (index < taskLength) {
                    doJob(name, index);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    index = getNextTask();
                }
            })/*.start()*/;
            thread.start();
        }
    }

    private void doJob(String name, int index) {
        String nextTask = tasks.get(index);
        System.out.println(name + " взял задание: " + nextTask);
        long start = System.currentTimeMillis();
        String stringInFile = resolveTask(name, nextTask);
        long finish = System.currentTimeMillis();
        incrementSpentTime(name, start, finish);
        writeResultInFile(output.getAbsolutePath(), stringInFile, name);
        incrementSolverTasks(name);
    }

    private String resolveTask(String name, String task) {
// Разные реализации:
        String countResult = SimpleSolver.calculate(task);
//        String countResult = ExpressionParser.run(task);
//        String countResult = ExampleSolver.count(task);
        System.out.println(name + " решил задание, ответ: " + countResult);
        return name + ";" + task + ";" + countResult + "\n";
    }

    public void writeResultInFile(String file, String str, String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(str);
            writer.flush();
            System.out.print("Записано: " + str);
        } catch (IOException e1) {
            throw new RuntimeException("Не вышло записать в файл по причине: ", e1.getCause());
        }
    }

    private void incrementSpentTime(String name, long start, long finish) {
        long spentTimeOnTask = finish - start;
        Long newTime = new Long(spentTime.get(name) + spentTimeOnTask);
        spentTime.put(name, newTime);
    }

    private void incrementSolverTasks(String name) {
        Integer integer = new Integer(solvedTasks.get(name) + 1);
        solvedTasks.put(name, integer);
    }
}
