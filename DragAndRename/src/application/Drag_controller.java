package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import models.PathExtented;
import utils.DragControl;
import utils.ItemComparator;
import utils.TreeTools;

public class Drag_controller implements Initializable{
	
    @FXML
    private HBox box;
    
    @FXML
    private Button onReload;
    
    private TreeView<PathExtented> tree_general;
    
    private ObservableList<String> names = FXCollections.observableArrayList();
    
    private ListView<String> liste;
    
    private Path courant;
    
    private static String template = "%s_%s_%02d.jpg";
    
    private PathExtented depart;
	
    private TreeTools tool;
	
    private TreeItem<PathExtented> rootItem;
    
    private TreeItem<PathExtented> currentItem;

	public static String getTemplate() {
		return template;
	}
	
	@FXML
	public void onReload(){
		
		populateRoot();
		
	}
	
	public void populateRoot() {
		
		
		
		box.getChildren().clear();
		
		rootItem = tool.createRoot(depart);
		
		tree_general  = new TreeView<PathExtented>(rootItem);
		tree_general.setVisible(true);
        tree_general.setPrefWidth(500);
       
        tree_general.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
 
            	currentItem = ((TreeItem<PathExtented>) newValue);
            	
            	tree_general.getSelectionModel().select(currentItem);
            	populate(newValue);


           };
        });	
        
        box.getChildren().add(tree_general);
	}
	
	public void populate(Object newValue){
        
        
		
		names.clear();
    	
    	liste = new ListView<String>(names);
    	liste.setVisible(true);
    	
    	File f = ((TreeItem<PathExtented>)newValue).getValue().getPath().toFile();
    	
    	File[] files_to_iretate = f.listFiles();
    	
    	ArrayList<File> sortedFiles = new ArrayList<File>(Arrays.asList(files_to_iretate));
    	
    	Collections.sort(sortedFiles, ItemComparator.getListComparator());

        for (File p : sortedFiles) {
        	
        	if(p.isFile()){

        		String item = p.toPath().getFileName().toString();  
        		names.add(item);
        	}

        }
        if (box.getChildren().size() > 1) {
        	box.getChildren().remove(1);
        }
        DragControl.drag(liste, tree_general.getSelectionModel().getSelectedItem(), this);

        box.getChildren().add(liste);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		depart = new PathExtented(Paths.get("/home/kaplone/Desktop/Test_Rename"), "Test_Rename");
		
		tool = new TreeTools();
		
		rootItem = tool.createRoot(depart);

		tree_general  = new TreeView<PathExtented>(rootItem);
		      
        tree_general.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
            	
            	currentItem = ((TreeItem<PathExtented>) newValue);

            	tree_general.getSelectionModel().select(currentItem);
            	populate(newValue);


           };
        });	
		
		tree_general.setVisible(true);
        tree_general.setPrefWidth(500);
        
        box.getChildren().add(tree_general);
        
       // populateRoot(); 
	}

}
