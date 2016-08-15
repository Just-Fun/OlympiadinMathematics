package ua.com.juja.version_2;

import ua.com.juja.version_1.Logic;
import ua.com.juja.version_1.MapUtil;

import java.util.*;

/**
 * Created by Serzh on 8/14/16.
 */
public class Judges2 {
    List<Student> students;

    public Judges2(List<Student> students) {
        this.students = students;
    }

    public String getWinners() {
//        students.sort(Comparator.comparing(a -> a.getNumberResolvedTasks()));
//        students.sort(Comparator.comparing(Student::getNumberResolvedTasks));
//        Collections.sort(students, (b1, b2) -> (b2.getNumberResolvedTasks() - b1.getNumberResolvedTasks()));

        System.out.println("\nТройка победителей:");
        students.stream()
                .sorted((s1, s2) -> (s2.getNumberResolvedTasks() - s1.getNumberResolvedTasks()))
                .map(Student::getName)
                .limit(3)
                .forEach(System.out::println);
        ;

        String result = "";
        for (int i = 0; i < 3; i++) {
            Student student = students.get(i);
            String name = student.getName();
            int numberOfTasks = student.getNumberResolvedTasks();
            Long spentTime = student.getSpentTimeOnTasks();
            result += name + ";" + String.valueOf(numberOfTasks) + ";" + String.valueOf(spentTime) + "\n";
        }
//        Utils.writeResultInFile(output.getAbsolutePath(), result);
        System.out.println("\nТройка победителей: \n" + result);
//        students.forEach(System.out::println);
        return result;
}
}
