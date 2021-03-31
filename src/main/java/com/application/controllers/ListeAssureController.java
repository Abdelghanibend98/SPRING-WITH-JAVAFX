package com.application.controllers;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListeAssureController implements Initializable{
	
	private ObservableList<Assure_cvm> liste;
	private ObservableList<Ayd_cvm> listayd;

	
	@FXML
    private TableView<Assure_cvm> assureTable;
    
    @FXML
    private AnchorPane principalePane;

    @FXML
    private TableColumn<Assure_cvm, String> nss;

    @FXML
    private TableColumn<Assure_cvm, String> nom;

    @FXML
    private TableColumn<Assure_cvm, String> prenom;

    @FXML
    private TableColumn<Assure_cvm, Date> dateN;
    

    @FXML
    private TableColumn<Assure_cvm, Date> dateFinc;

    @FXML
    private TableColumn<Assure_cvm, String> obs;

    @FXML
    private TableColumn<Assure_cvm, String> adresse;

    @FXML
    private TableColumn<Assure_cvm, String> wilaya;

    @FXML
    private TableColumn<Assure_cvm, String> daira;

    @FXML
    private TextField nssChamp;

    @FXML
    private Button rechercheBtn;
    
    @FXML
    private Button supprimerAssBtn;
    
    @FXML
    private Pane listeAYDPane;

    @FXML
    private TableView<Ayd_cvm> tableAYD;
    
    @FXML
    private TableColumn<Ayd_cvm, String> nomAyd;
    
    @FXML
    private Button modifierAYDBtn;

    @FXML
    private Button suppAYDBtn;
    
    @FXML
    private Button modifierAssureBtn;
    
    
    
    @FXML
    private TableColumn<Ayd_cvm, String> prenomAyd;
    @FXML
    private TableColumn<Ayd_cvm, Date> dateNAYD;

    @FXML
    private TableColumn<Ayd_cvm, Date> dateFINPC;

    @FXML
    private TableColumn<Ayd_cvm, String> relation;
    
    public static Assure_cvm assure_cvmModifier;
    
    public static Ayd_cvm ayd_cvmModifier;
    
    @FXML
    private MenuBar menuBar;
    
    private Alert a = new Alert(AlertType.NONE); 
    
    @FXML
    void exitApp(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    void openFSM(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/com/application/windows/Principale.fxml"));
			//Scene scene = new Scene(root);
			Scene scene = new Scene(root,550,650);
			//Scene scene = new Scene(root,559.37008,650);
			//Scene scene = new Scene(root,950,475);
			scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/file.png");
			primaryStage.getIcons().add(new Image(file.toURI().toString()));
			primaryStage.setTitle("Feuille de soins médicaux");
			primaryStage.centerOnScreen();
			primaryStage.show();
			Stage stage = (Stage) menuBar.getScene().getWindow();
			stage.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }
    @FXML
    void ActivAjoutAYD(ActionEvent event) {

		try {
			assure_cvmModifier = assureTable.getSelectionModel().getSelectedItem();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/com/application/windows/AjoutFormAyd.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/file.png");
			primaryStage.getIcons().add(new Image(file.toURI().toString()));
			primaryStage.setTitle("Ajouter un nouveau AYD");
			primaryStage.centerOnScreen();
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch (IOException e) {
			a.setTitle("error");
			a.setHeaderText("Oops! "+e.getMessage());
			a.setAlertType(AlertType.ERROR); 
			a.show();
		}

    }
    
    @FXML
    void AjoutActive(ActionEvent event) {
    	
		try {
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/com/application/windows/Ajoutform.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/file.png");
			primaryStage.getIcons().add(new Image(file.toURI().toString()));
			primaryStage.setTitle("Ajouter un nouveau assuré");
			primaryStage.centerOnScreen();
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch (IOException e) {
			a.setTitle("error");
			a.setHeaderText("Oops! "+e.getMessage());
			a.setAlertType(AlertType.ERROR); 
			a.show();
		}


    }
    
    @FXML
    void modifierAssure(ActionEvent event) {
           try {
			    assure_cvmModifier = assureTable.getSelectionModel().getSelectedItem();
			    Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/com/application/windows/ModifForm.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				String canonicalPath = new File(".").getCanonicalPath();
				File file = new File(canonicalPath+"/file.png");
				primaryStage.getIcons().add(new Image(file.toURI().toString()));
				primaryStage.setTitle("Modification");
				primaryStage.centerOnScreen();
				primaryStage.initModality(Modality.APPLICATION_MODAL);
				primaryStage.show();
		} catch (Exception e) {
			a.setTitle("error");
			a.setHeaderText("Oops! "+e.getMessage());
			a.setAlertType(AlertType.ERROR); 
			a.show();
		}

    }
    
    @FXML
    void modifierAyd(ActionEvent event) {
    	
        try {
			    ayd_cvmModifier = tableAYD.getSelectionModel().getSelectedItem();
			    Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/com/application/windows/ModifFormAyd.fxml"));		
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/com/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				String canonicalPath = new File(".").getCanonicalPath();
				File file = new File(canonicalPath+"/file.png");
				primaryStage.getIcons().add(new Image(file.toURI().toString()));
				primaryStage.setTitle("Modification");
				primaryStage.centerOnScreen();
				primaryStage.initModality(Modality.APPLICATION_MODAL);
				primaryStage.show();
		} catch (Exception e) {
			a.setTitle("error");
			a.setHeaderText("Oops! "+"ici");
			a.setAlertType(AlertType.ERROR); 
			a.show();
		}


    }

    @FXML
    void supprimerAssur(ActionEvent event) {
         try {
        	 a.setTitle("Confirmation");
		     a.setHeaderText("êtes-vous sur de supprimer cet assuré ?");
		     a.setAlertType(AlertType.CONFIRMATION); 
             Optional<ButtonType> option = a.showAndWait();
             if (option.get() == ButtonType.OK) {
    			 Assure_cvm assure_cvm = assureTable.getSelectionModel().getSelectedItem();
    			 ArrayList<Ayd_cvm> ayd_cvms = JavaFxWithSpring1Application.aydReposirtory2.getByAssure(assure_cvm.getNss());
    			 for (Ayd_cvm ayd_cvm : ayd_cvms) {
    				 JavaFxWithSpring1Application.aydReposirtory2.delete(ayd_cvm);
    			}
    			 JavaFxWithSpring1Application.assureRepository.delete(assure_cvm);
    			 refresh();
    			 a.setTitle("Opération réussi!");
    			 a.setHeaderText("l'opération a été bien effectuée !");
    		     a.setAlertType(AlertType.INFORMATION);
    			 a.show();

             }
		} catch (Exception e) {
			a.setTitle("Erreur !");
			a.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
			a.setAlertType(AlertType.ERROR);
			a.show();
			
		}
    }

    @FXML
    void supprimerAyd(ActionEvent event) {
        try {
       	 a.setTitle("Confirmation");
		     a.setHeaderText("êtes-vous sur d'effectuer cet opération ?");
		     a.setAlertType(AlertType.CONFIRMATION); 
            Optional<ButtonType> option = a.showAndWait();
            if (option.get() == ButtonType.OK) {
            JavaFxWithSpring1Application.aydReposirtory2.delete(tableAYD.getSelectionModel().getSelectedItem());
   			 refresh();
   			 a.setTitle("Opération réussi!");
   			 a.setHeaderText("l'opération a été bien effectuée !");
   		     a.setAlertType(AlertType.INFORMATION);
   			 a.show();

            }
		} catch (Exception e) {
			a.setTitle("Erreur !");
			a.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
			a.setAlertType(AlertType.ERROR);
			a.show();
			
		}


    }
    public void refresh() {
    	try {
			liste = FXCollections.observableArrayList(JavaFxWithSpring1Application.assureRepository.getAll());
			assureTable.setItems(liste);
			tableAYD.setItems(null);
			listeAYDPane.setDisable(true);
			suppAYDBtn.setDisable(true);
			modifierAYDBtn.setDisable(true);
			this.modifierAssureBtn.setDisable(true);
			this.supprimerAssBtn.setDisable(true);
		} catch (Exception e) {
			a.setTitle("Erreur !");
			a.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
			a.setAlertType(AlertType.ERROR);
			a.show();
		}
    }
    
    @FXML
    void reload(ActionEvent event) {
    	try {
			liste = FXCollections.observableArrayList(JavaFxWithSpring1Application.assureRepository.getAll());
			assureTable.setItems(liste);
			tableAYD.setItems(null);
			listeAYDPane.setDisable(true);
			suppAYDBtn.setDisable(true);
			modifierAYDBtn.setDisable(true);
			this.modifierAssureBtn.setDisable(true);
			this.supprimerAssBtn.setDisable(true);
		} catch (Exception e) {
			a.setTitle("Erreur !");
			a.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
			a.setAlertType(AlertType.ERROR);
			a.show();
		}
    }
    
    @FXML
    void searchInTable(ActionEvent event) {
        try {
			if(! nssChamp.getText().isEmpty()) {
				if(JavaFxWithSpring1Application.assureRepository.getAllById(nssChamp.getText()).size() > 0) {
					liste = FXCollections.observableArrayList(JavaFxWithSpring1Application.assureRepository.getAllById(nssChamp.getText()));
					assureTable.setItems(liste);

				}else
				{
					a.setTitle("Résultat introuvable");
					a.setHeaderText("Oops! Résultat introuvable pour ce numéro d'immatriculation");
					a.setAlertType(AlertType.WARNING);
					a.show();
				}
			}else {
				
				a.setTitle("error");
				a.setHeaderText("Une erreur s'est produit veuillez résseyer plus tard!");
				a.setAlertType(AlertType.CONFIRMATION); 
                
                Optional<ButtonType> option = a.showAndWait();
                if (option.get() == ButtonType.OK) {
					System.out.println("ok");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			initTableAssure();
			
			nomAyd.setCellValueFactory(new PropertyValueFactory<Ayd_cvm, String>("nom"));
			prenomAyd.setCellValueFactory(new PropertyValueFactory<Ayd_cvm, String>("prenom"));
			dateNAYD.setCellValueFactory(new PropertyValueFactory<Ayd_cvm, Date>("dateN"));
			dateFINPC.setCellValueFactory(new PropertyValueFactory<Ayd_cvm, Date>("fin_pc"));
			relation.setCellValueFactory(new PropertyValueFactory<Ayd_cvm, String>("lp"));
			assureTable.setOnMouseClicked(e->{
				if(! assureTable.getSelectionModel().isEmpty()) {
				  ArrayList<Ayd_cvm> a = JavaFxWithSpring1Application.aydReposirtory2.getByAssure(assureTable.getSelectionModel().getSelectedItem().getNss());			
			      listeAYDPane.setDisable(false);
				  listayd = FXCollections.observableArrayList(a);
				  tableAYD.setItems(listayd);
				  this.modifierAssureBtn.setDisable(false);
				  this.supprimerAssBtn.setDisable(false);
				  this.modifierAYDBtn.setDisable(true);
				  this.suppAYDBtn.setDisable(true);
				}
			});
			
			tableAYD.setOnMouseClicked(e->{
				if(! tableAYD.getSelectionModel().isEmpty()) {
				  this.modifierAYDBtn.setDisable(false);
				  this.suppAYDBtn.setDisable(false);
				}
			});
		} catch (Exception e) {
			a.setTitle("Erreur !");
			a.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
			a.setAlertType(AlertType.ERROR);
			a.show();
		}	
		
		principalePane.setOnKeyPressed(ev->{
			if(ev.getCode()==KeyCode.ENTER) {
		        try {
					if(! nssChamp.getText().isEmpty()) {
						if(JavaFxWithSpring1Application.assureRepository.getAllById(nssChamp.getText()).size() > 0) {
							liste = FXCollections.observableArrayList(JavaFxWithSpring1Application.assureRepository.getAllById(nssChamp.getText()));
							assureTable.setItems(liste);

						}else
						{
							a.setTitle("Résultat introuvable");
							a.setHeaderText("Oops! Résultat introuvable pour ce numéro d'immatriculation");
							a.setAlertType(AlertType.WARNING);
							a.show();
						}
					}else {
						
						a.setTitle("error");
						a.setHeaderText("Une erreur s'est produit veuillez résseyer plus tard!");
						a.setAlertType(AlertType.CONFIRMATION); 
		                
		                Optional<ButtonType> option = a.showAndWait();
		                if (option.get() == ButtonType.OK) {
							System.out.println("ok");
						}
					}
				} catch (Exception e) {
					a.setTitle("Erreur !");
					a.setHeaderText("Oops! l'opération effectuée a genéré une erreur");
					a.setAlertType(AlertType.ERROR);
					a.show();
				}

			}
		});
	}
	
	private void initTableAssure() {
		
		nss.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("nss"));
		nom.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("prenom"));
		obs.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("obs"));
		dateN.setCellValueFactory(new PropertyValueFactory<Assure_cvm, Date>("dateN"));
		adresse.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("adresse"));
		wilaya.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("wilaya"));
		daira.setCellValueFactory(new PropertyValueFactory<Assure_cvm, String>("daira"));
		dateFinc.setCellValueFactory(new PropertyValueFactory<Assure_cvm, Date>("date_fin_pc"));
		liste = FXCollections.observableArrayList(JavaFxWithSpring1Application.assureRepository.getAll());
		assureTable.setItems(liste);
		
	}

	public TableView<Assure_cvm> getAssureTable() {
		return assureTable;
	}

	public void setAssureTable(ArrayList<Assure_cvm> arrayList) {
		this.assureTable.setItems(FXCollections.observableArrayList(arrayList));
	}
	
	

}
