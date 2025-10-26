package DesignPatterns.StructuralPatterns.Decorator.Solution;

public class OliveDecorator extends AbstractPizzaDecorator {
    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return decoratedPizza.getDescription() + ", Olives";
    }

    public double getCost() {
        return decoratedPizza.getCost() + 2.00;
    }
}
