package application;

import java.net.URL;

import java.util.ResourceBundle;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InitializationController implements Initializable {

	private static String DocumentType[] = {"article","report","proc","book","letter","memoir","beamer","minimal","slides"}; 
    @FXML
    private ComboBox<String> TypeComboBox;

@FXML
Button MonoButton;
@FXML
Button PolyButton;
@FXML
Label MarginLabel;
@FXML 
TextField MarginField;
@FXML
Label TopLabel;
@FXML
Label LeftLabel;
@FXML
Label RightLabel;
@FXML
Label BottomLabel;
@FXML 
TextField TopField;
@FXML 
TextField LeftField;
@FXML 
TextField RightField;
@FXML 
TextField BottomField;


static int clicked =0;

private void setVisibilityMono(boolean Isvisible) {
	MarginLabel.setVisible(Isvisible);
	MarginField.setVisible(Isvisible);
}

private void setVisibilityPoly(boolean Isvisible) {
	TopLabel.setVisible(Isvisible);
	LeftLabel.setVisible(Isvisible);
	RightLabel.setVisible(Isvisible);
	BottomLabel.setVisible(Isvisible);
	TopField.setVisible(Isvisible);
	LeftField.setVisible(Isvisible);
	RightField.setVisible(Isvisible);
	BottomField.setVisible(Isvisible);
	
}

@FXML
private void switchButtonToPoly() {
	
	if(clicked == 0) {
		setVisibilityMono(false);
		setVisibilityPoly(true);
		MonoButton.setStyle("-fx-background-color:#504B4B;-fx-text-fill: #504B4B;-fx-cursor: none;");
		PolyButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-cursor: hand;");
	    clicked =1;
	}
	
}
@FXML
 private  void switchButtonToMono() {
	if(clicked==1) {
		setVisibilityMono(true);
		setVisibilityPoly(false);
		PolyButton.setStyle("-fx-background-color:#504B4B;-fx-text-fill: #504B4B;-fx-cursor: none;");
		MonoButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-cursor: hand;");
	    clicked =0;
	}
	 
 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for(String type :DocumentType) {
			TypeComboBox.getItems().add(type);
		}
	}
}
