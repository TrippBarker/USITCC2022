package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class EditChoiceController {
    @FXML
    private Button backBtn, editBtn;

    @FXML
    private ToggleGroup editChoice;

    @FXML
    private RadioButton indRecBtn, usersBtn;

    @FXML
    protected void edit(ActionEvent event) throws IOException {
    	if (indRecBtn.isSelected()) {
    		Main.ss.switchScene(Main.IND_REC_SCENE_PATH);
    	} else if (usersBtn.isSelected()) {
    		Main.ss.switchScene(Main.NEW_USER_SCENE_PATH);
    	}
    }

    @FXML
    protected void goBack(ActionEvent event) throws IOException {
    	Main.ss.switchScene(Main.LOGIN_SCENE_PATH);
    }
}
