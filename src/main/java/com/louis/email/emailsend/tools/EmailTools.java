package com.louis.email.emailsend.tools;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @date : 2021/9/27
 */
public class EmailTools {
    public static final String UTF8 = "UTF-8";

    public MimeMessage createSimpleMimeMessage(Session session, String sendEmail, String receiveMail, String subject, String content) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(sendEmail, sendEmail, UTF8));
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiveMail, receiveMail, UTF8)});
        mimeMessage.setSubject(subject, UTF8);
        mimeMessage.setContent(content, "text/html;charset=UTF-8");
        mimeMessage.setSentDate(new Date());

        //保存设置
        mimeMessage.saveChanges();
        return mimeMessage;
    }

    public MimeMessage createSimpleMimeMessage(Session session, String sendEmail, String receiveMail, String subject, String content, List<String> selectedFilePaths) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(sendEmail, sendEmail, UTF8));
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiveMail, receiveMail, UTF8)});
        mimeMessage.setSubject(subject, UTF8);


        //创建文件节点
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content, "text/html;charset=UTF-8");

        //设置文本和附件的关系（合成一个大的混合的节点）
        MimeMultipart totalPart = new MimeMultipart();
        totalPart.addBodyPart(text);

        for (String selectedFilePath : selectedFilePaths) {
            //创建附件节点
            MimeBodyPart attachment = new MimeBodyPart();
            DataHandler dataHandler = new DataHandler(new FileDataSource(selectedFilePath));
            attachment.setDataHandler(dataHandler);
            attachment.setFileName(MimeUtility.encodeText(dataHandler.getName()));
            totalPart.addBodyPart(attachment);
        }

        //如果有多个附件，可以创建多个 多次添加
        totalPart.setSubType("mixed");

        //设置整个邮件的关系（将最终的混合节点作为邮件的内容）
        mimeMessage.setContent(totalPart);
        mimeMessage.setSentDate(new Date());


        //保存设置
        mimeMessage.saveChanges();
        return mimeMessage;
    }
}
