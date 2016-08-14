package ua.com.juja;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    Utils utils;

    @Before
    public void setup() {
        logic = new Logic();
        utils = new Utils();
    }

    @Ignore // при реализованной многопоточности выдает разные результаты
    @Test
    public void run() throws Exception {
        logic.run(input, output);
        String check = utils.read(output);
        assertEquals(("Оля;2*3+4/5;99\n" +
                "Вася;1+1;99\n" +
                "Коля;4/9;99\n" +
                "Юля;1*2*3*4*5*6;99\n" +
                "Оля;(7-9)/6;99\n" +
                "Вася;(6-(2+(1*3)*2));99").replaceAll("\r\n", "\n"), check);

    }

    @Test
    public void createNamesAndTasksFromFile() throws Exception {
        logic.createNamesAndTasksFromFile(input, names, tasks);
        assertEquals("[[Вася, Оля, Коля, Юля]]", Arrays.asList(names).toString());
        assertEquals("[[1+1, 2*3+4/5, 4/9, 1*2*3*4*5*6, (7-9)/6, (6-(2+(1*3)*2))]]", Arrays.asList(tasks).toString());
    }

    @Test
    public void writeResultInFile() throws Exception {
        File file = new File("src/test/resources/testOutput2.txt");
        logic.clearFile(file);
        logic.writeResultInFile(file.getAbsolutePath(), "Test writing", "name");
        String check = utils.read(new File("src/test/resources/testOutput2.txt"));
        assertEquals(("Test writing").replaceAll("\r\n", "\n"), check);
    }
}