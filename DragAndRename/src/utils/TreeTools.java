package utils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.plaf.RootPaneUI;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.PathExtented;

public class TreeTools {
	
	public TreeItem<PathExtented> createRoot(PathExtented depart){
		
		TreeItem<PathExtented> rootItem = new TreeItem<PathExtented> (depart, new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));
        rootItem.setExpanded(true);
        
        File[] files_to_iretate = depart.toFile().listFiles();
    	
    	ArrayList<File> sortedFiles = new ArrayList<File>(Arrays.asList(files_to_iretate));
    	
    	Collections.sort(sortedFiles, ItemComparator.getListComparator());
        
        for (File p : sortedFiles) {

        	if(p.isDirectory()){
        		TreeItem<PathExtented> item = new TreeItem<PathExtented> (new PathExtented(p.toPath(), p.toPath().getFileName().toString()), new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));            
        		
        		createBranch(p, item);
        		
        		rootItem.getChildren().add(item); 
        	}   
        }  
        return rootItem;
	}
	
    public TreeItem<PathExtented> createBranch(File chemin, TreeItem<PathExtented> item2 ){
    	
        File[] files_to_iretate = chemin.listFiles();
    	
    	ArrayList<File> sortedFiles = new ArrayList<File>(Arrays.asList(files_to_iretate));
    	
    	Collections.sort(sortedFiles, ItemComparator.getListComparator());
        
        for (File p : sortedFiles) {
        	
        	if(p.isDirectory()){
        		TreeItem<PathExtented> item = new TreeItem<PathExtented> ( new PathExtented(p.toPath(), p.toPath().getFileName().toString()), new ImageView(new Image(getClass().getResourceAsStream("folder.png"))));            
                item2.getChildren().add(item);
        	}   
        }  
        return item2;
	}
}
