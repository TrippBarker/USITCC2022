package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewController implements Initializable{
	
    @FXML
    private TableView<GrandSlam> gSlamsTable;

    @FXML
    private TableColumn<GrandSlam, String> playerIDField;

    @FXML
    private TableColumn<GrandSlam, String> playerNameField;

    @FXML
    private TableColumn<GrandSlam, String> playerNameField1;

    @FXML
    private TableColumn<GrandSlam, String> playerWinningsField;

    @FXML
    private TableColumn<GrandSlam, String> slamIDField;

    @FXML
    private TableColumn<GrandSlam, String> slamNameField;
    
    
	public void buildTable() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (GrandSlam slam : Main.gSlams) {
			System.out.println(slam.getPlayerID());
		}
		slamIDField.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("slamID"));
		slamNameField.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("slamName"));
		playerIDField.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("playerID"));
		playerNameField.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("playerName"));
		playerWinningsField.setCellValueFactory(new PropertyValueFactory<GrandSlam, String>("playerWinnings"));
		gSlamsTable.setItems(Main.gSlams);
	}
}
