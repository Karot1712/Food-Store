package com.karot.food.backend.Utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailUtils {
    @Value("${secretEmail}")
    private String secretMail;

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(secretMail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        if(list != null && list.size() > 0){
            message.setCc(getCcArray(list));
        }
        emailSender.send(message);
    }

    private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];
        for(int i =0; i < ccList.size(); i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public void forgetMail(String to, String subject, String code) throws MessagingException{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(secretMail);
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<b>Your Login details for Food Management System</b><br><b>Email: </b> " + to + " <br><b>Verification code: </b> " + code;
        message.setContent(htmlMsg,"text/html");
        emailSender.send(message);
    }

    public void sendVerificationMail(String to, String subject, String verificationCode){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("<b>Click here to activate your account: </b><br><a href='http://localhost:5051/auth/approve?email='"+ to + "&code=" + verificationCode + "> Here </a>");
        emailSender.send(message);
    }

}
