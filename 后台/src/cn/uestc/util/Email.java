package cn.uestc.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Email {

	/*
	 * 账号密码改成自己的！！！
	 * 密码是对应邮箱的授权码
	 */
    public static String myEmailAccount = "xxxxx@qq.com";
    public static String myEmailPassword = "xxxxx";//出于安全考虑，这里可能需要改成从数据库读取出来
    public static String myEmailSMTPHost = "smtp.qq.com";

    public static String send(String userId,String receiveMailAccount) throws Exception {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        Session session = Session.getInstance(props);
        session.setDebug(true);                                 
        
        String code = (int)(Math.random()*10) +""+ (int)(Math.random()*10) +""+ (int)(Math.random()*10) +""+ (int)(Math.random()*10);
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,userId,code);

        Transport transport = session.getTransport();

        transport.connect(myEmailAccount, myEmailPassword);

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
        return code;
    }

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String userId,String code) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "互联网+智慧物流质询系统", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, userId + "用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("互联网+智慧物流质询系统", "UTF-8");

        // 5. Content: 邮件正文
        message.setContent("您的验证码是: " + code, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}