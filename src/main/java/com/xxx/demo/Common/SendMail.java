package com.xxx.demo.Common;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.xxx.demo.Common.Random;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;


/**
 * @author licf
 * @since 2017-11-09
 */

public class SendMail implements Serializable {

    private  final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 发件人使用发邮件的电子信箱服务器
     * smtp.exmail.qq.com代表是qq的服务器
     */
    private final String SMTP_HOST = "smtp.163.com";

    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private String subject ;

    /**
     * 发件人的用户名
     */
    private String userName;

    /**
     * 发件人的邮件密码
     */
    private String password;

    /**
     * 发件人邮件
     */
    private String mailFrom;

    /**
     * 发件人昵称
     */
    private String nick;


    /**
     *@描述 发送邮件人的相关信息
     *@参数  [userName, password, mailFrom, nick, subject]
     *@返回值
     *@创建人  licf
     *@创建时间 2017/11/09
     *@修改人和其它信息
     */
    public SendMail(String userName, String password, String mailFrom, String nick,String subject) {
        this.userName = userName;
        this.password = password;
        this.mailFrom = mailFrom;
        this.nick = nick;
        this.subject = subject;
    }

    /**
     *@描述 发送邮件方法
     *@参数  [environment, mailTo, content, model]
     *@返回值  void
     *@创建人  licf
     *@创建时间  2017/11/9
     *@修改人和其它信息
     */
    public void send(String environment, String mailTo, String content, String model) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465"); //Linux下需要设置此项，Windows默认localhost为127.0.0.1
            props.put("mail.smtp.localhost", "127.0.0.1");
            //鉴权验证
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }});
            Message msg = new MimeMessage(session);

            InternetAddress fromAddress = null;
            //设置自定义发件人昵称
            if(null != nick && !"".equals(nick)){
                try {
                    nick= MimeUtility.encodeText(nick);
                    fromAddress = new InternetAddress(nick+" <" + mailFrom + ">");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                fromAddress = new InternetAddress(mailFrom);
            }

            // 设置发件人地址信息
            msg.setFrom(fromAddress);

            // 设置收件人地址信息
            String[] to = mailTo.split(",");
            for(int i = 0; i < to.length;i++) {
                InternetAddress toAddress = new InternetAddress(to[i]);
                msg.addRecipient(Message.RecipientType.TO,toAddress);
            }

            //设置邮件主题
            /*if (Constant.SERVER_ENV_UAT.equals(environment )) {
                msg.setSubject("【测试环境】" + model +  subject);
            } else if(Constant.SERVER_ENV_DEV.equals(environment )){
                msg.setSubject("【开发环境】" + model +  subject);
            } else if (Constant.SERVER_ENV_PRODUCT.equals(environment )){
                msg.setSubject("【生产环境】" + model +  subject);
            }else{
                logger.error("未知的环境变量");
                msg.setSubject("【未知环境】" + model +  subject);
            }*/
            msg.setSubject("主题"+model+ subject);
            // 邮件内容
            msg.setText(content);
            //发送时间
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}


