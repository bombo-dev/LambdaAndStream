package org.example.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx3 {
    public static void main(String[] args) {

        Student[] stuArr = {
                new Student("김자반", 3, 300),
                new Student("이자바", 1, 200),
                new Student("김자바", 2, 150),
                new Student("박자바", 2, 180),
                new Student("안자바", 1, 210),
                new Student("나자바", 3, 100),
                new Student("감자바", 1, 100)
        };

        Stream<Student> stuStream = Arrays.stream(stuArr);
        stuStream.sorted(Comparator.comparing(Student::getBan)
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        stuStream = Stream.of(stuArr);
        IntStream stuScoreStream = stuStream.mapToInt(Student::getTotalScore);

        IntSummaryStatistics stat = stuScoreStream.summaryStatistics();
        System.out.println("count = " + stat.getCount());
        System.out.println("sum = " + stat.getSum());
        System.out.println("average = " + ((int) (stat.getAverage() * 100)) / 100.0);
        System.out.println("min = " + stat.getMin());
        System.out.println("max = " + stat.getMax());
    }
}
