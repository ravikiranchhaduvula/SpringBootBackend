package Operators;

public class OperatorDemo {
    public static void main(String[] args) {
        int a = 10, b = 3;

        // Arithmetic Operators
        System.out.println("Arithmetic:");
        System.out.println("a + b = " + (a + b)); // addition
        System.out.println("a - b = " + (a - b)); // subtraction
        System.out.println("a * b = " + (a * b)); // multiplication
        System.out.println("a / b = " + (a / b)); // division
        System.out.println("a % b = " + (a % b)); // modulus

        // Relational Operators
        System.out.println("\nRelational:");
        System.out.println("a > b? " + (a > b));
        System.out.println("a == b? " + (a == b));

        // Logical Operators
        System.out.println("\nLogical:");
        boolean x = true, y = false;
        System.out.println("x && y = " + (x && y));
        System.out.println("x || y = " + (x || y));
        System.out.println("!x = " + (!x));

        // Unary Operators
        System.out.println("\nUnary:");
        int c = 5;
        System.out.println("c = " + c);
        System.out.println("++c = " + (++c)); // pre-increment
        System.out.println("c-- = " + (c--)); // post-decrement

        // Assignment Operators
        System.out.println("\nAssignment:");
        int d = 7;
        d += 3; // d = d + 3
        System.out.println("d after += 3 = " + d);

        // Bitwise Operators
        System.out.println("\nBitwise:");
        int p = 5, q = 3; // binary: 5=0101, 3=0011
        System.out.println("p & q = " + (p & q)); // AND
        System.out.println("p | q = " + (p | q)); // OR
        System.out.println("p ^ q = " + (p ^ q)); // XOR
        System.out.println("~p = " + (~p));       // NOT

        // Shift Operators
        System.out.println("\nShift:");
        System.out.println("p << 1 = " + (p << 1)); // left shift (multiply by 2)
        System.out.println("p >> 1 = " + (p >> 1)); // right shift (divide by 2)
        System.out.println("p >>> 1 = " + (p >>> 1)); // unsigned right shift

        // Ternary Operator
        System.out.println("\nTernary:");
        String result = (a > b) ? "a is bigger" : "b is bigger";
        System.out.println(result);

        // Type-check (instanceof with pattern matching - Java 21)
        System.out.println("\nType Check:");
        Object obj = "Hello Java 21";
        if (obj instanceof String s) { // pattern matching
            System.out.println("Length of string = " + s.length());
        }
    }
}
