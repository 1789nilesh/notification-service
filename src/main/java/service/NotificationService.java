package service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class NotificationService {

    private final SendGrid sendGrid;

    public NotificationService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendNotification(String message) {
        Email from = new Email("noreply@yourdomain.com");
        String subject = "New Notification";
        Email to = new Email("user@domain.com"); // Replace with dynamic recipient
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("your-sendgrid-api-key");
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}