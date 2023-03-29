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

public class IndRecController implements Initializable{
	
	private int index;
	

    @FXML
    private Button dltBtn, 
    			   newBtn, 
    			   nxtBtn,
    			   prvBtn, 
    			   saveBtn,
    			   backBtn;

    @FXML
    private TextField playerIDTF,
    				  playerNameTF,
    				  slamIDTF,
    				  slamNameTF,
    				  playerWinningsTF;
    
	
	@FXML
	Label recID;


	@FXML
	protected void newRec() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (playerIDTF.getText().length() > 1 &&
			playerNameTF.getText().length() > 1 &&
			slamIDTF.getText().length() > 1 && 
			slamNameTF.getText().length() > 1 &&
			playerWinningsTF.getText().length() > 1) {
			Main.gSlams.add(new GrandSlam(slamIDTF.getText(), slamNameTF.getText(), playerIDTF.getText(), playerNameTF.getText(), playerWinningsTF.getText()));
			Main.gSlamWriter.buildDocument(Main.gSlams);
		}
	}
	
	@FXML
	protected void goBack() throws IOException {
		Main.ss.switchScene(Main.EDIT_CHOICE_PATH);
	}
	
	@FXML
	protected void nextRecord() {
		if (index <= Main.gSlams.size() -2) {
			index++;
		} else {
			index = 0;
		}
		changeRecord();
	}
	
	@FXML
	protected void prvRecord() {
		if (index > 0) {
			index--;
		} else {
			index = Main.gSlams.size() - 1;
		}
		changeRecord();
	}
	
	@FXML
	protected void dltRecord() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (Main.gSlams.size() == 1) {
			Alert alert = new Alert(AlertType.ERROR,"cannot delete last record!!");
			alert.show();
		} else {
			Main.gSlams.remove(index);
			prvRecord();
			Main.gSlamWriter.buildDocument(Main.gSlams);
		}
	}
	
	@FXML
	protected void saveRecord() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (playerIDTF.getText().length() > 1 &&
				playerNameTF.getText().length() > 1 &&
				slamIDTF.getText().length() > 1 && 
				slamNameTF.getText().length() > 1 &&
				playerWinningsTF.getText().length() > 1) {
				Main.gSlams.get(index).setPlayerID(playerIDTF.getText());
				Main.gSlams.get(index).setPlayerName(playerNameTF.getText());
				Main.gSlams.get(index).setSlamID(slamIDTF.getText());
				Main.gSlams.get(index).setSlamName(slamNameTF.getText());
				Main.gSlams.get(index).setPlayerWinnings(playerWinningsTF.getText());
				Main.gSlamWriter.buildDocument(Main.gSlams);
		} else {
			Alert alert = new Alert(AlertType.ERROR,"bad player info");
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
			maxLen = 16;
			break;
		case "slamIDTF":
			regex = "[^a-zA-Z0-9_]";
			maxLen = 8;
			break;
		case "slamNameTF":
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
		recID.setText("Individual Record # " + (index + 1 ));;
		playerIDTF.setText(Main.gSlams.get(index).getPlayerID());
		playerNameTF.setText(Main.gSlams.get(index).getPlayerName());
		slamIDTF.setText(Main.gSlams.get(index).getSlamID());
		slamNameTF.setText(Main.gSlams.get(index).getSlamName());
		playerWinningsTF.setText(Main.gSlams.get(index).getPlayerWinnings());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		index = 0;
		changeRecord();
	}
}
