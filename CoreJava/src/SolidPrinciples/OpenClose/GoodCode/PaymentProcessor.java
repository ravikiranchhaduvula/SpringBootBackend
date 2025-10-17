package SolidPrinciples.OpenClose.GoodCode;

public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.pay(amount); // Runtime poly
    }
}
