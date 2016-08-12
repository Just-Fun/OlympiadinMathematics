package ua.com.juja;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Serzh on 8/13/16.
 */
public class TestSolver {

    @Test
    public void test() {

        assertEquals(String.valueOf(2), Solver.calculate("1+1"));
        assertEquals(String.valueOf(6.8), Solver.calculate("2*3+4/5"));
//        assertEquals(String.valueOf(0.45), Solver.calculate("4/9"));
        assertEquals(String.valueOf(720), Solver.calculate("1*2*3*4*5*6"));
//        assertEquals(String.valueOf(-0.34), Solver.calculate("(7-9)/6"));
        assertEquals(String.valueOf(-2), Solver.calculate("6-(2+(1*3)*2)"));
    }
}
