package SolidPrinciples.LiscovSubstitution.BadCode;

public class ReadonlyFile extends File {
    public void write() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot write to a read only file");
    }
}
