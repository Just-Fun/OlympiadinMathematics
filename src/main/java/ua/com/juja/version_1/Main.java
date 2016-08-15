package ua.com.juja.version_1;

import java.io.*;

/**
 * Created by Serzh on 8/12/16.
 */
/*
 1. Два и более ученика не могут решать одну и ту же задачу, то есть поток должен не просто прочитать задачу из файла,
 а прочитать и забрать себе первую задачу какую ещё никто не забрал.
 2. Результаты решения задач следует округлять до второго знака после запятой в большую сторону.
 3. Числа в задачах целые и положительные
 Дополнительные задания:
 Числа в задачах могут быть дробными и отрицательными.
 В конце выходного файла вывести тройку лидеров (кто больше задач сделал)
 с количеством решенных задача и временем выполнения задач. Формат вывода:
 Имя ученика;количество решенных задач;время выполнения задач
*/
public class Main {
    public static void main(String[] args) {
        File input = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");

        Logic logic = new Logic();
        logic.run(input, output);
    }
}