package DesignPatterns.CreationalPatterns.Factory;

public class TransportFactory {
    // We want transport object not transport factory hence the static
    public static Transport createTransport(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bus" -> new Bus();
            case "bike" -> new Bike();
            default -> throw new IllegalArgumentException("Unsupported Transport type");
        };
    }
}
