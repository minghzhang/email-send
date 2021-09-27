package com.louis.email.emailsend.controller;

import com.louis.email.emailsend.MainApp;
import com.louis.email.emailsend.tools.EmailTools;
import com.louis.email.emailsend.tools.JavaFXTools;
import com.louis.email.emailsend.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MainFrameController {
    private List<String> selectedFilePaths = new ArrayList<>();

    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField subject;

    @FXML
    private Button settingsButton;

    @FXML
    private Button attachmentButton;

    @FXML
    private HTMLEditor emailContent;

    @FXML
    private Button sendButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label attachmentNameLabel;

    @FXML
    void addAttachment(ActionEvent event) {
        File selectedFile = simpleTools.getSelectedFile();
        selectedFilePaths.add(selectedFile.getAbsolutePath());
        attachmentNameLabel.setText(getSelectedFileNames());
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void reset(ActionEvent event) {
        JavaFXTools javaFXTools = new JavaFXTools();
        javaFXTools.reset(emailAddress, subject);
        javaFXTools.reset(emailContent);
        javaFXTools.reset(attachmentNameLabel);
    }

    @FXML
    void sendEmail(ActionEvent event) throws MessagingException, UnsupportedEncodingException {
        Map<String, String> dataMap = simpleTools.readReturnMap("src/main/resources/properties/data.properties");
        String addresser = dataMap.get("addresser");
        String addressee = emailAddress.getText();
        String subjectText = subject.getText();
        String contentHtmlText = emailContent.getHtmlText();

        String password = dataMap.get("password");

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", dataMap.get("server"));
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties);
        session.setDebug(true);

        MimeMessage message;
        if (selectedFilePaths.isEmpty()) {
            message = new EmailTools().createSimpleMimeMessage(session, addresser, addressee, subjectText, contentHtmlText);
        } else {
            message = new EmailTools().createSimpleMimeMessage(session, addresser, addressee, subjectText, contentHtmlText, selectedFilePaths);
        }
        Transport transport = session.getTransport();
        transport.connect(addresser, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    @FXML
    void settingsClick(ActionEvent event) {
        new MainApp().initSendAccountOptionFrame();
    }

    public void initialize() {
        simpleTools.setLabeledImage(new Labeled[]{settingsButton, sendButton, resetButton, exitButton, attachmentButton},
                new String[]{"src/main/resources/images/option.png", "src/main/resources/images/send.png", "src/main/resources/images/reset.png"
                        , "src/main/resources/images/exit.png", "src/main/resources/images/appendix.png"});
    }

    private String getSelectedFileNames() {
        StringBuilder builder = new StringBuilder();
        for (String selectedFilePath : selectedFilePaths) {
            selectedFilePath = selectedFilePath.substring(selectedFilePath.lastIndexOf("/") + 1);
            builder.append(selectedFilePath).append(" ");
        }
        return builder.toString();
    }

}