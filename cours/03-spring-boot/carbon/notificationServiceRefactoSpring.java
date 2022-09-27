public interface NotificationService {
    void notify(Event event);
}
@Service
class NotificationServiceImpl implements NotificationService {
    private MailService mailService;
    @Autowired
    NotificationService(MailService mailService){
        this.mailService = mailService;
    }
    void notify(Event event){
        this.mailService.sendMail(event.passenger().mail(), event.body());
    }
}
public interface MailService {
    void sendMail(String to, String body);
}
@Service
class MailServiceImpl implements MailService {
    void sendMail(String to, String body){
        [...]
    }
}
