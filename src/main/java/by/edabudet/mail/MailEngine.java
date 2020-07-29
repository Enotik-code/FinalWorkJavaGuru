package by.edabudet.mail;
import by.edabudet.authentication.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailEngine {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    SpringTemplateEngine templateEngine;
    @Value("classpath:/eb.jpg")
    Resource resourceFile;

    public void sendHTMLTestEmailWithLogo(User user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(user.getEmail());
        helper.setSubject("edabudet.by");
        helper.setText("<img src=\"cid:attachment.png\" /><H1>Hello," + user.getUserName() + "</h1><h3>    My congratulations." +
                " You could easily register and hope just as easily you will be able to purchase products and more using our website." +
                "<br><br> Good luck and pleasant shopping <br> Kupreichik A.<br>edabudet.by</h3>", true);
        helper.addInline("attachment.png", resourceFile);
        mailSender.send(message);
    }

    public void sendHTMLTestEmailWithLogoAndActivateCode(User user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(user.getEmail());
        helper.setSubject("edabudet.by");
        helper.setText("<img src=\"cid:attachment.png\" /><H1>Hello," + user.getUserName() + "</h1><h3>here is your activation code for edabudet.by - " + user.getActivationCode() +"</h3>", true);
        helper.addInline("attachment.png", resourceFile);
        mailSender.send(message);
    }
}