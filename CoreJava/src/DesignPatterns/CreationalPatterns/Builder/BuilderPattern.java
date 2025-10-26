package DesignPatterns.CreationalPatterns.Builder;

public class BuilderPattern {
    public static void main(String[] args) {
        House house = new House.HouseBuilder("Concrete", "Wooden", "Title")
                .setGarden(true)
                .setSwimmingPool(true)
                .setGarage(false)
                .build();
        System.out.println(house);
    }
}
