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
    long speed;

    List<Student> students = new ArrayList<>();
    List<String> tasks = new ArrayList<>();

    ThreadGroup tg = new ThreadGroup("threadGroup");

    int taskLength;
    static volatile int nextTask = 0;

    public synchronized int getNextTask() {
        return nextTask++;
    }

    public void run(File input, File output, long sleepTime) {
        this.output = output;
        this.speed = sleepTime;
        Utils.clearFile(output);
        createNamesAndTasksFromFile(input);
        createAndStartThreads(students);
        winners();
    }

    public void createNamesAndTasksFromFile(File input) {
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

    public void createAndStartThreads(List<Student> students) {
        for (Student student : students) {
            Thread thread = new Thread(tg, () -> {
                int taskIndex = getNextTask();
                while (taskIndex < taskLength) {
                    takeTask(student, taskIndex);
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    taskIndex = getNextTask();
                }
            })/*.start()*/;
            thread.start();
        }
    }

    private void winners() {
        boolean end = false;
        while (!end) {
            if (tg.activeCount() == 0) {
                Judges2 judges = new Judges2(students);
//                System.out.println("судьи считают");
                String result = judges.getWinners();
                Utils.writeResultInFile(output.getAbsolutePath(), result);
                end = true;
            }
        }
    }

    private void takeTask(Student student, int taskIndex) {
        String task = tasks.get(taskIndex);
        System.out.println(student.getName() + " взял задание №" + (taskIndex + 1) + ": " + task);
        resolveTask(student, taskIndex);
    }

    private void resolveTask(Student student, int taskIndex) {
        String name = student.getName();
        String task = tasks.get(taskIndex);
        Example solver = new ExampleSolver();
        long start = System.nanoTime();
        // Разные реализации:
//        String countResult = ExpressionParser.run(task);
//        String countResult = ExampleSolver.count(task);
        String countResult = solver.count(task);
        long spentTime = System.nanoTime() - start;
        System.out.println(name + " потратил на решение задания №" + (taskIndex + 1) + ": " + spentTime + " наносекунд , ответ: " + countResult + ",  ");
        student.incrementSpentTimeAndResolvedTasks(spentTime);
        String stringInFile =  name + ";" + task + ";" + countResult + "\n";
        Utils.writeResultInFile(output.getAbsolutePath(), stringInFile);
    }
}
