package Loops;

import java.util.Arrays;
import java.util.List;

//Good for looping until a condition changes dynamically.
public class DoWhileLoop {
    public static void main(String[] args) {
        List<String> queue = Arrays.asList("Hello");

        // Infinite loop
        while (true) {
            process();
            break; // <-- Remove this if you want it truly infinite
        }

        int i = 0;
        do {
            System.out.println(i++);
        } while (i < 5);
    }

    static void process() {
        System.out.println("proceed");
    }
}

