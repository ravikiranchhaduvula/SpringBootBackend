package DesignPatterns.CreationalPatterns.Factory;

public class TransportService {
    public static void main(String[] args) {
        // Direct Create Objects (Client code Tightly coupled to concrete classes)
        // Adding new transport types requires modifying client code (Open/Close principle violation)
        // Transport car = new Car();
        // Transport bike = new Bike();
        Transport vehicle = TransportFactory.createTransport("bus"); // Runtime open-close
        vehicle.deliver();
    }
}
