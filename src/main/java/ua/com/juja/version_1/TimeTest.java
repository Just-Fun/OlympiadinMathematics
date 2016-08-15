package ua.com.juja.version_1;

import ua.com.juja.solvers.Example;
import ua.com.juja.solvers.ExampleSolver;
import ua.com.juja.solvers.ExpressionParser;
import ua.com.juja.solvers.SimpleSolver;

public class TimeTest {
    public static void main(String[] args) {
        Example example = new ExampleSolver();
        System.out.println("ExampleSolver : " + getTime(example));
        example = new ExpressionParser();
        System.out.println("ExpressionParser : " + getTime(example));
        example = new SimpleSolver();
        System.out.println("SimpleSolver : " + getTime(example));

        example = new ExampleSolver();
        System.out.println("ExampleSolver : " + getTime(example));
        example = new ExpressionParser();
        System.out.println("ExpressionParser : " + getTime(example));
        example = new SimpleSolver();
        System.out.println("SimpleSolver : " + getTime(example));

        example = new ExampleSolver();
        System.out.println("ExampleSolver : " + getTime(example));
        example = new ExpressionParser();
        System.out.println("ExpressionParser : " + getTime(example));
        example = new SimpleSolver();
        System.out.println("SimpleSolver : " + getTime(example));
    }

    private static double getTime(Example example) {
        long t1 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            example.count("35+40*2/(10-5*7)");
            example.count("1+1");
            example.count("2*3+4/5");
            example.count("4/9");
            example.count("1*2*3*4*5*6");
            example.count("(7-9)/6");
            example.count("6-(2+(1*3)*2)");
        }
        return (System.nanoTime() - t1) / 1_000_000;
    }
}