package DesignPatterns.StructuralPatterns.Adapter;

public class Client {
    public static void main(String[] args) {
        NotificationService emailService = new EmailNotificationService();
        emailService.send("customer@codingminutes.com", "Order Confirmation", "Your Order Has Been Received");
        // NotificationService sendGridService = new SendGridService();
        NotificationService sendGridService = new SendGridAdapter(new SendGridService());
        sendGridService.send("customer@codingminutes.com", "Order Confirmation", "Your Order Has Been Received");
    }
}
