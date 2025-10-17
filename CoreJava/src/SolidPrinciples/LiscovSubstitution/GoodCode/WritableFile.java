package SolidPrinciples.LiscovSubstitution.GoodCode;

public class WritableFile extends ReadbleFile implements Writable{
    @Override
    public void write() {
        System.out.println("Writing to a file");
    }
}
