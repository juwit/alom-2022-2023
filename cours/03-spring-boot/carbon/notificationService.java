class NotificationService {
    private MailService mailService = new MailService();
    private Paypal paypal = new Paypal();
    void notify(Event event){
        this.mailService.sendMail(event.passenger(), event.body());
    }
    void payForTrip(Trip t){
        this.paypal.requiredPayment(t.passenger().email(), t.cost());
    }
}
class MailService {
    void sendMail(String to, String content){
        [...]
    }
}
