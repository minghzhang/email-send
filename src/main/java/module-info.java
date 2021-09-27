module com.louis.email.emailsend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires mail;
    requires activation;

    opens com.louis.email.emailsend to javafx.fxml;
    exports com.louis.email.emailsend;
    exports com.louis.email.emailsend.controller;
    opens com.louis.email.emailsend.controller to javafx.fxml;
}