package SolidPrinciples.DependencyInversion.GoodCode;

public class SmsService implements NotificationChannel {

    @Override
    public void send(String msg) {
        System.out.println("Sending SMS: "+msg);
    }
}
