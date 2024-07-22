package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class InitializationController implements Initializable {
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	private String currentUnity="mm";
	private static String DocumentType[] = {"article","report","proc","book","letter","memoir","beamer","minimal","slides"}; 
    @FXML
    private ComboBox<String> TypeComboBox;
@FXML
HBox MarginBox;
@FXML
HBox TopBox;
@FXML
HBox LeftBox;
@FXML
HBox RightBox;
@FXML
HBox BottomBox;
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
@FXML
Pane PaneInit;

static int clicked =0;
@FXML
private void OpenConvertWindow() {
	try {
	 Stage convertStage = new Stage();
	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressource/Convert.fxml"));
     String css = this.getClass().getResource("/ressource/Convert.css").toExternalForm();
     Parent root;
	
		root = loader.load();
	 ConvertController convert = loader.getController();
	 convert.setInit(this);
     // Set the FXML content to the scene
     Scene scene = new Scene(root);
     scene.getStylesheets().add(css);
     convertStage.setScene(scene);
     convertStage.setResizable(false);
     convertStage.show();
	}catch(IOException e) {
		
	}
}


private void setupKeyEventHandling(TextField field) {
    field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
        if (isNowFocused) {
            field.addEventHandler(KeyEvent.KEY_PRESSED, event -> handleKeyEvent(event, field));
        } else {
            field.removeEventHandler(KeyEvent.KEY_PRESSED, event -> handleKeyEvent(event, field));
        }
    });
}

private void handleKeyEvent(KeyEvent event,TextField field) {
    if (event.isShiftDown() && event.getCode() == KeyCode.UP) {
        incrementField(field);
    }
    else if(event.isShiftDown() && event.getCode() == KeyCode.DOWN) {
        decrementField(field);
    }
}

private void decrementField(TextField field) {
	if(!field.getText().isBlank()) {
	double number =Double.parseDouble(field.getText());
	if(number-0.1>=0) {
	DecimalFormat df = new DecimalFormat("#.##");
	field.setText(String.valueOf(Double.parseDouble(df.format(number-0.1))));
	}
	else {
		field.setText("0.00");

	}
	}
}

private void incrementField(TextField field) {
	if(!field.getText().isBlank()) {
	double number =Double.parseDouble(field.getText());
	DecimalFormat df = new DecimalFormat("#.##");
	field.setText(String.valueOf(Double.parseDouble(df.format(number+0.1))));
}
}

private boolean IsFloat(String text) {
	String regex="\\s*(\\+\\s*)?\\d+(\\s*\\.\\s*\\d+)?\\s*";
	String reg="\\s*\\+\\s*";
	String rag="\\s*(\\+\\s*)?\\d+(\\s*\\.\\s*)?";
	return text.matches(regex)||text.matches(reg)||text.matches(rag);
}


private void setVisibilityMono(boolean Isvisible) {
	MarginLabel.setVisible(Isvisible);
	MarginBox.setVisible(Isvisible);
}

private void setVisibilityPoly(boolean Isvisible) {
	TopLabel.setVisible(Isvisible);
	LeftLabel.setVisible(Isvisible);
	RightLabel.setVisible(Isvisible);
	BottomLabel.setVisible(Isvisible);
	TopBox.setVisible(Isvisible);
	LeftBox.setVisible(Isvisible);
	RightBox.setVisible(Isvisible);
	BottomBox.setVisible(Isvisible);
	
}

private void setPolyFieldValue(String text) {
	TopField.setText(text);
	LeftField.setText(text);
	RightField.setText(text);
	BottomField.setText(text);
}
@FXML
private void switchButtonToPoly() {
	
	if(clicked == 0) {
		setVisibilityMono(false);
		setVisibilityPoly(true);
		MonoButton.setStyle("-fx-background-color:#504B4B;-fx-text-fill: #504B4B;-fx-cursor: none;");
		PolyButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-cursor: hand;");
	    clicked =1;
	    String MarginText=MarginField.getText();
	    if(!MarginText.isBlank()) {
	    	setPolyFieldValue(MarginText);
	    }
	}
	
}

private void setUpInputListener(TextField field) {

	field.textProperty().addListener((obs, oldText, newText) -> {	
		boolean updated = false;
		if(!updated&&!newText.isBlank()) {
       String AllText = field.getText(); 	
	if (!IsFloat(AllText)) {
    	
    	field.setText(oldText);
    	updated = true;	
    }
	else {
		
    	updated = true;
	}
	
	
	    
	}

});

} 


 void FocusFieldShortKey(KeyEvent event) {
	
	if(clicked==1) {
	
	if(event.getCode() == KeyCode.UP&& event.isControlDown()) {
	
		TopField.requestFocus();
	    TopField.selectEnd();

	}
	else if(event.getCode() == KeyCode.RIGHT&& event.isControlDown()) {
		RightField.requestFocus();
		RightField.selectEnd();
	}
	else if(event.getCode() == KeyCode.LEFT&& event.isControlDown()) {
	
		LeftField.requestFocus();
		LeftField.selectEnd();
	}
	else if(event.getCode() == KeyCode.DOWN&& event.isControlDown()) {
	
		BottomField.requestFocus();
		BottomField.selectEnd();
		
	}
	}
	else {
		if((event.getCode() == KeyCode.UP||event.getCode()==KeyCode.DOWN||event.getCode()==KeyCode.LEFT||event.getCode()==KeyCode.RIGHT) && event.isControlDown()) {
			MarginField.requestFocus();
			MarginField.selectEnd();
		}
	
	}
}

