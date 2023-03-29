package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CreateUserController implements Initializable{
	
	private int index;
	

    @FXML
    private Button dltBtn, 
    			   newBtn, 
    			   nxtBtn,
    			   prvBtn, 
    			   saveBtn,
    			   backBtn;

    @FXML
    private TextField fNameTF,
    				  lNameTF,
    				  idTF,
    				  usernameTF,
    				  passwordTF;
    
	
	@FXML
	Label recID;


	@FXML
	protected void newRec() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (fNameTF.getText().length() > 1 &&
			lNameTF.getText().length() > 1 &&
			idTF.getText().length() > 1 && 
			usernameTF.getText().length() > 1 &&
			passwordTF.getText().length() > 1) {
			Player player = new Player(usernameTF.getText(), passwordTF.getText(), idTF.getText(), fNameTF.getText(), lNameTF.getText());
			player.hashPass();
			Main.usersAL.add(player);
			Main.userWriter.buildDocument(Main.usersAL);
			index = Main.usersAL.size() - 1;
			changeRecord();
		}
	}
	
	@FXML
	protected void goBack() throws IOException {
		Main.ss.switchScene(Main.EDIT_CHOICE_PATH);
	}
	
	@FXML
	protected void nextRecord() {
		if (index <= Main.usersAL.size() -2) {
			index++;
		} else {
			index = 1;
		}
		changeRecord();
	}
	
	@FXML
	protected void prvRecord() {
		if (index > 1) {
			index--;
		} else {
			index = Main.usersAL.size() - 1;
		}
		changeRecord();
	}
	
	@FXML
	protected void dltRecord() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (Main.usersAL.size() == 2) {
			Alert alert = new Alert(AlertType.ERROR,"cannot delete last user!!");
			alert.show();
		} else {
			Main.usersAL.remove(index);
			prvRecord();
			Main.userWriter.buildDocument(Main.usersAL);
		}
	}
	
	@FXML
	protected void saveRecord() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (fNameTF.getText().length() > 1 &&
			lNameTF.getText().length() > 1 &&
			idTF.getText().length() > 1 && 
			usernameTF.getText().length() > 1 &&
			passwordTF.getText().length() > 1) {
			Player player = new Player(usernameTF.getText(), passwordTF.getText(), idTF.getText(), fNameTF.getText(), lNameTF.getText());
			player.hashPass();
			Main.usersAL.set(index, player);
			Main.userWriter.buildDocument(Main.usersAL);
			changeRecord();
		} else {
			Alert alert = new Alert(AlertType.ERROR,"bad user info");
			alert.show();
		}
	}
	
	public void checkValidEntry(KeyEvent e) {
		TextField source = (TextField)e.getSource();
		int caretPos = source.getCaretPosition();
		String regex = "[^a-zA-Z]";
		int maxLen = 16;
		switch (source.getId()) {
		case "playerIDTF":
			regex = "[^a-zA-Z0-9]";
			maxLen = 10;
			break;
		case "playerNameTF":
			regex = "[^a-zA-Z\\s]";
			maxLen = 16;
			break;
		case "slamIDTF":
			regex = "[^a-zA-Z0-9_]";
			maxLen = 8;
			break;
		case "slamNameTF":
			regex = "[^a-zA-Z\\s.]";
			maxLen = 20;
			break;
		case "playerWinningsTF":
			regex = "[^0-9]";
			maxLen = 20;
			break;
			
		}
		source.setText(source.getText().replaceAll(regex, ""));
		if (source.getText().length() > maxLen) {
			source.setText(source.getText().substring(0, maxLen));
		}
		source.positionCaret(caretPos);
	}
	
	public void changeRecord() {
		recID.setText("User # " + (index));;
		Player player = (Player)Main.usersAL.get(index);
		fNameTF.setText(player.getFName());
		lNameTF.setText(player.getLName());
		idTF.setText(player.getPlayerID());
		usernameTF.setText(player.getUsername());
		passwordTF.setText(player.getPassword());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		index = 1;
		changeRecord();
	}
}
