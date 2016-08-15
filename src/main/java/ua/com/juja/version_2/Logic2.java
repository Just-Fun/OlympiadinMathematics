package ua.com.juja.version_2;

import ua.com.juja.solvers.Example;
import ua.com.juja.solvers.ExampleSolver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Logic2 {
    private File output;
    private static volatile int nextTask = 0;

    List<Student> students = new ArrayList<>();
    List<String> tasks = new ArrayList<>();

    ThreadGroup tg = new ThreadGroup("threadGroup");

    private int taskLength;

    private synchronized int getNextTask() {
        return nextTask++;
    }

    public void run(File input, File output) {
        this.output = output;
        Utils.clearFile(output);
        createNamesAndTasksFromFile(input);
        createAndStartThreads(students);
        winners();
    }

    private void createNamesAndTasksFromFile(File input) {
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
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
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

    private void winners() {
        boolean end = false;
        while (!end) {
            if (tg.activeCount() == 0) {
                Judges2 judges = new Judges2(students);
//                System.out.println("судьи считают");
                String result = judges.getWinners();
                Utils.writeResultInFile(output.getAbsolutePath(), "Winners:");
                Utils.writeResultInFile(output.getAbsolutePath(), result);
                end = true;
            }
        }
    }

    private void takeTask(Student student, int taskIndex) {
        System.out.println(student.getName() + " started to task №" + (taskIndex + 1));
        resolveTask(student, taskIndex);
    }

    private void resolveTask(Student student, int taskIndex) {
        Example solver = new ExampleSolver();
        String task = tasks.get(taskIndex);
        long start = System.nanoTime();
        // Разные реализации:
//        String countResult = ExpressionParser.run(task);
//        String countResult = ExampleSolver.count(task);
        String countResult = solver.count(task);
        long spentTime = System.nanoTime() - start;

        String name = student.getName();
        System.out.println(name + " spent on task №" + (taskIndex + 1) + ": " + spentTime + " nanoseconds , result: " + task + " = "+ countResult + ",  ");
        student.incrementSpentTimeAndResolvedTasks(spentTime);
        String stringInFile = name + ";" + task + ";" + countResult + "\n";
        Utils.writeResultInFile(output.getAbsolutePath(), stringInFile);
    }
}