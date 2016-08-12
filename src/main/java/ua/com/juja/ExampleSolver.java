package ua.com.juja;

import java.util.Stack;

public class ExampleSolver {
    public static void main(String[] args) {
        String[] arr = {"3","4","+"};
        String[] arr1 = {"1","2","+","4","*","5","+","3","-"};
        String[] arr2 = {"5","1","2","+","4","*","+","3","-"};
        System.out.println(stackMachine(arr));
        System.out.println(stackMachine(arr1));
        System.out.println(stackMachine(arr2));
    }

    public static String count(String task) {
        String[] parsedTask = parseToRPN(task);
        double res = stackMachine(parsedTask);
        return String.valueOf(res);
    }

    private static String[] parseToRPN(String task) { //TODO implement me

        return null;
    }

    private static double stackMachine(String[] arr) {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            try {
                stack.push(Double.parseDouble(arr[i]));
            } catch (NumberFormatException e) {
                double b = stack.pop();
                double a = stack.pop();
                switch (arr[i]) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
