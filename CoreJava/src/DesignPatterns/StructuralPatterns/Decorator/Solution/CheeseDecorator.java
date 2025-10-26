package DesignPatterns.StructuralPatterns.Decorator.Solution;

public class CheeseDecorator extends AbstractPizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return decoratedPizza.getDescription() + ", Cheese";
    }

    public double getCost() {
        return decoratedPizza.getCost() + 1.00;
    }
}
