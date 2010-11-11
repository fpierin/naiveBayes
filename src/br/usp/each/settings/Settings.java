package br.usp.each.settings;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class Settings {

	private final String settings = "naiveBayes.properties";
	private final String stopWordLibrary = "stopword.library";

	public File getStopWordLibrary() {
		Properties properties = null;
		FileInputStream inputStream = null;
		try{
			inputStream = new FileInputStream(settings);
			properties = new Properties();
			properties.load(inputStream);
			String library = properties.getProperty(stopWordLibrary);
			return new File(library);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}