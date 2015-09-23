package utils;

import java.io.File;
import java.nio.file.Path;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.PathExtented;

public class TreeTools {
	
	public TreeItem<PathExtented> createRoot(PathExtented depart){
		
		TreeItem<PathExtented> rootItem = new TreeItem<PathExtented> (depart, new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));
        rootItem.setExpanded(true);
        for (File p : depart.toFile().listFiles()) {
        	
        	if(p.isDirectory()){
        		TreeItem<PathExtented> item = new TreeItem<PathExtented> (new PathExtented(p.toPath(), p.toPath().getFileName().toString()), new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));            
        		
        		createBranch(p, item);
        		
        		rootItem.getChildren().add(item); 
        	}   
        }  
        return rootItem;
	}
	
    public TreeItem<PathExtented> createBranch(File chemin, TreeItem<PathExtented> item2 ){
    	
        for (File p : chemin.listFiles()) {
        	
        	if(p.isDirectory()){
        		TreeItem<PathExtented> item = new TreeItem<PathExtented> ( new PathExtented(p.toPath(), p.toPath().getFileName().toString()), new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));            
                item2.getChildren().add(item);
        	}   
        }  
        return item2;
	}
}
