package application;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class IndRecController {
	

    @FXML
    private Button dltBtn, 
    			   newBtn, 
    			   nxtBtn,
    			   prvBtn, 
    			   saveBtn;

    @FXML
    private TextField playerIDTF,
    				  playerNameTF,
    				  slamIDTF,
    				  slamNameTF,
    				  playerWinningsTF;


	@FXML
	protected void newRec() throws TransformerConfigurationException, ParserConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		if (playerIDTF.getText().length() > 1 &&
			playerNameTF.getText().length() > 1 &&
			slamIDTF.getText().length() > 1 && 
			slamNameTF.getText().length() > 1 &&
			playerWinningsTF.getText().length() > 1) {
			Main.gSlams.add(new GrandSlam(slamIDTF.getText(), slamNameTF.getText(), playerIDTF.getText(), playerNameTF.getText(), playerWinningsTF.getText()));
			Main.gSlamWriter.buildDocument(Main.gSlams);
			System.out.println("hello");
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
}
