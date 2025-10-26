package DesignPatterns.StructuralPatterns.Adapter;

public class EmailNotificationService implements NotificationService {
    public void send(String to, String subject, String body) {
        System.out.println("Sending Email To: "+ to);
        System.out.println("Subject: "+subject);
        System.out.println("Body: "+body);
    }
}
