module MedSystem {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires json.simple;
    requires java.net.http;

    opens home;
    opens  home.fxml to javafx.fxml;
    opens  home.css to javafx.fxml;
    opens  home.controllers to javafx.fxml;
    opens  home.icons to javafx.fxml;
}