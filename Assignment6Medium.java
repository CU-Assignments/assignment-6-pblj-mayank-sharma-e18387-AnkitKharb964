import java.util.*;
import java.util.stream.*;

class Student {
    private String name;
    private double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public double getMarks() { return marks; }
}

public class Assignment6Medium {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ravi", 85.0),
                new Student("Priya", 72.5),
                new Student("Amit", 90.3),
                new Student("Neha", 76.8)
        );

        List<String> topStudents = students.stream()
                .filter(s -> s.getMarks() > 75)
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("Students scoring above 75%:");
        topStudents.forEach(System.out::println);
    }
}
