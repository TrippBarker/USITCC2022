package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class LoginSceneController {
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField pwField, adminPWField;
	
	@FXML
	Button loginBtn;
	
	@FXML
	Text errorMessage;
	
	public void loginUser() throws IOException {
		String hashedPass = hashPass(usernameField.getText(), pwField.getText());
		
		for (User user: Main.usersAL) {
			if (usernameField.getText().equals(user.getUsername())) {
				if (user instanceof Admin) {
					Admin admin = (Admin) user;
					String adminHashedPass = hashPass(usernameField.getText(), adminPWField.getText());
					if (admin.getPassword().equals(hashedPass) && admin.getAdminPassword().equals(adminHashedPass)) {
						Main.ss.switchScene(Main.TABLE_VIEW_SCENE_PATH);
					}
					else {
						errorMessage.setVisible(true);
					}
				} else {
					if (user.getPassword().equals(hashedPass)) {
						Main.ss.switchScene(Main.TABLE_VIEW_SCENE_PATH);
					} else {
						errorMessage.setVisible(true);
					}
				}
			}
		}
		
	}
	
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
	
	private String hashPass(String username, String password) {
		String hash;
		hash = String.valueOf(31 * (username.hashCode() * (password.hashCode())));
		return hash;
	}
}
