package SolidPrinciples.InterfaceSegregation.BadCode;

public class MultiPurposePrinter implements Machine {
    @Override
    public void print(Document doc) {
        System.out.println("Printing Document");
    }

    @Override
    public void scan(Document doc) {
        System.out.println("Scanning Document");
    }

    @Override
    public void copy(Document doc) {
        System.out.println("Copying Document");
    }
}
