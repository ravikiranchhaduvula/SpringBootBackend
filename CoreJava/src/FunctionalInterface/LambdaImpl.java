package FunctionalInterface;

public class LambdaImpl {
    public static void main(String[] args) {
        //As we have only one abstract method no need to mention method name
        BirdFunctionalInterface eagleObj = (String val) -> {
            System.out.println("Eagle dies "+val);
        };
        eagleObj.canFly("Bad");
    }
}
