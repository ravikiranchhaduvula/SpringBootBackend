package DesignPatterns.StructuralPatterns.FlyWeight.solution;

// Flyweight class
public class BulletType {
    private String color; // Intrinsic property

    public BulletType(String color) {
        this.color = color;
        System.out.println("Creating bulletType with color "+color);
    }
}