@FXML
 private void switchButtonToMono() {
	if(clicked==1) {
		setVisibilityMono(true);
		setVisibilityPoly(false);
		PolyButton.setStyle("-fx-background-color:#504B4B;-fx-text-fill: #504B4B;-fx-cursor: none;");
		MonoButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-cursor: hand;");
	    clicked =0;
	}
	 
 }

void setNumberField(TextField field , double number) {
	field.setText(String.valueOf(Double.parseDouble(df.format(number))));
	
}

double convertToPt(double number) {
	if(currentUnity.equals("mm")) {
	return number=(double)number/0.35;
	}
	else if(currentUnity.equals("cm")) {
	return number=(double)number/0.035; 
	}
	else if(currentUnity.equals("in")) {
	return number=number*72;
	}
	return number;
}

double convertToIn(double number) {
	if(currentUnity.equals("mm")) {
	return number=(double)number/25.4;
	}
	else if(currentUnity.equals("cm")) {
	return number=(double)number/2.54; 
	}
	else if(currentUnity.equals("pt")) {
	return	number=(double)number/72;
	}
	return number;
}
double convertToCm(double number) {
	if(currentUnity.equals("mm")) {
	return	number=(double)number/10;
	}
	else if(currentUnity.equals("in")) {
	return	number=number*2.54; 
	}
	else if(currentUnity.equals("pt")) {
	return	number=number*0.035;
	}
	return number;
}

double convertToMm(double number) {
	if(currentUnity.equals("cm")) {
		return number=number*10;
	}
	else if(currentUnity.equals("in")) {
	return	number=number*25.4; 
	}
	else if(currentUnity.equals("pt")) {
	return	number=number*0.35;
	}
	return number;
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		MarginField.setFocusTraversable(false);
		TopField.setFocusTraversable(false);
		LeftField.setFocusTraversable(false);
		RightField.setFocusTraversable(false);
		BottomField.setFocusTraversable(false);
		
		for(String type :DocumentType) {
			TypeComboBox.getItems().add(type);
		}
		Platform.runLater(() -> {
			Scene scene = PaneInit.getScene();
            if (scene != null) {
                scene.setOnKeyPressed(this::FocusFieldShortKey);
            } else {
                System.out.println("Scene is null.");
            }
        });
			
		TopField.setOnKeyPressed(this::FocusFieldShortKey);
	    BottomField.setOnKeyPressed(this::FocusFieldShortKey);
		LeftField.setOnKeyPressed(this::FocusFieldShortKey);
		RightField.setOnKeyPressed(this::FocusFieldShortKey);
        
	    setupKeyEventHandling(MarginField);
		setupKeyEventHandling(TopField);
		setupKeyEventHandling(LeftField);
		setupKeyEventHandling(RightField);
		setupKeyEventHandling(BottomField);
		setUpInputListener(TopField);
		setUpInputListener(MarginField);
		setUpInputListener(LeftField);
	    setUpInputListener(RightField);
		setUpInputListener(BottomField);
	}

	
 Set<Button> fetchButtonFromHbox(HBox box){
	 Set<Node> nodes = box.lookupAll(".Unit");
     // Cast the nodes to Button
    return nodes.stream()
           .filter(node -> node instanceof Button)
           .map(node -> (Button) node)
           .collect(Collectors.toSet());

 }
	
 
 void convertField(TextField field,String Unit) {
	 if(!IsFieldEmpty(field)) {   
		  double number = fetchNumberField(field);
		  double convertedNumber=0;
		  switch(Unit) {
		  case "mm":
			  convertedNumber = convertToMm(number);
			  break;
		  case "cm":
			  convertedNumber = convertToCm(number);
			  break;
		  case "pt":
			  convertedNumber = convertToPt(number);
			break;
		  case "in":
			  convertedNumber = convertToIn(number);
			break;
		  	  
		  }
		  setNumberField(field,convertedNumber); 
	  }
 }
 
 void convertPoly(String Unit) {
	 convertField(TopField, Unit);
	 convertField(LeftField, Unit);
	 convertField(RightField, Unit);
	 convertField(BottomField, Unit);
 }
 
 void convertMono(String Unit) {
	 convertField(MarginField, Unit);
 }
 
 boolean IsFieldEmpty(TextField field) {
	 return field.getText().isBlank() && field.getText().isEmpty();
 }
 
 double fetchNumberField(TextField field) {
	 return Double.parseDouble(field.getText());
 }
 void Convert(String Unit) {
	
	
     Set<Button> buttons = fetchButtonFromHbox(MarginBox);
     buttons.addAll(fetchButtonFromHbox(TopBox));
     buttons.addAll(fetchButtonFromHbox(BottomBox));
     buttons.addAll(fetchButtonFromHbox(LeftBox));
     buttons.addAll(fetchButtonFromHbox(RightBox));
     System.out.println(buttons);
	for(Button button : buttons) {
		button.setText(Unit);
	}
	
	convertMono(Unit);
	convertPoly(Unit);
		 currentUnity = Unit;
 }

}
