package FunctionalInterface;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> isEvenNumber = () -> {
            System.out.println("Inside Method");
            return "This is data i am returning";
        };

        String value = isEvenNumber.get();
        System.out.println(value);
    }
}
