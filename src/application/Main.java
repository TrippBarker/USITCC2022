package application;
	
import java.util.ArrayList;

import application.readerswriters.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static final String TABLE_VIEW_SCENE_PATH = "scenes/TableView.fxml";
	public static final String LOGIN_SCENE_PATH = "scenes/LoginScene.fxml";
	public static final String IND_REC_SCENE_PATH = "scenes/IndividualRecord.fxml";
	public static final String NEW_USER_SCENE_PATH = "scenes/CreateNewUser.fxml";
	public static final String EDIT_CHOICE_PATH = "scenes/EditChoice.fxml";
	
	public static final GrandSlamWriter gSlamWriter = new GrandSlamWriter();
	public static final GrandSlamReader gSlamReader = new GrandSlamReader();
	
	public static final UserWriter userWriter = new UserWriter();
	public static final UserReader userReader = new UserReader();
	
	public static ObservableList<GrandSlam> gSlams = FXCollections.observableArrayList(gSlamReader.readXML());
	public static ArrayList<User> usersAL = userReader.readXML();
	
	public static User currentUser;
	
	public static SceneSwitcher ss;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ss = new SceneSwitcher(primaryStage);
		ss.switchScene(EDIT_CHOICE_PATH);
		//gSlams.add(new GrandSlam("2022_1", "Austrailian Open", "P002", "Rafael Nadal", "$ 2,275,000*"));
		//gSlams.add(new GrandSlam("2022_2", "French Open", "P002", "Rafael Nadal", "$ 1,925,000*"));
		//gSlams.add(new GrandSlam("2020_2", "French Open", "P002", "Rafael Nadal", "$ 1,775,000*"));
		//gSlams.add(new GrandSlam("2019_2", "French Open", "P002", "Rafael Nadal", "$ 1,655,000*"));
		//gSlams.add(new GrandSlam("2019_4", "U.S. Open", "P002", "Rafael Nadal", "$ 2,125,000*"));
		//gSlams.add(new GrandSlam("2018_1", "Austrailian Open", "P001", "Rodger Federer", "$ 2,000,000*"));
		//gSlams.add(new GrandSlam("2018_2", "French Open", "P002", "Rafael Nadal", "$ 1,500,000*"));
		//gSlams.add(new GrandSlam("2017_1", "Austrailian Open", "P001", "Rodger Federer", "$ 1,875,000*"));
		//gSlams.add(new GrandSlam("2017_2", "French Open", "P002", "Rafael Nadal", "$ 1,375,000*"));
		//gSlams.add(new GrandSlam("2017_3", "Wimbledon Open", "P001", "Rodger Federer", "$ 2,250,000*"));
		//gSlams.add(new GrandSlam("2017_4", "U.S. Open", "P002", "Rafael Nadal", "$ 1,925,000*"));
		//gSlamWriter.buildDocument(gSlams);
		
		//usersAL.add(new Admin("admin", "nimda", "superuser"));
		//usersAL.add(new Player("rfederer", "gsw20", "P001", "Roger", "Federer"));
		//usersAL.add(new Player("rnadal", "gsw22", "P002", "Rafael", "Nadal"));
		//userWriter.buildDocument(usersAL);
		
		for (User user : usersAL) {
			System.out.println(user.toString());
			System.out.println("-----------------------------------");
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
