package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import application.Drag_controller;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;

import java.nio.file.Files;

public class CopyAndRename {

	public static void copyAndRename(Dragboard db, Path path) {
		
		ArrayList<File> files = (ArrayList<File>) db.getContent(DataFormat.FILES);
		
		for (int i = 0; i < files.size(); i++){
			
			File file = files.get(i);
			
			try {
				Files.copy(file.toPath(),
						   NewName(file, path),
						   StandardCopyOption.COPY_ATTRIBUTES);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private static Path NewName(File file, Path path) {
		
		Path newPath = path.resolve(String.format(Drag_controller.getTemplate(), path.getParent().getFileName() , path.getFileName(), getNum(path)));
		
		return newPath;
	}

	private static int getNum(Path path) {
		
		return path.toFile().listFiles().length + 1;
		
	}

}
