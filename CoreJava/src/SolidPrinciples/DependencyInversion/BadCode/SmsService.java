package SolidPrinciples.DependencyInversion.BadCode;

public class SmsService {
    public void sendSMS(String message) {
        System.out.println("Sending SMS: "+message);
    }
}
