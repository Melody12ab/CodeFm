package com.tools;

import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;


public class SendEmailUtil {
    private static final Logger logger = Logger.getLogger(SendEmailUtil.class);

    public static void sendEmail(String[] to, String subject, String messageText) {
        sendAttfileMail(to, subject, messageText, null, null);
    }

    public static void sendAttfileMail(String[] to, String subject, String messageText, String attfileName, String attfilePath) {
        sendAttfileMail(to, null, subject, messageText, attfileName, attfilePath);
    }

    public static void sendAttfileMail(String[] to, String[] cs, String subject, String messageText, String attfileName, String attfilePath) {
        ReadConfigUtil rdf=new ReadConfigUtil("systemCfg.properties",true);
        boolean sessionDebug = false;
        Properties props = System.getProperties();
        props.put("mail.smtp.host", rdf.getValue("mail.smtp.host"));
        props.put("mail.transport.protocol", "smpt");
        props.put("mail.smtp.auth", "true");
        // 系统需要的信息
        Session session = Session.getInstance(props);
        // 一次对话，一个session ，这个session 要货去固定的发送邮件信息
        session.setDebug(sessionDebug);
//		String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//		path = path.split("WEB-INF")[0]+File.separator+"excel"+File.separator;
        try {
            BodyPart messageBodyPart = new MimeBodyPart();
            Message meg = new MimeMessage(session);// 生成消息实例
            Multipart multipart = new MimeMultipart();
            meg.setFrom(new InternetAddress(rdf.getValue("mail.smtp.from")));// 指定发件人
            //生成收件人地址数组
            InternetAddress[] address = new InternetAddress[to.length];
            for (int i = 0; i < address.length; i++) {
                address[i] = new InternetAddress(to[i]);
            }
            InternetAddress[] addresscs=null;
            if(cs!=null){
                addresscs = new InternetAddress[cs.length];
                for (int i = 0; i < addresscs.length; i++) {
                    addresscs[i] = new InternetAddress(cs[i]);
                }
            }
            meg.setRecipients(Message.RecipientType.TO, address);// 指定收件人数组
            meg.setRecipients(Message.RecipientType.CC, addresscs);//抄送人
            meg.setSubject(subject);// 指定主题
            meg.setText(messageText);// 指定正文
            meg.setSentDate(new Date());// 指定发送时间
            messageBodyPart.setContent(messageText, "text/html;charset=utf-8");//给BodyPart对象设置内容和格式/编码方式
            multipart.addBodyPart(messageBodyPart);
            //附件
            if (attfilePath != null && !attfilePath.equals("")) {
                MimeBodyPart messageBodyPart2 = new MimeBodyPart();
                // 得到数据源
                FileDataSource fds = new FileDataSource(attfilePath + attfileName);
                // 得到附件本身并至入BodyPart
                messageBodyPart2.setDataHandler(new DataHandler(fds));
                messageBodyPart2.setFileName(MimeUtility.encodeText(attfileName));
                multipart.addBodyPart(messageBodyPart2);
            }
            meg.setContent(multipart);
            meg.saveChanges();// 保存信息
            Transport transport = session.getTransport("smtp");// 产生传输对象
            transport.connect(rdf.getValue("mail.smtp.host"), rdf.getValue("mail.smtp.user"), rdf.getValue("mail.smtp.password"));
            // 连接到你自己的主机
            transport.sendMessage(meg, meg.getAllRecipients());// 开始发送
            logger.info("send   over ");
        } catch (Exception ex) {
            logger.error("send   error " + ex);
        }
    }

    public static void main(String[] args) {
//        System.out.println("start send email");
//        sendEmail(new String[]{"jilong.li@haiwan.com"},"nihao","shi yig echashcia");
//        ReadConfigUtil rdf=new ReadConfigUtil("systemCfg.properties",true);
//        System.out.println(rdf.getValue("mail.smtp.host"));
    }

}
