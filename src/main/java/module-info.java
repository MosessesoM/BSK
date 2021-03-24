module BSK {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;
    requires poi.ooxml;
    opens controllers to javafx.fxml;
    exports controllers;
}