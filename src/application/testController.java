package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class testController {

@FXML
Button MonoButton;
@FXML
Button PolyButton;

static int clicked =0;
@FXML
private void switchButtonToPoly() {
	if(clicked==0) {
		MonoButton.setStyle("-fx-background-color:#504B4B;-fx-text-fill: #504B4B;");
		PolyButton.setStyle("-fx-background-color: black;-fx-text-fill: white;");
	    clicked =1;
	}
	
}
@FXML
 private  void switchButtonToMono() {
	if(clicked==1) {
		PolyButton.setStyle("-fx-background-color:#504B4B;-fx-text-fill: #504B4B;");
		MonoButton.setStyle("-fx-background-color: black;-fx-text-fill: white;");
	    clicked =0;
	}
	 
 }
}
