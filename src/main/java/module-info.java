module com.chuj.testowanie_chuja {
    requires javafx.controls;
    exports com.testowanie;
    requires javafx.fxml;
	requires javafx.graphics;
    
    opens com.testowanie to javafx.fxml;
    opens com.testowanie.controller to javafx.fxml;
    
}