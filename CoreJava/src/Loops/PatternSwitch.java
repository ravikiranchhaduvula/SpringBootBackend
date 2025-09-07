package Loops;

//Instead of verbose if-else or casting, you can match patterns directly in a switch.
//Cleaner than nested instanceof checks, especially inside loops.
public class PatternSwitch {
    public static void main(String[] args) {
        Object[] values = { "Hello", 123, 45.6, true };

        for (Object value : values) {
            switch (value) {
                case String s -> System.out.println("String of length " + s.length());
                case Integer i -> System.out.println("Integer doubled = " + (i * 2));
                case Double d -> System.out.println("Double squared = " + (d * d));
                case Boolean b -> System.out.println("Boolean value = " + b);
                default -> System.out.println("Unknown type");
            }
        }
    }
}

