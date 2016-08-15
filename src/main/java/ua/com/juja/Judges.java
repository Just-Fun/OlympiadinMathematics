package ua.com.juja;

import java.util.stream.Collectors;
import java.util.*;

/**
 * Created by Serzh on 8/14/16.
 */
public class Judges {
    private List<Student> students;

    public Judges(List<Student> students) {
        this.students = students;
    }

    public String getWinners() {
        Comparator<Student> comparator = Comparator
                .comparing(Student::getNumberResolvedTasks).reversed()
                .thenComparing(Student::getSpentTimeOnTasks);

        List<Student> winners = students.stream()
                .sorted(comparator)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nТройка победителей:");
        winners.stream()
                .map(Student::getName)
                .forEach(System.out::println);

        String result = "";
        for (Student winner : winners) {
            String name = winner.getName();
            int numberOfTasks = winner.getNumberResolvedTasks();
            Long spentTime = winner.getSpentTimeOnTasks();
            result += String.format("%s;%s;%s\n", name, String.valueOf(numberOfTasks), String.valueOf(spentTime));
        }
        return result;
    }
}
