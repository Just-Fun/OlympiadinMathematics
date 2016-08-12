package ua.com.juja;

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
                }
                if (!switchOnTasks) {
                    names.add(line);
                } else {
                    if (!line.equals("#")) {
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
                while (nextTask < taskLength) {
                    int index = getNextTask();
                    if (index >= taskLength) { // TODO костыль, подумать как убрать
                        break;
                    }
                    String stringInFile = getAndResolveTask(name, index);
                    writeResultInFile(output.getAbsolutePath(), stringInFile);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private String getAndResolveTask(String name, int index) {
        String task = tasks.get(index);
        String countResult = Solver.calculate(task);
//        String countResult = ExampleSolver.count(task); // TODO попробовать реализацию Оли
        String result = name + ";" + task + ";" + countResult + "\n";
        return result;
    }

    public static void writeResultInFile(String file, String str) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(str);
            writer.flush();
            System.out.print("Записано в файл " + str);
        } catch (IOException e1) {
            throw new RuntimeException("Не вышло записать в файл", e1.getCause());
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не вышло закрыть writer", e.getCause());
                }
        }
    }
}
