package SolidPrinciples.InterfaceSegregation.BadCode;

public class SimplePrinter implements Machine{
    @Override
    public void print(Document doc) {
        System.out.println("Printing");
    }

    @Override
    public void scan(Document doc) throws UnsupportedOperationException {
       throw new UnsupportedOperationException();
    }

    @Override
    public void copy(Document doc) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
