package Loops;

import java.util.List;

//Records are data carriers with automatic toString(), equals(), and hashCode().
//Less boilerplate, direct field access with accessor methods (name(), id()).
//Records & sequenced collections → easier iteration with order guarantees.
public class RecordExample {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", 101),
                new Employee("Bob", 102),
                new Employee("Charlie", 103)
        );

        for (Employee e : employees) {
            System.out.println(e.name() + " -> " + e.id());
        }
    }
}
