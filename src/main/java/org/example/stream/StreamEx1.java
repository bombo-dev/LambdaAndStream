package org.example.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamEx1 {
    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("김자반", 3, 300),
                new Student("이자바", 1, 200),
                new Student("김자바", 2, 150),
                new Student("박자바", 2, 180),
                new Student("안자바", 1, 210),
                new Student("나자바", 3, 100),
                new Student("감자바", 1, 100));

        studentStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}

class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    public Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore).toString();
    }

    public String getName() {
        return name;
    }

    public int getBan() {
        return ban;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}
