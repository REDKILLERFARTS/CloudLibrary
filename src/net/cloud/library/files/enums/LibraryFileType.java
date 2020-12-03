package net.cloud.library.files.enums;

public enum LibraryFileType {

	CONFIG("config");
	
	String name;
	
	LibraryFileType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
