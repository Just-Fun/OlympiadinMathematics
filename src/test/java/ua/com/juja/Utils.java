package ua.com.juja;

import java.io.*;

/**
 * Created by Serzh on 8/12/16.
 */
public class Utils {

    public String read(File file) {
        String result = "";
        BufferedReader reader = null;
        try {
            String currentLine;
            reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                result += currentLine + "\n";
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
