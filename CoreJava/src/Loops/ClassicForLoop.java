package Loops;

import java.util.Arrays;
import java.util.List;

//Best if you need the index or partial traversal.
//Still fine, but usually replaced by enhanced for when index isn’t needed.
public class ClassicForLoop {
    public static void main(String[] args) {
        List<String> arrList = Arrays.asList("Ravi", "Kiran");
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(i + ": " + arrList.get(i));
        }
    }
}
