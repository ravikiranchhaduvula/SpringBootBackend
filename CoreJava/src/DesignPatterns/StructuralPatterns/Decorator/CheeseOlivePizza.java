package DesignPatterns.StructuralPatterns.Decorator;

public class CheeseOlivePizza extends CheesePizza {
    @Override
    public String getDescription() {
        return super.getDescription()+", Olive";
    }

    @Override
    public double getCost() {
        return super.getCost() + 3.00; // Base Cost
    }
}
