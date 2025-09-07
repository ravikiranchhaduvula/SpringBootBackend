package FunctionalInterface;

public class ConcreteImpl implements BirdFunctionalInterface {

    private String breed;
    private boolean canSwim;

    @Override
    public void canFly(String str) {
        System.out.println("Eagle Bird Implementation");
    }
}
