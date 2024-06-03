package th.project.enterprise.Service;

import th.project.enterprise.Entity.Customer;
import th.project.enterprise.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void registrationConfirmationEmail(Customer user) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("zaher.abb12@gmail.com");
        String text = String.format("Welcome %s to our online Shop " +
                "  you have successfully registered", user.getFirstName());
        mail.setText(text);
        mail.setSubject("successfully registered");
        javaMailSender.send(mail);
    }



    public void userEmailToAdmin(Customer user, String text, String s) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("zaher.abb12@gmail.com");
        mail.setFrom("zaher.abb12@gmail.com");
        String TextMessage = String.format("message Sent From User with this Email :%S ", user.getEmail());
        mail.setText(TextMessage + "\n" + text);
        mail.setSubject(s);
        javaMailSender.send(mail);

    }


    /*public void orderConfirmationEmail(User user, LocalDateTime DelevieryDate) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("zaher.abb12@gmail.com");
        String text = String.format("Thank you  %s for you Order " +
                "  your order will be in your Adress at %s ", user.getFirstName(), DelevieryDate);
        mail.setText(text);
        mail.setSubject("Order Confirmation ");
        javaMailSender.send(mail);
    }*/


}
