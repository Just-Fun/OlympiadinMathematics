package ua.com.juja;

import java.util.Stack;

public class ExampleSolver {
    public static void main(String[] args) {
        String[] arr = {"3","3","+"};
        System.out.println(stackMachine(arr));
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
                switch (arr[i]) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        stack.push(stack.pop() - stack.pop());
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        stack.push(stack.pop() / stack.pop());
                        break;
                }
            }
        }
        return stack.pop();
    }
}
