package com.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.application.dao.AssureRepository;
import com.application.dao.AydReposirtory;
import com.application.entities.Assure_cvm;
import com.application.entities.Ayd_cvm;
import com.application.services.AssurCvmServices;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@SpringBootApplication
public class JavaFxWithSpring1Application extends Application implements CommandLineRunner{
	
	private ConfigurableApplicationContext applicationContext;
	
	public static AssureRepository assureRepository;
	
	@Autowired
	private AssureRepository assureRepository2;
	
	public static AydReposirtory aydReposirtory2;
	
	
	@Autowired
	private AydReposirtory aydReposirtory;
    	
	@Override
	public void start(Stage primaryStage) {
		try {
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
			//primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void init() {
		
		applicationContext = SpringApplication.run(JavaFxWithSpring1Application.class);
		assureRepository = applicationContext.getBean(AssureRepository.class);
		aydReposirtory2 = applicationContext.getBean(AydReposirtory.class);
    }
	
	@Override
	public void stop() {
	    this.applicationContext.close();
	    Platform.exit();
	}
	
	public static void main(String[] args) {
        launch(JavaFxWithSpring1Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(assureRepository2.count());
		System.out.println(aydReposirtory.count());
	
//		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//		Date date1 = new Date();
//		String date = format.format(date1);
//		System.out.println(date);
//		
//		DatePicker d = new DatePicker();
		
//        ArrayList<Ayd_cvm> liste = aydReposirtory.getByAssure("233003");
//        for (Ayd_cvm ayd_cvm : liste) {
//			System.out.println(ayd_cvm.getNum_pc());
//		}
		
		
//		try{
//		InputStream flux=new FileInputStream("assure-cvm.txt"); 
//		InputStreamReader lecture=new InputStreamReader(flux);
//		BufferedReader buff=new BufferedReader(lecture);
//		String ligne;
//		List<String> line = new ArrayList<String>();
//		int i =0;
//		while ((ligne=buff.readLine())!=null){
//			line = null;
//			String [] str = ligne.split("\\s");
//			line = Arrays.asList(str);
//	        if (line.size() <= 4) {
//			    if(line.get(1).equals("RETRAIT?")) {
//					assureRepository2.save(new Assure_cvm(line.get(0), "RETRAITÉ", line.get(2), line.get(3),null,null, null,null,null));	    	
//			    }else if(line.get(1).equals("")) {
//					assureRepository2.save(new Assure_cvm(line.get(0), "", line.get(2), line.get(3),null,null, null,null,null));			    	
//			    }
//			    else {
//					assureRepository2.save(new Assure_cvm(line.get(0), "ACTIF", line.get(2), line.get(3),null,null, null,null,null));
//			    }
//
//			}else {
//			    if(line.get(1).equals("RETRAIT?")) {
//					assureRepository2.save(new Assure_cvm(line.get(0), "RETRAITÉ", line.get(2)+" "+line.get(3),line.get(4),null,null, null,null,null));	    	
//			    }else if(line.get(1).equals("")){
//					assureRepository2.save(new Assure_cvm(line.get(0), "", line.get(2)+" "+line.get(3),line.get(4),null,null, null,null,null));
//			    }else{
//					assureRepository2.save(new Assure_cvm(line.get(0), "ACTIF", line.get(2)+" "+line.get(3),line.get(4),null,null, null,null,null));
//
//			    }
//
//			}
//				
//		}
//		
//		buff.close(); 
//		}		
//		catch (Exception e){
//		System.out.println(e.toString());
//		}
//
//		try{
//		InputStream flux=new FileInputStream("ayd-cvm.txt"); 
//		InputStreamReader lecture=new InputStreamReader(flux);
//		BufferedReader buff=new BufferedReader(lecture);
//		String ligne;
//		List<String> line = new ArrayList<String>();
//		
//		while ((ligne=buff.readLine())!=null){
//			line = null;
//			String [] str = ligne.split("\\s");
//			line = Arrays.asList(str);
//			if(line.size() == 7)
//			{
//	            Assure_cvm assure_cvm = assureRepository2.getById(line.get(0));
//	            aydReposirtory.save(new Ayd_cvm(assure_cvm, line.get(2), new SimpleDateFormat("dd/MM/yyyy").parse(line.get(3)), line.get(4),null, line.get(5), line.get(6)));
//
//			}else if(line.size() == 8) {
//	            Assure_cvm assure_cvm = assureRepository2.getById(line.get(0));
//	            aydReposirtory.save(new Ayd_cvm(assure_cvm, line.get(2), new SimpleDateFormat("dd/MM/yyyy").parse(line.get(3)), line.get(4),null, line.get(5)+" "+line.get(6),line.get(7)));
//
//			}else if(line.size()==9)
//			{
//	            Assure_cvm assure_cvm = assureRepository2.getById(line.get(0));
//	            aydReposirtory.save(new Ayd_cvm(assure_cvm, line.get(2), new SimpleDateFormat("dd/MM/yyyy").parse(line.get(3)), line.get(4),null, line.get(5)+" "+line.get(6),line.get(7)+" "+line.get(8)));
//
//			}else {
//	            Assure_cvm assure_cvm = assureRepository2.getById(line.get(0));
//	            aydReposirtory.save(new Ayd_cvm(assure_cvm, line.get(2), new SimpleDateFormat("dd/MM/yyyy").parse(line.get(3)), line.get(4),null, line.get(5)+" "+line.get(6),line.get(7)+" "+line.get(8)+line.get(9)));
//				
//			}
//	                
//		}
//		
//		buff.close(); 
//		}		
//		catch (Exception e){
//		System.out.println(e.toString());
//		}

		
	}


}
