package DesignPatterns.BehaviouralPatterns.StrategyPattern;

interface PaymentStrategy {
    void processPayment();
}

//Concrete strategy
class DebitCardPayment implements PaymentStrategy {

    @Override
    public void processPayment() {
        System.out.println("Making payment with DebitCard");
    }
}

class CreditCardPayment implements PaymentStrategy {

    @Override
    public void processPayment() {
        System.out.println("Making payment with CreditCard");
    }
}

class PaymentServices {
    //Holds one of the strategy to make payments
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay() {
        strategy.processPayment();
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        PaymentServices paymentServices = new PaymentServices();
        // Runtime we decide which payment strategy
        paymentServices.setPaymentStrategy(new DebitCardPayment());
        paymentServices.pay();
    }

}
