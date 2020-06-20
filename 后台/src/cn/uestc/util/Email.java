package cn.uestc.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Email {

	/*
	 * �˺�����ĳ��Լ��ģ�����
	 * �����Ƕ�Ӧ�������Ȩ��
	 */
    public static String myEmailAccount = "xxxxx@qq.com";
    public static String myEmailPassword = "xxxxx";//���ڰ�ȫ���ǣ����������Ҫ�ĳɴ����ݿ��ȡ����
    public static String myEmailSMTPHost = "smtp.qq.com";

    public static String send(String userId,String receiveMailAccount) throws Exception {
        Properties props = new Properties();                    // ��������
        props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤

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
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);

        // 2. From: ������
        message.setFrom(new InternetAddress(sendMail, "������+�ǻ�������ѯϵͳ", "UTF-8"));

        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, userId + "�û�", "UTF-8"));

        // 4. Subject: �ʼ�����
        message.setSubject("������+�ǻ�������ѯϵͳ", "UTF-8");

        // 5. Content: �ʼ�����
        message.setContent("������֤����: " + code, "text/html;charset=UTF-8");

        // 6. ���÷���ʱ��
        message.setSentDate(new Date());

        // 7. ��������
        message.saveChanges();

        return message;
    }

}