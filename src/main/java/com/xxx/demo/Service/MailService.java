package com.xxx.demo.Service;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.xxx.demo.Common.Random;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service("mailService")
public class MailService {
    boolean isSSL = true;
    String host = "smtp.163.com";
    int port = 465;
    String from = "tql_tql@163.com";
    boolean isAuth = true;
    final String username = "tql_tql@163.com";
    final String password = "HuaWei2018";

    public String sendMail(String to) {
        Properties props = new Properties();
        props.put("mail.smtp.ssl.enable",isSSL);
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port",port);
        props.put("mail.smtp.auth",isAuth);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            String vcode = Random.getRandomNumString(6);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("主题");
            message.setText(vcode);
            Transport.send(message);
            return vcode;
        }
        catch(AddressException e)
        {
            e.printStackTrace();
        }
        catch(MessagingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

