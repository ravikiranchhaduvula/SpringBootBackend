package SolidPrinciples.OpenClose.BadCode;

public class PaymentProcessor {
    public void processPayment(String paymentMethod, double amount) {
        if(paymentMethod.equals("CreditCard")) {
            System.out.println("Making Payment via credit card: "+amount);
        } else if(paymentMethod.equals("Debit Card")) {
            System.out.println("Making payment via Debit Card: "+amount);
        } else if(paymentMethod.equals("Paypal")) {
            System.out.println("Making payment via Debit Card: "+amount);
        } else {
            throw new IllegalArgumentException("Unsupported payment method "+paymentMethod);
        }
    }
}
