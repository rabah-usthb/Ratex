package application;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class ConvertController {
	InitializationController init;
	void setInit(InitializationController init){
		this.init = init;
	}
String selectedUnit="mm";
@FXML
RadioButton mmButton;
@FXML
RadioButton cmButton;
@FXML
RadioButton inButton;
@FXML
RadioButton ptButton;
@FXML

private void selectIn() {
	selectedUnit = "in";
	ptButton.setSelected(false);
	mmButton.setSelected(false);
	cmButton.setSelected(false);
}

@FXML
private void selectMm() {
	selectedUnit = "mm";
	ptButton.setSelected(false);
	inButton.setSelected(false);
	cmButton.setSelected(false);
}

@FXML
private void selectCm() {
	selectedUnit = "cm";
	ptButton.setSelected(false);
	inButton.setSelected(false);
	mmButton.setSelected(false);
}
@FXML
private void selectPt() {
	selectedUnit = "pt";
	cmButton.setSelected(false);
	inButton.setSelected(false);
	mmButton.setSelected(false);
}

@FXML
private void applyConversion() {
	
	this.init.Convert(selectedUnit);
}
}
