package ua.com.juja;

import java.io.*;

/**
 * Created by Serzh on 8/12/16.
 */
public class JustForTesting {
    public static void main(String[] args) {
    }


    public static void createFile(String path) {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
