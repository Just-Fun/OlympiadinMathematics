package ua.com.juja;

import org.junit.Before;
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
    List<Thread> threads = new ArrayList<>();
    File input = new File("src/test/resources/testInput.txt");
    File output = new File("src/test/resources/testOutput.txt");

    Logic logic;
    Utils utils;

    @Before
    public void setup() {
        logic = new Logic();
        utils = new Utils();
    }

    @Test
    public void run() throws Exception {

//        logic.run(input, output);
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
        logic.createNamesAndTasksFromFile(input, names, tasks);
        assertEquals("[[Вася, Оля, Коля, Юля]]", Arrays.asList(names).toString());
        assertEquals("[[1+1, 2*3+4/5, 4/9, 1*2*3*4*5*6, (7-9)/6, (6-(2+(1*3)*2))]]", Arrays.asList(tasks).toString());
    }

    @Test
    public void makeThread() throws Exception {
        logic.createNamesAndTasksFromFile(input, names, tasks);
        logic.makeThread(names, threads);
        assertEquals("[[Thread[Thread-0,5,main], Thread[Thread-1,5,main], Thread[Thread-2,5,main], Thread[Thread-3,5,main]]]", Arrays.asList(threads).toString());
    }

    @Test
    public void writeResultInFile() throws Exception {
       logic.writeResultInFile("src/test/resources/testOutput2.txt", "Test writing");
        String check = utils.read(new File("src/test/resources/testOutput2.txt"));
        assertEquals(("Test writing").replaceAll("\r\n", "\n"), check);
    }

}