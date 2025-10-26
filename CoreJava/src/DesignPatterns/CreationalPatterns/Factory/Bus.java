package DesignPatterns.CreationalPatterns.Factory;

public class Bus implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver By Bus");
    }
}
