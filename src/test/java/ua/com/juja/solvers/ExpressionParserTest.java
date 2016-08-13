package ua.com.juja.solvers;

import org.junit.Test;
import ua.com.juja.solvers.ExpressionParser;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 8/13/16.
 */
public class ExpressionParserTest {

    @Test
    public void test() {
        assertEquals(String.valueOf(2.0), ExpressionParser.run("1+1"));
        assertEquals(String.valueOf(6.8), ExpressionParser.run("2*3+4/5"));
        assertEquals(String.valueOf(0.45), ExpressionParser.run("4/9"));
        assertEquals(String.valueOf(720.0), ExpressionParser.run("1*2*3*4*5*6"));
        assertEquals(String.valueOf(-0.33), ExpressionParser.run("(7-9)/6"));
        assertEquals(String.valueOf(-2.0), ExpressionParser.run("6-(2+(1*3)*2)"));
    }


}