package utils;

import java.io.File;
import java.util.Comparator;

import javafx.scene.control.TreeItem;
import models.PathExtented;

public class ItemComparator {
	
	public static Comparator<TreeItem<PathExtented>> getComparator(){
	
		Comparator<TreeItem<PathExtented>> itemComparator = new Comparator<TreeItem<PathExtented>>() {
	        @Override
	        public int compare(TreeItem<PathExtented>  item1, TreeItem<PathExtented>  item2)
	        {
	
	            return  item1.getValue().getStr().compareTo(item2.getValue().getStr());
	        }
	    };
	    
	    return itemComparator;
	}
	
	public static Comparator<File> getListComparator(){
		
		Comparator<File> itemComparator = new Comparator<File>() {
	        @Override
	        public int compare(File item1, File  item2)
	        {
	
	            return  item1.toString().compareTo(item2.toString());
	        }
	    };
	    
	    return itemComparator;
	}

}
