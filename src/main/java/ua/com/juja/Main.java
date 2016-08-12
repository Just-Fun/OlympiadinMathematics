package ua.com.juja;

import java.io.*;

/**
 * Created by Serzh on 8/12/16.
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("/src/main/resurses/test.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
}
