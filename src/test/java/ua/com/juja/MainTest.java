package ua.com.juja;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Serzh on 8/12/16.
 */
public class MainTest {

    @Test
    public void testDoWork() throws Exception {
        File input = new File("src/test/resources/testInput.txt");
        File output = new File("src/test/resources/testOutput.txt");
        Main main = new Main();
//        main.doWork(input, output);

        Utils utils = new Utils();
        String check = utils.read(output);
        assertEquals(("Вася;1+1;2\n" +
                "Коля;2*3+4/5;6,8\n" +
                "Юля;4/9;0,45\n" +
                "Юля;1*2*3*4*5*6;720\n" +
                "Оля;(7-9)/6;-0,34\n" +
                "Вася;(6-(2+(1*3)*2));-2").replaceAll("\r\n", "\n"), check);


    }

}