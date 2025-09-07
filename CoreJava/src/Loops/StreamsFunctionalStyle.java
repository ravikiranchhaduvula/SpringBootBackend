package Loops;

import java.util.Arrays;
import java.util.List;

//Best for declarative logic (filter, map, collect).
//Cleaner when transforming data, not ideal for side-effects-heavy loops.
public class StreamsFunctionalStyle {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("ARavi", "AKiran");
        names.stream()
                .filter(n -> n.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
