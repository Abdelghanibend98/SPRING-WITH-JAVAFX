package com.application.controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.application.JavaFxWithSpring1Application;
import com.application.dao.AssureRepository;
import com.application.entities.Assure_cvm;
import com.application.entities.Ayd_cvm;
import com.jfoenix.controls.JFXDatePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;


@Controller
public class PrincipaleController implements Initializable{

	
	@FXML
    private Button rechrechBtn;
	
	@FXML
    private Button validerBtn;
	
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;
    
    @FXML
    private MenuBar menuBar;

    @FXML
    private TextField wilaya;

    @FXML
    private TextField daira;

    @FXML
    private JFXDatePicker dateN;

    @FXML
    private TextField fin_pc1;
    
    @FXML
    private TextField fin_pc2;
    
    @FXML
    private TextField fin_pc3;
    
    @FXML
    private CheckBox chekSalaire;
    
    @FXML
    private CheckBox checkNonSaliare;

    @FXML
    private CheckBox checkretrait;
    
    @FXML
    private TextField nss;
    
    @FXML
    private Button afficher;
    
    @FXML
    private TextField nomAYD;

    @FXML
    private DatePicker dateNAYD;
    
    @FXML
    private ComboBox<Ayd_cvm> listeCombo;
    
    @FXML
    private RadioButton autre;

    @FXML
    private ToggleGroup mygroupe;

    @FXML
    private RadioButton assureR;
    

    @FXML
    private DatePicker dateFinPC;
    @FXML
    private MenuItem listeAssurBtn;

    @FXML
    private RadioButton act;

    @FXML
    private ToggleGroup mySituation;

    @FXML
    private RadioButton ret;
    
    @FXML
    private Button printBtn;

    @FXML
    private RadioButton cont;
    
    private Assure_cvm assure;
    
    private ObservableList<Ayd_cvm> list ;
    
    @FXML
    private AnchorPane PrincipalPane;
    
    @FXML
    private DatePicker dateFINAYD;
    
