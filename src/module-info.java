module USITCC2022 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.xml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	
	exports application;
}
