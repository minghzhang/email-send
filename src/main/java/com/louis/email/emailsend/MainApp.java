package com.louis.email.emailsend;

import com.louis.email.emailsend.controller.SendAccountOptionFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("邮件发送器");

        initMainFrame();

    }

    private void initMainFrame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("MainFrame.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载设置界面
     */
    public void initSendAccountOptionFrame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("SendAccountOptionFrame.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("设置");
            // 设置该界面不可缩放
            stage.setResizable(true);
            // 设置该界面总是处于最顶端
            stage.setAlwaysOnTop(true);
            // 设置模态窗口,该窗口阻止事件传递到任何其他应用程序窗口
            stage.initModality(Modality.APPLICATION_MODAL);
            //设置主容器为主界面舞台
            stage.initOwner(primaryStage);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            // 获取SendAccountOptionFrameController控制器
            SendAccountOptionFrameController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.showAndWait();
        } catch (IOException ex) {

        }
    }

    public static void main(String[] args) {
        launch();
    }
}