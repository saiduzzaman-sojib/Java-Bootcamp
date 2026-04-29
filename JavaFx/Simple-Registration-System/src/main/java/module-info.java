module som.sojib.sojibsir {
    requires javafx.controls;
    requires javafx.fxml;


    opens som.sojib.sojibsir to javafx.fxml;
    exports som.sojib.sojibsir;
}