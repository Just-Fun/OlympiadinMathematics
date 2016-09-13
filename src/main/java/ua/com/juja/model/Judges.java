package ua.com.juja.model;

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
//        students.sort(Comparator.comparing(a -> a.getNumberResolvedTasks()));
//        students.sort(Comparator.comparing(Student::getNumberResolvedTasks).reversed());
//        Collections.sort(students, (b1, b2) -> (b2.getNumberResolvedTasks()));

        Comparator<Student> comparator = Comparator
                .comparing(Student::getNumberResolvedTasks).reversed()
                .thenComparing(Student::getSpentTimeOnTasks);

        List<Student> winners = students.parallelStream()
                .sorted(comparator)
                .limit(3)
                .collect(Collectors.toList());

        String result = "";
        System.out.println();
        for (int i = 0; i < winners.size(); i++) {
            String name = winners.get(i).getName();
            int numberOfTasks = winners.get(i).getNumberResolvedTasks();
            Long spentTime = winners.get(i).getSpentTimeOnTasks();
            System.out.printf("%dst place: %s, %d tasks are solved in %d nanoseconds%n", i + 1, name, numberOfTasks, spentTime);
            result += String.format("%s;%s;%s\n", name, String.valueOf(numberOfTasks), String.valueOf(spentTime));
        }
        return result;
    }
}
