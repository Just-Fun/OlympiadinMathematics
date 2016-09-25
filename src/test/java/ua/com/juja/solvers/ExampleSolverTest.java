package ua.com.juja.solvers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 8/13/16.
 */
public class ExampleSolverTest {
    @Test
    public void count() throws Exception {
        Solver solver = new ExampleSolver();
        assertEquals(String.valueOf(2), solver.count("1+1"));
        assertEquals(String.valueOf(6.8), solver.count("2*3+4/5"));
        assertEquals(String.valueOf(0.45), solver.count("4/9"));
        assertEquals(String.valueOf(720), solver.count("1*2*3*4*5*6"));
        assertEquals(String.valueOf(-0.33), solver.count("(7-9)/6"));
        assertEquals(String.valueOf(-2), solver.count("6-(2+(1*3)*2)"));
        assertEquals(String.valueOf(-2.5), solver.count("-5/2"));
    }
}