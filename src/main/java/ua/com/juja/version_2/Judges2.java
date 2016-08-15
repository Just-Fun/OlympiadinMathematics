package ua.com.juja.version_2;

import java.util.stream.Collectors;
import java.util.*;

/**
 * Created by Serzh on 8/14/16.
 */
public class Judges2 {
    private List<Student> students;

    public Judges2(List<Student> students) {
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
            result += name + ";" + String.valueOf(numberOfTasks) + ";" + String.valueOf(spentTime) + "\n";
        }
        return result;
    }
}
