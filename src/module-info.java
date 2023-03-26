module USITCC2022 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.xml;
	
	opens application to javafx.graphics, javafx.fxml;
}
