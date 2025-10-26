package DesignPatterns.StructuralPatterns.Proxy;

//Should not get loaded when initialized
public class ProxyImage implements Image {

    private String fileName;

    private RealImage realImage; // Proxy reference to real image

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void displayImage() {
        if(realImage == null) {
          realImage = new RealImage(fileName); //Image is loaded + cached
        }
        realImage.displayImage();
    }
}
