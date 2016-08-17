package ua.com.juja;

import ua.com.juja.solvers.*;

import java.io.File;

/**
 * Created by Serzh on 8/12/16.
 */
/*
 1. Два и более ученика не могут решать одну и ту же задачу, то есть поток должен не просто прочитать задачу из файла,
 а прочитать и забрать себе первую задачу какую ещё никто не забрал.
 2. Результаты решения задач следует округлять до второго знака после запятой.
 3. Числа в задачах целые и положительные
 Дополнительные задания:
 Числа в задачах могут быть дробными и отрицательными.
 В конце выходного файла вывести тройку лидеров (кто больше задач сделал)
 с количеством решенных задача и временем выполнения задач. Формат вывода:
 Имя ученика;количество решенных задач;время выполнения задач
*/
public class Main {
    public static void main(String[] args) {
        File namesAndTasks = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");
        Solver solver = new SimpleSolver();
//        Solver solver = new ExampleSolver();
//        Solver solver = new ExpressionParser();

        Logic logic = new Logic(namesAndTasks, output, solver);
        logic.run();
    }
}
