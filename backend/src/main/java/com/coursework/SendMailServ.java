package com.coursework;

import com.coursework.controller.UserController;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendMailServ {
    public final JavaMailSender emailSender;

    public final String NEW_USER_HELLO_SUBJECT = "Привет";
    public final String NEW_USER_HELLO_TEXT = "Новый сотрудник";

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public SendMailServ(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void SendMail (String to, String subject, String text) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Парользабыл <javamail@dunaevai135.me>");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        LOG.info("message: " + message.toString());
//        emailSender.send(message);

        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("dunaevai135_cv4","very_strong_pass")
                .setXmppDomain("jabber.ru")
                .setHost("jabber.ru")
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .build();

        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
        connection.connect(); //Establishes a connection to the server
        connection.login(); //Logs in
        ChatManager chatManager = ChatManager.getInstanceFor(connection);
        EntityBareJid jid = JidCreate.entityBareFrom("dunaevai135@jabber.ru");
        Chat chat = chatManager.createChat(jid);
        chat.sendMessage(subject + " " + text);
    }
}
