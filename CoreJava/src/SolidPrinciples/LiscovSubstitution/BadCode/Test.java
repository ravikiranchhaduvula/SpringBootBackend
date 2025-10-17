package SolidPrinciples.LiscovSubstitution.BadCode;

public class Test {
    public static void main(String[] args) {
        File file = new ReadonlyFile();
        file.read();
        file.write();//Run time error violation of LSP
    }
}