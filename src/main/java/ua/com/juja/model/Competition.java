package ua.com.juja.model;

import ua.com.juja.solvers.Solver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Competition {
    private File output;
    private File input;
    private String outputPath;
    private Solver solver;
    private static volatile int nextTask = 0;
    private int taskLength;

    private List<Student> students = new ArrayList<>();
    private List<String> tasks = new ArrayList<>();
    private ThreadGroup tg = new ThreadGroup("threadGroup");

    private synchronized int getNextTask() {
        return nextTask++;
    }

    public Competition(File namesAndTasks, File output, Solver solver) {
        this.input = namesAndTasks;
        this.output = output;
        outputPath = output.getAbsolutePath();
        this.solver = solver;
    }

    public void run() {
        Utils.clearFile(output);
        createNamesAndTasksFromFile();
        createAndStartThreads(students);
        boolean end = false;
        while (!end) {
            if (tg.activeCount() == 0) {
                winners();
                end = true;
            }
        }
    }

    private void createNamesAndTasksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            boolean task = false;
            while ((line = br.readLine()) != null) {
                if (line.equals("#")) {
                    task = true;
                } else {
                    if (!task) {
                        Student student = new Student(line);
                        students.add(student);
                    } else {
                        tasks.add(line);
                    }
                }
            }
            taskLength = tasks.size();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Invalid file path: " + ex.getLocalizedMessage());
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.getLocalizedMessage());
        }
    }

    private void createAndStartThreads(List<Student> students) {
        for (Student student : students) {
            new Thread(tg, () -> {
                int taskIndex = getNextTask();
                while (taskIndex < taskLength) {
                    takeTask(student, taskIndex);
                    taskIndex = getNextTask();
                }
            }).start();
        }
    }

    private void takeTask(Student student, int taskIndex) {
        System.out.printf("%s started to task №%d%n", student.getName(), taskIndex + 1);
        resolveTask(student, taskIndex);
    }

    private void resolveTask(Student student, int taskIndex) {
        String task = tasks.get(taskIndex);
        long start = System.nanoTime();
        String countResult = solver.count(task);
        long spentTime = System.nanoTime() - start;

        String name = student.getName();
        System.out.printf("%s spent on task №%d: %d nanoseconds , result: %s = %s,  %n", name, taskIndex + 1, spentTime, task, countResult);
        student.incrementSpentTimeAndResolvedTasks(spentTime);
        String stringInFile = String.format("%s;%s;%s\n", name, task, countResult);
        Utils.writeResultInFile(outputPath, stringInFile);
    }

    private void winners() {
        Judges judges = new Judges(students);
        String result = judges.getWinners();
        Utils.writeResultInFile(outputPath, "Winners:\n");
        Utils.writeResultInFile(outputPath, result);
    }
}