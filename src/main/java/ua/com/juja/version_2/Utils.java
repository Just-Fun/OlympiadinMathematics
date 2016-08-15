package ua.com.juja.version_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Serzh on 8/15/16.
 */
public class Utils {

    public static void clearFile(File output) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(output, false));
            out.write("");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeResultInFile(String file, String str) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(str);
            writer.flush();
            System.out.print("Записано: \n" + str);
        } catch (IOException e1) {
            throw new RuntimeException("Не вышло записать в файл по причине: ", e1.getCause());
        }
    }
}
