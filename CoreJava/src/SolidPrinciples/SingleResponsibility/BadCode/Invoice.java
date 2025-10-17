package SolidPrinciples.SingleResponsibility.BadCode;

public class Invoice {
    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    //Additional Functionality
    public void generateInvoice() {
        System.out.println("Invoice Generated & Printed for amount:" + amount);
    }

    public void saveToDatabase() {
        System.out.println("Saving Invoice to Database");
    }

    public void sendEmailNotification() {
        System.out.println("Sending Email Notification");
    }
}
