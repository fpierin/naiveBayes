package br.usp.each.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Stop words (ou palavras de parada – tradução livre) são palavras que podem ser 
 * consideradas irrelevantes para o conjunto de resultados a ser exibido em uma busca 
 * realizada em uma search engine. Exemplos: as, e, os, de, para, com, sem, foi.
 */

public class StopWordLibrary implements Library {

	private final List<String> stopWords = new ArrayList<String>();

	public StopWordLibrary(File library) {
		if ((library != null) && (library.isFile())) {
			loadFromFile(library);
		} else {
			throw new IllegalArgumentException(
					"O arquivo de definções de \"Stop words\" não é válido.");
		}
	}

	public void loadFromFile(File library) {
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String stopWord = null;
		try {
			inputStream = new FileInputStream(library);
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((stopWord = bufferedReader.readLine()) != null) {
				stopWords.add(stopWord.trim().toUpperCase());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean contains(String word) {
		if ((word == null) || (word.equals(""))) {
			return false;
		}
		return this.stopWords.contains(word.trim().toUpperCase());
	}

	@Override
	public String process(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
