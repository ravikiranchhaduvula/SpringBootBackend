package FunctionalInterface;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // First argument is input and second is output
        /**
         * Function<Integer, String> integerToString = (Integer number) -> {
         *             return number.toString();
         *         };
         */
        Function<Integer, String> integerToString = Object::toString;
        System.out.println(integerToString.apply(64));
    }
}
