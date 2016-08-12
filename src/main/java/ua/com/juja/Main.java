package ua.com.juja;

import java.io.*;

/**
 * Created by Serzh on 8/12/16.
 */
public class Main {
    public static void main(String[] args) {
        File input = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");

        Logic logic = new Logic();
        logic.run(input, output);
    }


}
