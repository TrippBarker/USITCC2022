package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewController implements Initializable{
	
    @FXML
    private TableColumn<GrandSlam, String> playerIDCol;

    @FXML
    private TableColumn<GrandSlam, String> playerNameCol;

    @FXML
    private TableColumn<GrandSlam, String> playerWinningsCol;

    @FXML
    private TableColumn<GrandSlam, String> slamIDCol;

    @FXML
    private TableColumn<GrandSlam, String> slamNameCol;

    @FXML
    private TableView<GrandSlam> table;
    
    @FXML Button logoutBtn, editBtn;
    
	public void buildTable() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		slamIDCol.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("slamID"));
		slamNameCol.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("slamName"));
		playerIDCol.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("playerID"));
		playerNameCol.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("playerName"));
		playerWinningsCol.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("playerWinnings"));
		if(Main.currentUser instanceof Admin) {
			editBtn.setVisible(true);
			editBtn.setDisable(false);
			table.setItems(Main.gSlams);
		} else {
			Player player = (Player) Main.currentUser;
			ObservableList<GrandSlam> slams = FXCollections.observableArrayList();
			for (GrandSlam slam : Main.gSlams) {
				if(slam.getPlayerID().equals(player.getPlayerID())) {
					slams.add(slam);
				}
			}
			table.setItems(slams);
		}
		
	}
	
	public void edit() {
		System.out.println("hello");
	}
	
	
	public void logout() throws IOException {
		Main.currentUser = new User();
		Main.ss.switchScene(Main.LOGIN_SCENE_PATH);
	}
}
