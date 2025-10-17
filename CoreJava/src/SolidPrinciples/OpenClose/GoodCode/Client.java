package SolidPrinciples.OpenClose.GoodCode;

public class Client {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        PaymentMethod creditCard = new CreditCard();
        paymentProcessor.processPayment(creditCard, 2000);
    }
}
