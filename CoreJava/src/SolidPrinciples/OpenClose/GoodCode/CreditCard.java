package SolidPrinciples.OpenClose.GoodCode;

public class CreditCard implements PaymentMethod{
    @Override
    public void pay(double amount) {
        System.out.println("Making Payment inside CreditCard: "+amount);
    }
}
