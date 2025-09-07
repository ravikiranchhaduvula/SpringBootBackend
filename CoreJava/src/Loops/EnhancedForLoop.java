package Loops;

import java.util.Arrays;
import java.util.List;

//Readable and avoids off-by-one errors
//Use when: You just want to visit every element, no index needed.
public class EnhancedForLoop {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ravi", "Kiran");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
