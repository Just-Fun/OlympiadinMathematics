package ua.com.juja;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Serzh on 8/12/16.
 */
public class LogicTest {
    List<String> names = new ArrayList<>();
    List<String> tasks = new ArrayList<>();
    File input = new File("src/test/resources/testInput.txt");
    File output = new File("src/test/resources/testOutput.txt");

    Logic logic;

    @Before
    public void setup() {
        logic = new Logic();
    }


    @Test
    public void run() throws Exception {

        logic.run(input, output);

        Utils utils = new Utils();
        String check = utils.read(output);
        assertEquals(("Вася;1+1;2\n" +
                "Коля;2*3+4/5;6,8\n" +
                "Юля;4/9;0,45\n" +
                "Юля;1*2*3*4*5*6;720\n" +
                "Оля;(7-9)/6;-0,34\n" +
                "Вася;(6-(2+(1*3)*2));-2").replaceAll("\r\n", "\n"), check);

    }

    @Test
    public void createNamesAndTasksFromFile() throws Exception {
        logic.createNamesAndTasksFromFile(input, names);
        assertEquals("", );
    }

    @Test
    public void makeThread() throws Exception {

    }

    @Test
    public void writeResultInFile() throws Exception {

    }

}