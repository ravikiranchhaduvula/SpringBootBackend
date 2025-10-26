package DesignPatterns.StructuralPatterns.Adapter;

//Easily extensible
public class SendGridAdapter implements NotificationService {

    private SendGridService sendGridService;

    public SendGridAdapter(SendGridService sendGridService) {
        this.sendGridService = sendGridService;
    }

    @Override // Client will see this
    public void send(String to, String subject, String body) {
        // Adapter method (Convert params & calls to sendGrid method
        sendGridService.sendEmail(to, subject, body);
    }
}
