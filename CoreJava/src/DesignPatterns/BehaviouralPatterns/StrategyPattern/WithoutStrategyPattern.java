package DesignPatterns.BehaviouralPatterns.StrategyPattern;

class PaymentService {
    public void processPayment(String paymentMethod) {
        if(paymentMethod.equals("Credit Card")) {
            System.out.println("Making Payment With Credit Card");
        } else if(paymentMethod.equals("Debit Card")) {
            System.out.println("Making Payment with Debit Card");
        } else {
            System.out.println("Unsupported Payment Method");
        }
    }
}

public class WithoutStrategyPattern {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.processPayment("Debit Card");
    }
}
