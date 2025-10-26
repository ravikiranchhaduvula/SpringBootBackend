package DesignPatterns.StructuralPatterns.Proxy;

public class Client {
    public static void main(String[] args) {
        //Image image = new RealImage("Dog.png");
        //Image image2 = new RealImage("Dog.png");
        Image image = new ProxyImage("Dog.png");
        Image image1 = new ProxyImage("Dog.png");

        // Loading image twice which is heavy
        // Waiting CPU time and memory
        image1.displayImage(); // Load image here Lazily / caching
        image1.displayImage();
    }
}
