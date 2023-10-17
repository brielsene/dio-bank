package br.com.diobank.service;

import br.com.diobank.model.EmailDetails;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(EmailDetails details){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(details.getSubject());
        simpleMailMessage.setText(details.getMessageBody());
        simpleMailMessage.setTo(details.getRecipient());
        simpleMailMessage.setFrom(sender);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendEmailWithAttachment(EmailDetails emailDetails)throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);


        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(emailDetails.getRecipient());
        mimeMessageHelper.setSubject(emailDetails.getSubject());
        mimeMessageHelper.setText(emailDetails.getMessageBody());

//        FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
//        mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        String attachmentPath = "classpath:assets/LOGO-DIO-COLOR.png";
        ClassPathResource classPathResource = new ClassPathResource(attachmentPath);

        try {
            InputStream inputStream = classPathResource.getInputStream();
            mimeMessageHelper.addAttachment("LOGO-DIO-COLOR.png", (DataSource) inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        javaMailSender.send(mimeMessageHelper.getMimeMessage());

    }
}
