package com.application.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import com.application.JavaFxWithSpring1Application;
import com.application.entities.Assure_cvm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;

public class AjoutfromController implements Initializable{
	@FXML
    private TextField nom;

	@FXML
    private AnchorPane principalePane;
	
    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField wilaya;

    @FXML
    private TextField daira;

    @FXML
    private DatePicker dateN;

    @FXML
    private RadioButton cont;
    
    @FXML
    private DatePicker dateFinc;

    @FXML
    private RadioButton act;

    @FXML
    private ToggleGroup mySituation;

    @FXML
    private RadioButton ret;


    @FXML
    private TextField nss;
    
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
				boolean test = createAssure();
				if(test == false)
				{
					if(! annuler) {
						alerte.setTitle("Assure existant!");
						alerte.setHeaderText("Cet assuré déjà existe !");
						alerte.setAlertType(AlertType.WARNING);
						alerte.show();
					}
				}else {
					Stage stage = (Stage) validerBtn.getScene().getWindow();
					stage.close();
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
    
    @FXML
    void selectedChoice(ActionEvent event) {
         if(act.isSelected() || ret.isSelected())
         {
        	 dateFinc.setDisable(true);
         }
         else if(cont.isSelected()){
        	 dateFinc.setDisable(false);
         }
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		alerte = new Alert(AlertType.NONE);
		principalePane.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {
	            try {
					if(! ispleinForm()) {
						alerte.setTitle("Champ vide !");
						alerte.setHeaderText("Veuillez remplire tous les champs vide!");
						alerte.setAlertType(AlertType.WARNING);
						alerte.show();
					}else {
						boolean test = createAssure();
						if(test == false)
						{
							if(! annuler) {
								alerte.setTitle("Assure existant!");
								alerte.setHeaderText("Cet assuré déjà existe !");
								alerte.setAlertType(AlertType.WARNING);
								alerte.show();
							}
						}else {
							Stage stage = (Stage) validerBtn.getScene().getWindow();
							stage.close();
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
		if(this.nss.getText().isEmpty() || this.nom.getText().isEmpty() || this.prenom.getText().isEmpty() || this.dateN.getValue() == null
			|| this.adresse.getText().isEmpty() || this.wilaya.getText().isEmpty()
			|| this.daira.getText().isEmpty() || this.mySituation.getSelectedToggle() == null || (this.cont.isSelected() && this.dateFinc.getValue() == null)) {
			return false;
		}
		return true;
	}
	
	private boolean createAssure() {
		try {
			alerte.setTitle("Confirmation");
			alerte.setHeaderText("êtes-vous sur de ces informations?");
			alerte.setAlertType(AlertType.CONFIRMATION); 
            
            Optional<ButtonType> option = alerte.showAndWait();
            if (option.get() == ButtonType.OK) {
    			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    			Assure_cvm assure=null;
    			if(act.isSelected()) {
    				assure = new Assure_cvm(this.nss.getText(), "ACTIF", this.nom.getText(), this.prenom.getText(), format.parse(this.dateN.getValue().toString()), null, this.adresse.getText(), this.wilaya.getText(), this.daira.getText());
    			}else if(ret.isSelected()){
    				assure = new Assure_cvm(this.nss.getText(), "RETRAITÉ", this.nom.getText(), this.prenom.getText(), format.parse(this.dateN.getValue().toString()), null, this.adresse.getText(), this.wilaya.getText(), this.daira.getText());			
    			}else if(cont.isSelected()){
    			    assure = new Assure_cvm(this.nss.getText(), "CONTRACTUEL", this.nom.getText(), this.prenom.getText(), format.parse(this.dateN.getValue().toString()),  format.parse(this.dateFinc.getValue().toString()), this.adresse.getText(), this.wilaya.getText(), this.daira.getText());
    			}
                Assure_cvm assureTest = JavaFxWithSpring1Application.assureRepository.getById(this.nss.getText());
                if(assureTest != null) {
                	return false;
                }else {
                	JavaFxWithSpring1Application.assureRepository.save(assure);
                	
                }

            }else {
            	Stage stage = (Stage) validerBtn.getScene().getWindow();
            	stage.close();
            	annuler = true;
            	return false;
            }
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
		return true;

	}

}
