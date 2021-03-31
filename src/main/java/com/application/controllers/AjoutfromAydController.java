package com.application.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import com.application.JavaFxWithSpring1Application;
import com.application.entities.Assure_cvm;
import com.application.entities.Ayd_cvm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;

public class AjoutfromAydController implements Initializable{
	
	@FXML
    private TextField nom;
	
	@FXML
    private AnchorPane principalPane;

    @FXML
    private TextField prenom;

    @FXML
    private DatePicker dateN;

    @FXML
    private DatePicker fin_pc;

    @FXML
    private ComboBox<String> lp;

    @FXML
    private Button validerBtn;
    
    boolean annuler = false;
    
    private Alert alerte;

    @FXML
    void vlideForm(ActionEvent event) {
            try {
				if(! ispleinForm()) {
					alerte.setTitle("Champ vide !");
					alerte.setHeaderText("Veuillez remplire tous les champs vide!");
					alerte.setAlertType(AlertType.WARNING);
					alerte.show();
				}else {
					createAyd();
					Stage stage = (Stage) validerBtn.getScene().getWindow();
					stage.close();
					alerte.setTitle("Opération réussi!");
					alerte.setHeaderText("l'opération a été bien effectuée !");
					alerte.setAlertType(AlertType.INFORMATION);
					alerte.show();
				}
				
			} catch (Exception e) {
				alerte.setTitle("Erreur !");
				alerte.setHeaderText("Oops! "+e.getMessage());
				alerte.setAlertType(AlertType.ERROR);
				alerte.show();
			}
    	
    }
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			alerte = new Alert(AlertType.NONE);
			ObservableList<String> list = FXCollections.observableArrayList("Conjoint","Enfant","Ascendant");
			lp.setItems(list);
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
		
		principalPane.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER)
			{
		           try {
						if(! ispleinForm()) {
							alerte.setTitle("Champ vide !");
							alerte.setHeaderText("Veuillez remplire tous les champs vide!");
							alerte.setAlertType(AlertType.WARNING);
							alerte.show();
						}else {
							createAyd();
							Stage stage = (Stage) validerBtn.getScene().getWindow();
							stage.close();
							if(! annuler) {
								
								alerte.setTitle("Opération réussi!");
								alerte.setHeaderText("l'opération a été bien effectuée !");
								alerte.setAlertType(AlertType.INFORMATION);
								alerte.show();

							}
						}
						
					} catch (Exception exp) {
						alerte.setTitle("Erreur !");
						alerte.setHeaderText("Oops! "+exp.getMessage());
						alerte.setAlertType(AlertType.ERROR);
						alerte.show();
					}

			}

		});
	}
	
	private boolean ispleinForm() {
		if(this.nom.getText().isEmpty() || this.prenom.getText().isEmpty() || this.dateN.getValue() == null
		   || lp.getSelectionModel().getSelectedItem() == null || this.fin_pc.getValue() == null) {
			return false;
		}
		return true;
	}
	
	private void createAyd() {
		try {
			alerte.setTitle("Confirmation");
			alerte.setHeaderText("êtes-vous sur de ces informations?");
			alerte.setAlertType(AlertType.CONFIRMATION); 
            
            Optional<ButtonType> option = alerte.showAndWait();
            if (option.get() == ButtonType.OK) {
    			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    			Ayd_cvm ayd_cvm = null;
    			if(this.lp.getSelectionModel().getSelectedItem() == "Conjoint") {
    				ayd_cvm = new Ayd_cvm(ListeAssureController.assure_cvmModifier, "C", format.parse(this.fin_pc.getValue().toString()), "", format.parse(this.dateN.getValue().toString()), this.nom.getText(), this.prenom.getText());			
    		    }else if(this.lp.getSelectionModel().getSelectedItem() == "Enfant") {
    		    	ayd_cvm = new Ayd_cvm(ListeAssureController.assure_cvmModifier, "E", format.parse(this.fin_pc.getValue().toString()), "", format.parse(this.dateN.getValue().toString()), this.nom.getText(), this.prenom.getText());			
   		    	
    		    }else if(this.lp.getSelectionModel().getSelectedItem() == "Ascendant"){
    				ayd_cvm = new Ayd_cvm(ListeAssureController.assure_cvmModifier, "ASC", format.parse(this.fin_pc.getValue().toString()), "", format.parse(this.dateN.getValue().toString()), this.nom.getText(), this.prenom.getText());			    		    	
    		    }
    			JavaFxWithSpring1Application.aydReposirtory2.save(ayd_cvm);
            }else {
            	annuler = true;
            }
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}

	}

}
