package DesignPatterns.StructuralPatterns.Decorator.Solution;

public class MushroomDecorator extends AbstractPizzaDecorator{
    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return decoratedPizza.getDescription() + ", Mushroom";
    }

    public double getCost() {
        return decoratedPizza.getCost() + 5.00;
    }
}