    private Alert alerte;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	alerte = new Alert(AlertType.NONE);
    	PrincipalPane.setOnKeyPressed(e->{
        	if (e.getCode() == KeyCode.ENTER ){
  	            	recherche();
  	            
  	        }else if (e.getCode() == KeyCode.A ){
  	        	System.out.println("test");
  	        }

    	});
    	
    	
		
	}
    
    @FXML
    void envoyer(ActionEvent event) {
    	
    	recherche();
    }
    
    private void changeAssure()
    { 
    	 try {
			 if(assureR.isSelected()) {
				 if(this.adresse.isEditable()) {
					 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					 assure.setAdresse(this.adresse.getText());
					 assure.setWilaya(this.wilaya.getText());
					 assure.setDaira(this.daira.getText());
					 assure.setDateN(format.parse(this.dateN.getValue().toString()));
					 this.dateNAYD.setEditable(false);
					 if(act.isSelected()) {
						 assure.setObs("ACTIF");
					 }else if(ret.isSelected()){
						 assure.setObs("RETRAITÉ"); 
					 }else if(cont.isSelected()){
						 assure.setObs("CONTRACTUEL");
						 assure.setDate_fin_pc(format.parse(this.dateFinPC.getValue().toString()));
					 }
					 JavaFxWithSpring1Application.assureRepository.save(assure);

				 }
			 }else if(autre.isSelected()){
				 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 if(this.adresse.isEditable()) {
					 assure.setAdresse(this.adresse.getText());
					 assure.setWilaya(this.wilaya.getText());
					 assure.setDaira(this.daira.getText());
					 assure.setDateN(format.parse(this.dateN.getValue().toString()));
					 if(act.isSelected()) {
						 assure.setObs("ACTIF");
					 }else if (cont.isSelected()) {
						assure.setObs("CONTRACTUEL");
						assure.setDate_fin_pc(format.parse(this.dateFinPC.getValue().toString()));
					}else {
						 assure.setObs("RETRAITÉ"); 
					 }
					 JavaFxWithSpring1Application.assureRepository.save(assure);
 
				 }else if(! this.act.isDisable() && ! this.ret.isDisable()){
					 if(act.isSelected()) {
						 assure.setObs("ACTIF");
					 }else {
						 assure.setObs("RETRAITÉ"); 
					 }
					 JavaFxWithSpring1Application.assureRepository.save(assure);
				 }
				 Ayd_cvm ayd = this.listeCombo.getValue();
				 ayd.setDateN(format.parse(this.dateNAYD.getValue().toString()));
				 JavaFxWithSpring1Application.aydReposirtory2.save(ayd);
			 }
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
    }
    
    private void disableAll()
    {
    	this.adresse.setEditable(false);
    	this.wilaya.setEditable(false);
    	this.daira.setEditable(false);
    	this.dateN.setEditable(false);
    	this.dateNAYD.setEditable(false);
    	this.act.setDisable(true);
    	this.ret.setDisable(true);
    	this.cont.setDisable(true);
    	this.dateFinPC.setEditable(false);
    }
    
    @FXML
    void vlideForm(ActionEvent event) {
         try {
        	 if(assureR.isSelected()) {
			    	this.dateNAYD.setValue(this.dateN.getValue());
			 }
        	 
				 if (this.adresse.getText().isEmpty() || this.wilaya.getText().isEmpty() || this.daira.getText().isEmpty() || this.dateN.getValue()== null
				     || this.dateNAYD.getValue() == null || this.mySituation.getSelectedToggle() == null || (! this.dateFinPC.isDisable() && this.dateFinPC.getValue()==null)) {
					    alerte.setTitle("Champ vide !");
						alerte.setHeaderText("Veuillez remplire tous les champs vide!");
						alerte.setAlertType(AlertType.WARNING);
						alerte.show();
					
				}else {
					
					 changeAssure();
					 disableAll();
					 text();
				}
			
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
    }
    
    @FXML
    void comboChanged(ActionEvent event) {
         try {
        	 this.afficher.setDisable(true);
        	 this.printBtn.setDisable(true);
			if(listeCombo.getValue() != null) {   
				this.nomAYD.setText(listeCombo.getValue().getNom()+" "+listeCombo.getValue().getPrenom());
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String date1 = format.format(listeCombo.getValue().getFin_pc());
				Date d1 = format.parse(date1);
				Instant instant = d1.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate localdate = zdt.toLocalDate();
			    this.dateFINAYD.setValue(localdate);
				validerBtn.setDisable(false);
					if(listeCombo.getValue().getDateN() != null) {
						String date = format.format(listeCombo.getValue().getDateN());
						Date d = format.parse(date);
						instant = d.toInstant();
						zdt = instant.atZone(ZoneId.systemDefault());
						localdate = zdt.toLocalDate();
					    this.dateNAYD.setValue(localdate);
					}else {
						this.dateNAYD.setValue(null);
						this.dateNAYD.setEditable(true);
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
    void selectedChoice(ActionEvent event) {

    	try {
    		this.afficher.setDisable(true);
    		this.printBtn.setDisable(true);
			if(autre.isSelected()) {
				this.dateNAYD.setDisable(false);
				this.dateFINAYD.setDisable(false);
				listeCombo.setDisable(false);
				if(listeCombo.getValue() != null) {
				this.nomAYD.setText(listeCombo.getValue().getNom()+" "+listeCombo.getValue().getPrenom());
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String date2 = format.format(listeCombo.getValue().getFin_pc());
				Date d2 = format.parse(date2);
				Instant instant = d2.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate localdate = zdt.toLocalDate();
			    this.dateFINAYD.setValue(localdate);
				if(this.listeCombo.getValue().getDateN() != null){
					format = new SimpleDateFormat("dd-MM-yyyy");
					date2 = format.format(listeCombo.getValue().getDateN());
				    d2 = format.parse(date2);
					instant = d2.toInstant();
					zdt = instant.atZone(ZoneId.systemDefault());
					localdate = zdt.toLocalDate();
				    this.dateNAYD.setValue(localdate);
				}else{
					this.dateNAYD.setEditable(true);
					this.dateNAYD.setValue(null);
					
				}
				}else {
					this.dateNAYD.setValue(null);
					this.dateFINAYD.setValue(null);
					this.dateNAYD.setEditable(false);
					validerBtn.setDisable(true);
					this.nomAYD.setText("");
				}
				
				
			}else if(assureR.isSelected()) {
	            this.dateFINAYD.setDisable(true);
	            this.dateNAYD.setDisable(true);
				if(assure.getDate_fin_pc() != null)
				{
					DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String date = format.format(assure.getDate_fin_pc());
					Date d = format.parse(date);
					Instant instant = d.toInstant();
					ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
					LocalDate localdate = zdt.toLocalDate();
				    this.dateFinPC.setValue(localdate);
				    this.dateFINAYD.setValue(localdate);
				}else {
					this.dateFINAYD.setValue(null);
				}
				validerBtn.setDisable(false);
				if(assure.getDateN() != null) {
					DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String date = format.format(assure.getDateN());
					Date d = format.parse(date);
					Instant instant = d.toInstant();
					ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
					LocalDate localdate = zdt.toLocalDate();
				    this.dateNAYD.setValue(localdate);
				    this.dateNAYD.setEditable(false);
				}
					
				this.nomAYD.setText(assure.getNom()+" "+assure.getPrenom());
				listeCombo.setDisable(true);
			}
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
    }
    @FXML
    void selectSituation(ActionEvent event) {
 
    	if(this.act.isSelected()) {
    		
    		this.dateFinPC.setDisable(true);
    	}else if (ret.isSelected()) {
    		this.dateFinPC.setDisable(true);
		}else if (cont.isSelected()) {
			this.dateFinPC.setValue(null);
			this.dateFinPC.setDisable(false);
			this.dateFinPC.setEditable(true);
			
		}
    }
    @FXML
    void OpenListeAssure(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/application/windows/ListeAssure.fxml"));
			Scene scene = new Scene(root,950,475);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/file.png");
			primaryStage.getIcons().add(new Image(file.toURI().toString()));
			primaryStage.setTitle("Liste des assurés");
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.WINDOW_MODAL);
			primaryStage.show();
            Stage stage = (Stage) menuBar.getScene().getWindow();
			stage.close();
		} catch(Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+"ici");
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}

    }
    @FXML
    void afficherAvantImp(ActionEvent event) {
    	ApercuAvantImp();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/application/Main.fxml"));
			Scene scene = new Scene(root,559.37008,650);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/file.png");
			primaryStage.getIcons().add(new Image(file.toURI().toString()));
			primaryStage.setTitle("Apercu avant impression");
			primaryStage.centerOnScreen();
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			
		} catch(Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}

    }
    
	public void ApercuAvantImp() {
        try {
        	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String canonicalPath = new File(".").getCanonicalPath();
			BufferedImage image = ImageIO.read(new File(canonicalPath+"/image.png"));
				//récupérer l'objet Graphics
				Graphics g = image.getGraphics();
				//définir le font
				g.setFont(g.getFont().deriveFont(15f));
				g.setColor(Color.BLACK);
				//afficher le texte sur les coordonnées(x,y)
				g.drawString(assure.getNss(), 230, 138);
				g.drawString(assure.getNom(), 120, 166);
				g.drawString(assure.getPrenom(), 120, 182);
				g.drawString(format.format(assure.getDateN()), 120, 199);
				g.drawString(assure.getAdresse(), 100, 220);
				g.drawString(assure.getDaira(), 80, 237);
				g.drawString(assure.getWilaya(), 300, 238);
				if(autre.isSelected()) {
					if(this.listeCombo.getValue().getLp().equals("C")) {
						g.drawString("Fin de pris en charge", 300, 289);
						g.drawString(format.format(listeCombo.getValue().getFin_pc()), 300, 308);
						g.drawString("*", 219, 465);
					}else if(this.listeCombo.getValue().getLp().equals("E")){   
						if(assure.getObs().equals("CONTRACTUEL")) {
						g.drawString(assure.getObs(), 300, 289);
						g.drawString(format.format(assure.getDate_fin_pc()), 300, 308);
					    }else {
						g.drawString(assure.getObs(), 300, 289);
					    }
						g.drawString("*", 319, 465);
					}else {
						g.drawString("Fin de pris en charge", 300, 289);
						g.drawString(format.format(listeCombo.getValue().getFin_pc()), 300, 308);
						g.drawString("*", 433, 465);
					}
					g.drawString(format.format(listeCombo.getValue().getDateN()), 120, 500);
				}else if(assureR.isSelected())
					
				{
					if(assure.getObs().equals("CONTRACTUEL")) {
						g.drawString("*", 111, 464);
						g.drawString(assure.getObs(), 300, 289);
						g.drawString(format.format(assure.getDate_fin_pc()), 300, 308);
					}else {
						g.drawString("*", 111, 464);
						g.drawString(assure.getObs(), 300, 289);
					}
					g.drawString(format.format(assure.getDateN()), 120, 500);
				}
				g.drawString("*", 35, 291);
				g.drawString(this.nomAYD.getText(), 120, 480);
				g.dispose();
				
				//écrire l'image
				ImageIO.write(image, "png", new File(canonicalPath+"/imageApercu.png"));
				this.afficher.setDisable(false);
				this.printBtn.setDisable(false);
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
 
	}
	
	
	public void recherche() {
        try {
        	validerBtn.setDisable(true);
        	printBtn.setDisable(true);
        	afficher.setDisable(true);
			if(! this.nss.getText().isEmpty()) {
				assure = JavaFxWithSpring1Application.assureRepository.getById(this.nss.getText());
				if(autre.isSelected())
				{
					if(listeCombo.getValue()!=null) {
						listeCombo.getItems().clear();
					}
					listeCombo.setDisable(true);
					autre.setSelected(false);
				}else {
					this.dateNAYD.setValue(null);
					this.nomAYD.setText("");
					assureR.setSelected(false);
				}
					
				if (assure != null) {
				list = FXCollections.observableArrayList(JavaFxWithSpring1Application.aydReposirtory2.getByAssure(assure.getNss()));
			    
				if(list.size() <= 0){
					this.autre.setVisible(false);
				}else {
					this.autre.setVisible(true);
					listeCombo.setItems(list);
				}
				
				autre.setDisable(false);
				assureR.setDisable(false);
				this.nss.setText(assure.getNss());
				this.nom.setText(assure.getNom());
				this.prenom.setText(assure.getPrenom());
				if(assure.getAdresse() == null) {
					this.adresse.setText("");
					this.adresse.setEditable(true);
				}else {
					this.adresse.setEditable(false);
				   this.adresse.setText(assure.getAdresse());
				}
				if(assure.getWilaya() == null) {
					this.wilaya.setText("");
					this.wilaya.setEditable(true);
				}else {
					this.wilaya.setEditable(false);
				   this.wilaya.setText(assure.getWilaya());
				}
				if(assure.getDaira() == null) {
					this.daira.setText("");
					this.daira.setEditable(true);
				}else {
					this.daira.setEditable(false);
				   this.daira.setText(assure.getDaira());
				}
				
				if(assure.getDateN() == null) {
					this.dateN.setValue(null);
					this.dateN.setEditable(true);
				}else {
					this.dateN.setEditable(false);
					DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String date = format.format(assure.getDateN());
					Date d = format.parse(date);
					Instant instant = d.toInstant();
					ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
					LocalDate localdate = zdt.toLocalDate();
				    this.dateN.setValue(localdate);
				    
				}
				if(assure.getObs().equals("")) {
					act.setDisable(false);
					ret.setDisable(false);
					cont.setDisable(false);
					act.setSelected(false);
					ret.setSelected(false);
					cont.setSelected(false);
				}else if(assure.getObs().equals("ACTIF")){
					act.setSelected(true);
				}else if(assure.getObs().equals("CONTRACTUEL")) {
					cont.setSelected(true);
					this.dateFinPC.setEditable(false);
					DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String date = format.format(assure.getDate_fin_pc());
					Date d = format.parse(date);
					Instant instant = d.toInstant();
					ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
					LocalDate localdate = zdt.toLocalDate();
				    this.dateFinPC.setValue(localdate);
				}else {
					ret.setSelected(true);
				}
				}else {
					alerte.setTitle("Résultat introuvable");
					alerte.setHeaderText("Oops! Résultat introuvable pour ce numéro d'immatriculation");
					alerte.setAlertType(AlertType.WARNING);
					alerte.show();
				}

			}else {
				alerte.setTitle("Champ vide !");
				alerte.setHeaderText("Le champ de texte est vide  !");
				alerte.setAlertType(AlertType.WARNING);
				alerte.show();
			}
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}

		
	}
	
	public void text() {
        try {
        	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String canonicalPath = new File(".").getCanonicalPath();
			BufferedImage image = ImageIO.read(new File(canonicalPath+"/image-vide.png"));
				//récupérer l'objet Graphics
				Graphics g = image.getGraphics();
				//définir le font
				g.setFont(g.getFont().deriveFont(15f));
				g.setColor(Color.BLACK);
				//afficher le texte sur les coordonnées(x,y)
				g.drawString(assure.getNss(), 230, 138);
				g.drawString(assure.getNom(), 120, 166);
				g.drawString(assure.getPrenom(), 120, 182);
				g.drawString(format.format(assure.getDateN()), 120, 199);
				g.drawString(assure.getAdresse(), 100, 220);
				g.drawString(assure.getDaira(), 80, 237);
				g.drawString(assure.getWilaya(), 300, 238);
				if(autre.isSelected()) {
					if(this.listeCombo.getValue().getLp().equals("C")) {
						g.drawString("Fin de pris en charge", 300, 289);
						g.drawString(format.format(listeCombo.getValue().getFin_pc()), 300, 308);
						g.drawString("*", 219, 465);
					}else if(this.listeCombo.getValue().getLp().equals("E")){   
						if(assure.getObs().equals("CONTRACTUEL")) {
						g.drawString(assure.getObs(), 300, 289);
						g.drawString(format.format(assure.getDate_fin_pc()), 300, 308);
					    }else {
						g.drawString(assure.getObs(), 300, 289);
					    }
						g.drawString("*", 319, 465);
					}else {
						g.drawString("Fin de pris en charge", 300, 289);
						g.drawString(format.format(listeCombo.getValue().getFin_pc()), 300, 308);
						g.drawString("*", 433, 465);
					}
					g.drawString(format.format(listeCombo.getValue().getDateN()), 120, 500);
					
				}else if(assureR.isSelected())
					
				{
					if(assure.getObs().equals("CONTRACTUEL")) {
						g.drawString("*", 111, 464);
						g.drawString(assure.getObs(), 300, 289);
						g.drawString(format.format(assure.getDate_fin_pc()), 300, 308);
					}else {
						g.drawString("*", 111, 464);
						g.drawString(assure.getObs(), 300, 289);
					}
					g.drawString(format.format(assure.getDateN()), 120, 500);
				}
				g.drawString("*", 35, 291);
				g.drawString(this.nomAYD.getText(), 120, 480);
				g.dispose();
				
				//écrire l'image
				ImageIO.write(image, "png", new File(canonicalPath+"/printable.png"));
				this.afficher.setDisable(false);
				this.printBtn.setDisable(false);
		} catch (Exception e) {
			alerte.setTitle("Erreur !");
			alerte.setHeaderText("Oops! "+e.getMessage());
			alerte.setAlertType(AlertType.ERROR);
			alerte.show();
		}
       

	}
	
	public void print(Node node) {
        
        ChoiceDialog<Printer> dialog = new ChoiceDialog<Printer>(Printer.getDefaultPrinter(), Printer.getAllPrinters());
        dialog.setHeaderText("Choisire une imprimante !");
        dialog.setContentText("Choisire une imprimante parmis l'existantes");
        dialog.setTitle("Choix de l'imprimante");
        Optional<Printer> opt = dialog.showAndWait();
                if (opt.isPresent()) {
                    Printer printer = opt.get();
                    PageLayout pageLayout = printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, 0, 0, 0, 0);               
                    double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
                    double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
                    node.getTransforms().add(new Scale(scaleX, scaleY));
                    PrinterJob job = PrinterJob.createPrinterJob();
                    job.setPrinter(printer);
                    if (job != null && job.showPageSetupDialog(null)) {
                        boolean success = job.printPage(pageLayout,node);
                        if (success) {
                            job.endJob();
                        }
                    }

                }
    }
	
	@FXML
    void printF(ActionEvent event) {
		try {
			ImageView imageView = new ImageView();
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/printable.png");
			Image image = new Image(file.toURI().toString());
			imageView.setFitWidth(image.getWidth());
			imageView.setFitHeight(image.getHeight());
			imageView.setImage(image);
			print(imageView);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    void exiteApp(ActionEvent event) {
       System.exit(0);
    }


}
