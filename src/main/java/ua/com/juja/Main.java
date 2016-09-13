package ua.com.juja;

import ua.com.juja.model.Logic;
import ua.com.juja.solvers.*;

import java.io.File;

/**
 * Created by Serzh on 8/12/16.
 */
/*
 1. Two or more students can not solve the same problem, that is, the flow should not just read the task from a file,
 and read and pick up a first task which still has not been taken away.
 2. The results of solving problems should be rounded to the second decimal place.
 3. The numbers in the problems of the whole and positive
 Additional tasks:
 The numbers in the problems can be fractional or negative.
 At the end of the output file to bring the three leaders (who did more tasks)
 with the amount of solution to the problem of time and tasks. Output Format:
 Name student; the number of solved problems; task time
*/
public class Main {
    public static void main(String[] args) {
        File namesAndTasks = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");
        Solver solver = new SimpleSolver();
//        Solver solver = new ExampleSolver();
//        Solver solver = new ExpressionParser();

        Logic logic = new Logic(namesAndTasks, output, solver);
        logic.run();
    }
}
