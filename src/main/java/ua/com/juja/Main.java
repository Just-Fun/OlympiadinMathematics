package ua.com.juja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 8/12/16.
 */
public class Main {
    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        File input = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");
        new Main().doWork(input, output);
    }

    private void doWork(File input, File output) {
        readFile(input);
        new Users(names, tasks, output).makeThread();
    }

    public void readFile(File input) {
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


}
