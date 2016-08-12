package ua.com.juja;

import java.io.*;

/**
 * Created by Serzh on 8/12/16.
 */
public class Main {
    public static void main(String[] args) {
        File input = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");
        doWork(input, output);
    }

    public static void doWork(File input, File output) {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null && !(line = br.readLine()).equals("#")) {
                // something with threads
                // process the line.
            }

            while ((line = br.readLine()) != null) {
                // solved examples
                // process the line.
            }
        }catch (FileNotFoundException ex){
            System.out.println("FileNotFoundException");
        }catch (IOException e) {
            System.out.println("IOException");
        }
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
