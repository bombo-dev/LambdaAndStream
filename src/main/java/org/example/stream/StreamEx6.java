package org.example.stream;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("김자반", 3, 300),
                new Student("이자바", 1, 200),
                new Student("김자바", 2, 150),
                new Student("박자바", 2, 180),
                new Student("안자바", 1, 210),
                new Student("나자바", 3, 100),
                new Student("감자바", 1, 100),
                new Student("박팔자", 2, Integer.MAX_VALUE)
        };

        // 학생 이름만 뽑아서 List<String>에 저장
        List<String> names = Stream.of(stuArr).map(Student::getName).collect(toList());
        System.out.println(names);

        // 스트림을 배열로 변환
        Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

        for(Student s : stuArr2) {
            System.out.println(s);
        }

        // 스트림을 Map<String, Student>로 변환, 이때, 학생이름이 Key
        Map<String, Student> stuMap = Stream.of(stuArr).collect(toMap(Student::getName, s -> s));

        for(Map.Entry<String, Student> entry : stuMap.entrySet()){
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        // 스트림 count
        long count = Stream.of(stuArr).collect(counting());
        // 스트림 sum
        long totalScore = Stream.of(stuArr).collect(summingLong(Student::getTotalScore));

        System.out.println("count = " + count);
        System.out.println("totalScore = " + totalScore);
        // 스트림 reducing sum

        // long 타입으로 반환하려면 map 을 거쳐야함
        // 기본 reducing 의 반환은 int
//        totalScore = Stream.of(stuArr).collect(reducing(0, Student::getTotalScore, Integer::sum));
        // 콜렉트 내부에서는 불가능
//        totalScore = Stream.of(stuArr).mapToLong(Student::getTotalScore).collect(reducing(0L, Long::sum));
        totalScore = Stream.of(stuArr).mapToLong(Student::getTotalScore).reduce(0L, Long::sum);
        System.out.println("totalScore2 = " + totalScore);
        // 스트림 max 총점
        Optional<Student> max = Stream.of(stuArr).collect(maxBy((a, b) -> a.totalScore - b.totalScore));
        System.out.println(max.get());
        // 바로 OptionalInt 로 반환하는 것은 불가능
        Optional<Integer> max2 = Stream.of(stuArr).map(Student::getTotalScore).collect(maxBy((a, b) -> a - b));
        // 따로 변환하는 과정을 거쳐야함.
        System.out.println(max2.get());
        OptionalInt max3 = max2.isPresent() ? OptionalInt.of(max2.get()) : OptionalInt.empty();
        System.out.println(max3.getAsInt());
        // 스트림 Comparator max
        Optional<Student> max4 = Stream.of(stuArr).collect(maxBy(Comparator.comparing(Student::getTotalScore)));
        System.out.println(max4.get());
        // 스트림 Summary count, sum, min, average, max 연산모음, sum -> long 연산
        IntSummaryStatistics stat = Stream.of(stuArr)
                .collect(summarizingInt(Student::getTotalScore));
        System.out.println(stat);
        // 스트림 joining
        String stuNames = Stream.of(stuArr)
                .map(Student::getName)
                .collect(joining(",", "{", "}"));

        System.out.println(stuNames);

    }
}
