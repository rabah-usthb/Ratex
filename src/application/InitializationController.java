package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class InitializationController implements Initializable {

	private static String DocumentType[] = {"article","report","proc","book","letter","memoir","beamer","minimal","slides"}; 
    @FXML
    private ComboBox<String> TypeComboBox;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for(String type :DocumentType) {
			TypeComboBox.getItems().add(type);
		}
	}
}
