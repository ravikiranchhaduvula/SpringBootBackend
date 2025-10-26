package DesignPatterns.StructuralPatterns.FlyWeight.solution;

public class Bullet {
    private int x,y; // Extrinsic properties unique to each bullet

    private int velocity;

    private BulletType type; // All Intrinsic wrapped inside this

    public Bullet(String color, int x, int y, int velocity) {
        this.type = BulletTypeFactory.getBulletType(color);
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        System.out.println("Creating Bullet At ("+ x + ","+ y);
    }

    public void display() {
        System.out.println("Bullet At ("+x+","+y+") Moving at +"+velocity);
    }
}
