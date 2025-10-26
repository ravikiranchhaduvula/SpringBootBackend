package DesignPatterns.StructuralPatterns.Decorator.Solution;

public abstract class AbstractPizzaDecorator implements Pizza{
    protected Pizza decoratedPizza;

    public AbstractPizzaDecorator(Pizza pizza) {
        this.decoratedPizza = pizza;
    }
    @Override
    public String getDescription() {
        return decoratedPizza.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost();
    }
}
