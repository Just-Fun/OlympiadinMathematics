package ua.com.juja;

import ua.com.juja.version_2.Student2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Serzh on 8/14/16.
 */
public class ForTests {
    public static void main(String[] args) {

        List<Student2> students = new ArrayList<>();
        students.add(new Student2("John", 5, 2));
        students.add(new Student2("Vlad", 5, 3));
        students.add(new Student2("Violetta", 3, 1));
        students.add(new Student2("Dig", 2, 8));
        students.add(new Student2("Volt", 111, 22));

        Comparator<Student2> c = Comparator
                .comparing(Student2::getTasks).reversed()
                .thenComparing(Student2::getTime);

                /*.comparing(Student2::getTasks).reversed()
                .thenComparing(Student2::getTime).reversed();*/

        List<Student2> winners = students.stream()
                .sorted(c)
//                .sorted((s1, s2) -> (s2.getNumberResolvedTasks() - s1.getNumberResolvedTasks()))
                .limit(3)
                .collect(Collectors.toList());
        ;

        winners.stream()
                .map(student -> student.getName())
                .forEach(System.out::println);

    }
}
