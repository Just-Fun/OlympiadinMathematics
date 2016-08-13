package ua.com.juja.solvers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 8/13/16.
 */
public class ExampleSolverTest {
    @Test
    public void count() throws Exception {
        assertEquals(String.valueOf(2), ExampleSolver.count("1+1"));
        assertEquals(String.valueOf(6.8), ExampleSolver.count("2*3+4/5"));
        assertEquals(String.valueOf(0.45), ExampleSolver.count("4/9"));
        assertEquals(String.valueOf(720), ExampleSolver.count("1*2*3*4*5*6"));
        assertEquals(String.valueOf(-0.33), ExampleSolver.count("(7-9)/6"));
        assertEquals(String.valueOf(-2), ExampleSolver.count("6-(2+(1*3)*2)"));
    }

}