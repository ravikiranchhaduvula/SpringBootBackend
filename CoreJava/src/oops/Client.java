package oops;

public class Client {
    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.addPaymentMethod("RaviDebitCard", new DebitCard("1234", "Ravi Ch"));
        ps.addPaymentMethod("RaviCreditCard", new CreditCard("5678", "Ravi Ch"));
        ps.addPaymentMethod("RaviUPI", new UPI("RaviUPI"));
        ps.addPaymentMethod("RaviWallet", new UPI("RaviWallet"));

        ps.makePayment("RaviUPI");
        ps.makePayment("RaviCreditCard");
        ps.makePayment("RaviWallet");
    }
}
