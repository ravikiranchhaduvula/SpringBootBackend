package SolidPrinciples.DependencyInversion.GoodCode;

public class Client {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService(new EmailService());
        notificationService.notify("Jumbo");

        NotificationService notificationService1 = new NotificationService(new SmsService());
        notificationService1.notify("Mambo");
    }
}
