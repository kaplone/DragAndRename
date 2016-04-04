package utils;

import application.Drag_controller;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import models.PathExtented;

public class DragControl {
	
	public static void drag( ListView dragTarget, TreeItem<PathExtented> treeItem, Drag_controller drag_controller){
	
		dragTarget.setOnDragOver(new EventHandler<DragEvent>() {
	
	        @Override
	        public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.COPY);
	            event.consume();
	        }
	    });
	
	    dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {
	
	        @Override
	        public void handle(DragEvent event) {

	            Dragboard db = event.getDragboard();
	            boolean success = false;

            	CopyAndRename.copyAndRename(db, treeItem.getValue().getPath());	
            	drag_controller.populate(treeItem);
                success = true;       
	                
	            /* let the source know whether the string was successfully 
	             * transferred and used */
	            event.setDropCompleted(success);
	            event.consume();
	        }
	    });
	}


}
