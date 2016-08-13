package ua.com.juja;

import java.util.Stack;

public class ExampleSolver {
    public static void main(String[] args) {
        String arr = "35+40*2/(10-5)";
        System.out.println(count(arr));
        arr = "35+40*2/(10-3)";
        System.out.println(count(arr));
        arr = "35+40*2/(10-5*7)";
        System.out.println(count(arr));
    }

    public static String count(String task) {
        String[] parsedTask = parseToRPN(task);
        String res = String.valueOf(stackMachine(parsedTask));
        return (res.substring(res.length()-2)).equals(".0") ? res.substring(0,res.length()-2) : res;
    }

    private static String[] parseToRPN(String task) {
        StringBuilder string = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < task.length(); i++) {
            char c = task.charAt(i);
            if (Character.isDigit(c)) { //TODO rewrite it
                while (Character.isDigit(c)) {
                    string.append(c);
                    c = task.charAt(++i);
                }
                i--;
                string.append(' ');
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                c = stack.pop();
                while (c != '(') {
                    string.append(c);
                    string.append(' ');
                    c = stack.pop();
                }
            } else {
                while (!stack.empty() && (getPriority(c) <= getPriority(stack.peek()))) {
                    string.append(stack.pop());
                    string.append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.empty()) {
            string.append(stack.pop());
            string.append(' ');
        }
        string.deleteCharAt(string.length() - 1);
        return string.toString().split(" ");
    }

    private static int getPriority(char c) {
        switch (c) {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 3;
        }
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
                    case "/":// TODO: if b are zero send message
                        stack.push(a / b);
                        break;
                }
            }
        }
        return Math.ceil(stack.pop()*100)/100;
    }
}
