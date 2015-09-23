package models;

import java.io.File;
import java.nio.file.Path;

public class PathExtented {
	
	Path path;
	String str;
	
	public PathExtented(Path path, String str) {
		super();
		this.path = path;
		this.str = str;
	}
	
	public File toFile(){
		return this.path.toFile();
	}
	
	@Override
	public String toString(){
		return this.str;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	
}
