package DesignPatterns.StructuralPatterns.Proxy;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk(); // Heavy Operation'
    }

    public void loadImageFromDisk() {
        System.out.println("Loading Image from disk: "+ fileName);
    }

    @Override
    public void displayImage() {
        System.out.println("Displaying: "+ fileName);
    }
}
