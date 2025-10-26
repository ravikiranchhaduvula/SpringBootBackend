package SolidPrinciples.DependencyInversion.BadCode;

public class NotificationService {
    private EmailService emailService;
    private SmsService smsService;
    //Tightly coupled with Low level systems and not flexible
    //If we have wattsapp in future then we need to modify this service

    public NotificationService() {
        emailService = new EmailService();
        smsService = new SmsService();
    }

    public void notifyByEmail(String msg) {
        emailService.sendEmail(msg);
    }

    public void notifyBySms(String msg) {
        smsService.sendSMS(msg);
    }
}
