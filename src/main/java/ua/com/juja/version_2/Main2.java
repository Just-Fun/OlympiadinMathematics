package ua.com.juja.version_2;

import ua.com.juja.version_1.Logic;

import java.io.File;

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
public class Main2 {
    public static void main(String[] args) {
        File input = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");
        long speed = 100;
        Logic2 logic = new Logic2();
        logic.run(input, output, speed);
    }
}
