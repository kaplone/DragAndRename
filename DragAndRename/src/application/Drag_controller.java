package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import models.PathExtented;
import utils.TreeTools;

public class Drag_controller implements Initializable{
	
    @FXML
    private HBox box;
    
    private TreeView<PathExtented> tree_general;
    
    private ObservableList<String> names = FXCollections.observableArrayList();
    
    ListView<String> liste;
    
    Path courant;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		PathExtented depart = new PathExtented(Paths.get("/home/autor/Desktop/Test_Rename"), "Test_Rename");
		
		TreeTools tool = new TreeTools();
		
		TreeItem<PathExtented> rootItem = tool.createRoot(depart);
		
		tree_general  = new TreeView<PathExtented>(rootItem);
		tree_general.setVisible(true);
        tree_general.setPrefWidth(500);
        
        tree_general.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
            	
            	names.clear();
            	
            	liste = new ListView<String>(names);
            	liste.setVisible(true);

                for (File p : ((TreeItem<PathExtented>)newValue).getValue().getPath().toFile().listFiles()) {
                	
                	if(p.isFile()){

                		String item = p.toPath().getFileName().toString();  
                		names.add(item);
                	}

                }
                if (box.getChildren().size() > 1) {
                	box.getChildren().remove(1);
                }
                box.getChildren().add(liste);


           };
        });
        
		
		box.getChildren().add(tree_general);
		
		
		
		
		
		
	}

}
