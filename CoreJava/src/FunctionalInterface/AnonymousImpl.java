package FunctionalInterface;

public class AnonymousImpl {
    public static void main(String[] args) {
        BirdFunctionalInterface birdFunctionalInterface = new BirdFunctionalInterface() {
            @Override
            public void canFly(String val) {
                System.out.println("Eagle Flies "+ val);
            }
        };
        birdFunctionalInterface.canFly("Faster");
    }
}
