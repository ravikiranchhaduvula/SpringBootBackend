package SolidPrinciples.SingleResponsibility.GoodCode;

public class Invoice {
    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    //Additional Functionality
    public void generateInvoice() {
        System.out.println("Invoice Generated & Printed for amount:" + amount);
    }
}

