package DesignPatterns.StructuralPatterns.FlyWeight.problem;

public class Bullet {
    private String color; // Intrinsic properties shared by all bullets

    private int x,y; // Extrinsic properties unique to each bullet

    private int velocity;

    public Bullet(String color, int x, int y, int velocity) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        System.out.println("Creating Bullet At ("+ x + ","+ y);
    }

    public void display() {
        System.out.println("Bullet At ("+x+","+y+") Moving at +"+velocity);
    }
}
