package com.louis.email.emailsend.tools;

import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @date : 2021/9/27
 */
public class SimpleTools {

    /**
     * 操作结果：JavaFX设置按钮、标签等组件的图标
     *
     * @param labeleds   需要设置图标的按钮
     * @param imagePaths 图标的路径
     */
    public void setLabeledImage(Labeled[] labeleds, String[] imagePaths) {
        for (int i = 0; i < labeleds.length; i++) {
            labeleds[i].setGraphic(new ImageView(new Image("file:" + imagePaths[i])));
        }
    }

    public void dataWriteProperties(String filePath, Map<String, String> dataMap) {
        Properties properties = new Properties();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            for (String key : dataMap.keySet()) {
                properties.setProperty(key, String.valueOf(dataMap.get(key)));
            }
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, null);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, String> readReturnMap(String filePath) {
        File file = new File(filePath);
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        return map;
    }

    public File getSelectedFile() {
        String selectedFilePath = "";
        FileChooser fileChooser = new FileChooser();
        //打开文件选择框
        File result = fileChooser.showOpenDialog(null);
        if (result != null) {
            selectedFilePath = result.getAbsolutePath();
        }
        return new File(selectedFilePath);
    }
}
