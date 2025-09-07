package FunctionalInterface;

@FunctionalInterface
public interface BirdFunctionalInterface {
    // Functional Interface abstract
    void canFly(String val);

    // Interface default
    default void canSit() {
        System.out.println("Inside interface default");
    }

    // Object class method override
    String toString();

    // Static method
    static void getVal() {
        System.out.println("Inside interface static");
    }
}
