package SolidPrinciples.LiscovSubstitution.GoodCode;

public class Client {
    public static void readAnyFile(ReadbleFile file) {
        file.read();
    }
    public static void main(String[] args) {
        ReadbleFile readbleFile = new ReadonlyFile();
        WritableFile writableFile = new WritableFile();

        readbleFile.read();
        writableFile.read();
        writableFile.write();

        //Both readble and writable files substituted her LSP is followed
        readAnyFile(readbleFile);
        readAnyFile(writableFile);
    }
}
