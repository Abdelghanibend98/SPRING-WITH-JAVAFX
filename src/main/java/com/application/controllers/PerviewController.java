package com.application.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.filechooser.FileSystemView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;



public class PerviewController implements Initializable{

	
	@FXML
    private ScrollPane scrollPane;
	
	@FXML
    private ImageView imageView;
	
	@FXML
    private Button plus;

    @FXML
    private Button moins;
    
    @FXML
    private MenuItem btnPrint;
    
    private Image image;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FileSystemView filesys = FileSystemView.getFileSystemView();
		try {
			String canonicalPath = new File(".").getCanonicalPath();
			File file = new File(canonicalPath+"/imageApercu.png");
			image = new Image(file.toURI().toString());
			imageView.setImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}


	@FXML
    void Print(ActionEvent event) {
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
	
	
	
	
	
	
//	private void printImage(BufferedImage image) {
//        //final PrinterJob printJob = Objects.requireNonNull(PrinterJob.getPrinterJob(), "Cannot create printer job");
//        PrinterJob printJob = PrinterJob.getPrinterJob();        
//        printJob.setPrintable(new Printable() {
//		@Override
//		public int print(Graphics arg0, PageFormat arg1, int arg2) throws PrinterException {
//			// Get the upper left corner that it printable
//	        int x = (int) Math.ceil(pageFormat.getImageableX());
//	        int y = (int) Math.ceil(pageFormat.getImageableY());
//	        if (pageIndex != 0) {
//	          return NO_SUCH_PAGE;
//	        }
//	        graphics.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
//	        return PAGE_EXISTS;
//		}
//	    },getPageFormat(printJob));
//	    try {
//	      printJob.print();
//	    } catch (PrinterException e1) {
//	      e1.printStackTrace();
//	    }
//	  }

}