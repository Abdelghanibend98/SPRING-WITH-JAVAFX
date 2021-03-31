package com.application.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.application.JavaFxWithSpring1Application;
import com.application.entities.Assure_cvm;

import javafx.collections.FXCollections;
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

public class ModifFromController implements Initializable{
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
    private RadioButton act;

    @FXML
    private ToggleGroup mySituation;

    @FXML
    private RadioButton ret;


    @FXML
    private TextField nss;
    
    @FXML
    private Button validerBtn;
    
    @FXML
    private RadioButton cont;


    @FXML
    private DatePicker dateFinc;

    
    
    
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
					alerte.setTitle("Confirmation");
					alerte.setHeaderText("êtes-vous sur de modifier cet assuré ?");
					alerte.setAlertType(AlertType.CONFIRMATION); 
	                
	                Optional<ButtonType> option = alerte.showAndWait();
	                if (option.get() == ButtonType.OK) {
						boolean test = ModifAssure();
						if(test == false)
						{   
							alerte.setTitle("Erreur !");
							alerte.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
							alerte.setAlertType(AlertType.ERROR);
							alerte.show();
						}else {
							Stage stage = (Stage) validerBtn.getScene().getWindow();
						    stage.close();
							alerte.setTitle("Opération réussi!");
							alerte.setHeaderText("l'opération a été bien effectuée !");
							alerte.setAlertType(AlertType.INFORMATION);
							alerte.show();
						}

					}
				}
			} catch (Exception e) {
				alerte.setTitle("Erreur !");
				alerte.setHeaderText("Oops! "+e.getMessage());
				alerte.setAlertType(AlertType.ERROR);
				alerte.show();
			}
    	
    }
    @FXML
    void change(ActionEvent event) {
       if(act.isSelected()) {
    	   dateFinc.setDisable(true);
       }else if(ret.isSelected()){
    	   dateFinc.setDisable(true);
       }else if(cont.isSelected()) {
    	   dateFinc.setDisable(false);
       }
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			alerte = new Alert(AlertType.NONE);
			initStage();
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
		
		principalePane.setOnKeyPressed(ev->{
			if(ev.getCode() == KeyCode.ENTER) {
	            try {
					if(! ispleinForm()) {
						alerte.setTitle("Champ vide !");
						alerte.setHeaderText("Veuillez remplire tous les champs vide!");
						alerte.setAlertType(AlertType.WARNING);
						alerte.show();
					}else {
						alerte.setTitle("Confirmation");
						alerte.setHeaderText("êtes-vous sur de modifier cet assuré ?");
						alerte.setAlertType(AlertType.CONFIRMATION); 
		                
		                Optional<ButtonType> option = alerte.showAndWait();
		                if (option.get() == ButtonType.OK) {
							boolean test = ModifAssure();
							if(test == false)
							{   
								alerte.setTitle("Erreur !");
								alerte.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
								alerte.setAlertType(AlertType.ERROR);
								alerte.show();
							}else {
								Stage stage = (Stage) validerBtn.getScene().getWindow();
							    stage.close();
								alerte.setTitle("Opération réussi!");
								alerte.setHeaderText("l'opération a été bien effectuée !");
								alerte.setAlertType(AlertType.INFORMATION);
								alerte.show();
							}

						}
					}
				} catch (Exception e) {
					alerte.setTitle("Erreur !");
					alerte.setHeaderText("Oops! "+e.getMessage());
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
	
	private boolean ModifAssure() {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Assure_cvm assure = null;
			if(act.isSelected()) {
				assure = new Assure_cvm(this.nss.getText(), "ACTIF", this.nom.getText(), this.prenom.getText(), format.parse(this.dateN.getValue().toString()), null, this.adresse.getText(), this.wilaya.getText(), this.daira.getText());
				JavaFxWithSpring1Application.assureRepository.save(assure);
				return true;
			}else if(ret.isSelected()){
				assure = new Assure_cvm(this.nss.getText(), "RETRAITÉ", this.nom.getText(), this.prenom.getText(), format.parse(this.dateN.getValue().toString()), null, this.adresse.getText(), this.wilaya.getText(), this.daira.getText());			
				JavaFxWithSpring1Application.assureRepository.save(assure);
				return true;
			}else if(cont.isSelected()){
				assure = new Assure_cvm(this.nss.getText(), "CONTRACTUEL", this.nom.getText(), this.prenom.getText(), format.parse(this.dateN.getValue().toString()), format.parse(this.dateFinc.getValue().toString()), this.adresse.getText(), this.wilaya.getText(), this.daira.getText());			
				JavaFxWithSpring1Application.assureRepository.save(assure);
				return true;
			}
            
            	
            
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
		return false;

	}
	
	public void initStage() {
		try {
			this.nss.setText(ListeAssureController.assure_cvmModifier.getNss());
			this.nom.setText(ListeAssureController.assure_cvmModifier.getNom());
			this.prenom.setText(ListeAssureController.assure_cvmModifier.getPrenom());
			if(ListeAssureController.assure_cvmModifier.getAdresse() != null) {
				this.adresse.setText(ListeAssureController.assure_cvmModifier.getAdresse());
			}else {
				this.adresse.setText("");
			}
			if(ListeAssureController.assure_cvmModifier.getWilaya() != null) {
				this.wilaya.setText(ListeAssureController.assure_cvmModifier.getWilaya());
			}else
			{
				this.wilaya.setText("");
			}
			if(ListeAssureController.assure_cvmModifier.getDaira() != null) {
				this.daira.setText(ListeAssureController.assure_cvmModifier.getDaira());
			}else {
				this.daira.setText("");
			}
			if(ListeAssureController.assure_cvmModifier.getDateN() != null) {
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String date1 = format.format(ListeAssureController.assure_cvmModifier.getDateN());
				Date d1 = format.parse(date1);
				Instant instant = d1.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate localdate = zdt.toLocalDate();
				this.dateN.setValue(localdate);
			}
			if(ListeAssureController.assure_cvmModifier.getObs().equals("ACTIF")) {
				this.dateFinc.setDisable(true);
				act.setSelected(true);
				
			}else if(ListeAssureController.assure_cvmModifier.getObs().equals("RETRAITÉ")){
				this.dateFinc.setDisable(true);
				ret.setSelected(true);
			}else if(ListeAssureController.assure_cvmModifier.getObs().equals("CONTRACTUEL")){
				this.dateFinc.setDisable(false);
				cont.setSelected(true);
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String date1 = format.format(ListeAssureController.assure_cvmModifier.getDate_fin_pc());
				Date d1 = format.parse(date1);
				Instant instant = d1.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate localdate = zdt.toLocalDate();
				this.dateFinc.setValue(localdate);
			}else
			{   cont.setSelected(false);
				act.setSelected(false);
				ret.setSelected(false);
				this.dateFinc.setDisable(true);
			}
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
	}
	

}
