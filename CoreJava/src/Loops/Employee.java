package Loops;

//Records are data carriers with automatic toString(), equals(), and hashCode().
//Less boilerplate, direct field access with accessor methods (name(), id()).
public record Employee(String name, int id) {
}
