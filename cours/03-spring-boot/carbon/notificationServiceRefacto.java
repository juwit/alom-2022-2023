public interface NotificationService {
    void notify(Event event);
}
class NotificationServiceImpl implements NotificationService {
    private MailService mailService;
    void setMailService(MailService mailService){
        this.mailService = mailService;
    }
    void notify(Event event){
        this.mailService.sendMail(event.passenger().mail(), event.body());
    }
}
public interface MailService {
    void sendMail(String to, String body);
}
class MailServiceImpl implements MailService {
    void sendMail(String to, String body){
        [...]
    }
}
