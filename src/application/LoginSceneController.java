package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginSceneController {
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField pwField, adminPWField;
	
	@FXML
	Button loginBtn;
	
	public void checkValidEntry(KeyEvent e) {
		TextField source = (TextField)e.getSource();
		int caretPos = source.getCaretPosition();
		String regex = "[^a-zA-Z]";
		int maxLen = 16;
		switch (source.getId()) {
		case "usernameField":
			regex = "[^a-zA-Z0-9]";
			maxLen = 10;
			break;
		case "pwField":
			regex = "[^a-zA-Z0-9!@#$%&*_-]";
			maxLen = 20;
			break;
		case "adminPWField":
			regex = "[^a-zA-Z0-9!@#$%&*_-]";
			maxLen = 20;
			break;
		}
		
		source.setText(source.getText().replaceAll(regex, ""));
		if (source.getText().length() > maxLen) {
			source.setText(source.getText().substring(0, maxLen));
		}
		
		if (usernameField.getText().equals("admin")) {
			adminPWField.setDisable(false);
			adminPWField.setVisible(true);
		} else {
			adminPWField.setVisible(false);
		}
		source.positionCaret(caretPos);
	}
}
