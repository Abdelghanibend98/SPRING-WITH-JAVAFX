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

public class ModifFromAydController implements Initializable{
	
	@FXML
    private TextField nom;
	
	@FXML
    private AnchorPane principalPane;

    @FXML
    private TextField prenom;

    @FXML
    private DatePicker dateN;

    @FXML
    private TextField nss;

    @FXML
    private DatePicker fin_pc;

    @FXML
    private ComboBox<String> lp;

    @FXML
    private Button validerBtn;
    
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
						ModifAyd();
						Stage stage = (Stage) validerBtn.getScene().getWindow();
						stage.close();
						alerte.setTitle("Opération réussi!");
						alerte.setHeaderText("l'opération a été bien effectuée !");
						alerte.setAlertType(AlertType.INFORMATION);
						alerte.show();
						}

					
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
			initStage();
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
		
		principalPane.setOnKeyPressed(ev->{
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
							ModifAyd();
							Stage stage = (Stage) validerBtn.getScene().getWindow();
							stage.close();
							alerte.setTitle("Opération réussi!");
							alerte.setHeaderText("l'opération a été bien effectuée !");
							alerte.setAlertType(AlertType.INFORMATION);
							alerte.show();
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
			) {
			return false;
		}
		return true;
	}
	
	private void ModifAyd() {
		
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Ayd_cvm ayd = ListeAssureController.ayd_cvmModifier;
			ayd.setNom(this.nom.getText());
			ayd.setPrenom(this.prenom.getText());
            ayd.setDateN(format.parse(this.dateN.getValue().toString()));
            ayd.setFin_pc(format.parse(this.fin_pc.getValue().toString()));
            JavaFxWithSpring1Application.aydReposirtory2.save(ayd);
            
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
		
            	
            
		

	}
	
	public void initStage() {
		try {
			ObservableList<String> list = FXCollections.observableArrayList("Conjoint","Enfant","Ascendant");
			lp.setItems(list);
			this.nss.setText(ListeAssureController.ayd_cvmModifier.getAssure_cvm().getNss());
			this.nom.setText(ListeAssureController.ayd_cvmModifier.getNom());
			this.prenom.setText(ListeAssureController.ayd_cvmModifier.getPrenom());
			if(ListeAssureController.ayd_cvmModifier.getDateN() != null) {
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String date1 = format.format(ListeAssureController.ayd_cvmModifier.getDateN());
				Date d1 = format.parse(date1);
				Instant instant = d1.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate localdate = zdt.toLocalDate();
				this.dateN.setValue(localdate);
			}
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String date1 = format.format(ListeAssureController.ayd_cvmModifier.getFin_pc());
			Date d1 = format.parse(date1);
			Instant instant = d1.toInstant();
			ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
			LocalDate localdate = zdt.toLocalDate();
			this.fin_pc.setValue(localdate);
			if (ListeAssureController.ayd_cvmModifier.getLp().equals("C")) {
				lp.setValue("Conjoint");
				
			}else if(ListeAssureController.ayd_cvmModifier.getLp().equals("E")){
				lp.setValue("Enfant");
			}else {
				lp.setValue("Ascendant");
			}
			
			
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+"ici");
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
	}
	

}
