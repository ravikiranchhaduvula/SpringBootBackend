package DesignPatterns.StructuralPatterns.Composite;

public class FileSystemApp {
    public static void main(String[] args) {
        //File file1 = new File("File1.txt");
        //File file2 = new File("File2.txt");
        FileSystemComponent file1 = new File("File1.txt");
        FileSystemComponent file2 = new File("File2.txt");
        Folder folder = new Folder("Documents");// List of files or folders how it will be uniform
        folder.addComponent(file1);
        folder.addComponent(file2);

        //Sub Folder
        Folder subFolder = new Folder("subfolder");
        FileSystemComponent file3 = new File("File3.txt");
        subFolder.addComponent(file3);
        folder.addComponent(subFolder);

        folder.showDetails();
    }
}
