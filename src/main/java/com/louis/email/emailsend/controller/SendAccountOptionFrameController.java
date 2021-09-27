package com.louis.email.emailsend.controller;


import com.louis.email.emailsend.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @date : 2021/9/27
 */
public class SendAccountOptionFrameController {

    private Stage stage;

    @FXML
    private TextField senderEmail;

    @FXML
    private TextField password;

    @FXML
    private TextField server;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        String addresser = senderEmail.getText();
        String passwordText = password.getText();
        String serverText = server.getText();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("addresser", addresser);
        dataMap.put("password", passwordText);
        dataMap.put("server", serverText);
        new SimpleTools().dataWriteProperties("src/main/resources/properties/data.properties", dataMap);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        new SimpleTools().setLabeledImage(new Labeled[]{saveButton, cancelButton},
                new String[]{"src/main/resources/images/save.png", "src/main/resources/images/cancel.png"});

        Map<String, String> dataMap = new SimpleTools().readReturnMap("src/main/resources/properties/data.properties");
        String addresser = dataMap.get("addresser");
        String passwordText = dataMap.get("password");
        String serverText = dataMap.get("server");
        senderEmail.setText(addresser);
        password.setText(passwordText);
        server.setText(serverText);
    }
}

