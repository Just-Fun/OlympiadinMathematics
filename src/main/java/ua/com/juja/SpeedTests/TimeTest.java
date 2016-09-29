package ua.com.juja.SpeedTests;

import ua.com.juja.solvers.Solver;
import ua.com.juja.solvers.ExampleSolver;
import ua.com.juja.solvers.SimpleSolver;

public class TimeTest {
    public static void main(String[] args) {
        Solver example = new ExampleSolver();
        System.out.println("ExampleSolver : " + getTime(example));
        example = new SimpleSolver();
        System.out.println("SimpleSolver : " + getTime(example));

        example = new ExampleSolver();
        System.out.println("ExampleSolver : " + getTime(example));
        example = new SimpleSolver();
        System.out.println("SimpleSolver : " + getTime(example));

        example = new ExampleSolver();
        System.out.println("ExampleSolver : " + getTime(example));
        example = new SimpleSolver();
        System.out.println("SimpleSolver : " + getTime(example));
    }

    private static double getTime(Solver example) {
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
