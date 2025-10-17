package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FilterExample {
    public static void main(String[] args) {
        //From Collection
        List<Integer> salaryList = new ArrayList<>();
        salaryList.add(3000);
        salaryList.add(4100);
        salaryList.add(9000);
        salaryList.add(1000);
        salaryList.add(3500);

        Long count = salaryList.stream()
                .filter((Integer salary) -> salary > 3000)
                .peek(System.out::println)
                .count();
        System.out.println(count);

        //Arrays
        Integer[] salaryArray = {3000, 4000, 9000, 35000};
        Stream<Integer> arrStream = Arrays.stream(salaryArray);
        System.out.println(arrStream.toList());

        //From static method
        Stream<Integer> streamFromStatic = Stream.of(1000, 3500, 4000, 9000);
        System.out.println(streamFromStatic.toList());

        //From builder method
        Stream.Builder<Integer> streamBuilder = Stream.builder();
        streamBuilder.add(1000).add(3000).add(3500);
        Stream<Integer> streamFromBuilder = streamBuilder.build();
        System.out.println(streamFromBuilder.toList());

        //From stream iterate
        Stream<Integer> streamFromIterate = Stream.iterate(1000, (Integer n) -> n + 5000).limit(5);
        System.out.println(streamFromIterate.toList());

        //Map for transformation (Each word became a list of characters, but it’s not flattened.)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)   // transform each element
                .toList();         // collect into a new List
        System.out.println(squares);

        //Complex collection to a flattened list
        List<String> words = List.of("Java", "Stream");
        List<String> flattened = words.stream()
                .flatMap(word -> Arrays.stream(word.split(""))) // each → Stream<String>
                .toList();
        System.out.println(flattened);
        //Distinct, sorted(comparator)
        //Peek (Reading before the terminal operation)
        //skip n elements

        List<String> words1 = Arrays.asList("Java", "Stream", "API");
        int totalLength = words1.stream()
                .mapToInt(String::length) // Stream<String> → IntStream
                .sum();                   // specialized int ops
        System.out.println("Total length = " + totalLength);
        // Output: Total length = 13

        //AnyMatch
        List<Integer> numbersList = Arrays.asList(1, 3, 5, 7, 8);
        boolean hasEven = numbersList.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("Has even? " + hasEven); // true
    }
}
