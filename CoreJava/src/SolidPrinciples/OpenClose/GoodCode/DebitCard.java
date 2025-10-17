package SolidPrinciples.OpenClose.GoodCode;

public class DebitCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Making payment with Debit Card: "+amount);
    }
}
