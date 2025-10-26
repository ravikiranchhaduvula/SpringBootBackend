package SolidPrinciples.InterfaceSegregation.BadCode;

public interface Machine {
 void print(Document doc);
 void scan(Document doc);
 void copy(Document doc);
}
