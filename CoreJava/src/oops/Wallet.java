package oops;

public class Wallet implements PaymenteMethod{
    @Override
    public void pay() {
        System.out.println("Wallet Payment");
    }
}
