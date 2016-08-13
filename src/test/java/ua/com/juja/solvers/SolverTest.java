package ua.com.juja.solvers;

import org.junit.Test;
import ua.com.juja.solvers.Solver;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 8/13/16.
 */
public class SolverTest {
    @Test
    public void test() {
        assertEquals(String.valueOf(2.0), Solver.calculate("1+1"));
        assertEquals(String.valueOf(6.8), Solver.calculate("2*3+4/5"));
        assertEquals(String.valueOf(0.44), Solver.calculate("4/9"));
        assertEquals(String.valueOf(720.0), Solver.calculate("1*2*3*4*5*6"));
        assertEquals(String.valueOf(-0.33), Solver.calculate("(7-9)/6"));
        assertEquals(String.valueOf(-2.0), Solver.calculate("6-(2+(1*3)*2)"));
    }

}