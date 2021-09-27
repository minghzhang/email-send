package com.louis.email.emailsend.tools;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.web.HTMLEditor;

/**
 * @date : 2021/9/27
 */
public class JavaFXTools {

    public void reset(TextInputControl... textInputControls) {
        for (TextInputControl textInputControl : textInputControls) {
            textInputControl.setText("");
        }
    }

    public void reset(HTMLEditor... htmlEditors) {
        for (HTMLEditor htmlEditor : htmlEditors) {
            htmlEditor.setHtmlText("");
        }
    }

    public void reset(Label... labels) {
        for (Label label : labels) {
            label.setText("");
        }
    }
}
