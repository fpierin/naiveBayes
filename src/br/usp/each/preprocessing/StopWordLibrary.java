package br.usp.each.preprocessing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StopWordDictionary {

	private final List<String> stopWords = new ArrayList<String>();

	public StopWordDictionary(String stopWordsFileSource) {
		if ((stopWordsFileSource == null) || (stopWordsFileSource.equals(""))) {
			throw new IllegalArgumentException("O arquivo de definções de \"Stop words\" não pode ser vazio.");
		}
		loadStopWordsFromFileSource(stopWordsFileSource);
	}

	private void loadStopWordsFromFileSource(String stopWordsFileSource) {
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String stopWord = null;
		try {
			inputStream = new FileInputStream(stopWordsFileSource);
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((stopWord = bufferedReader.readLine()) != null) {
				stopWords.add(stopWord.trim().toUpperCase());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean contains(String word) {
		word = word.trim();
		if ((word == null) || (word.equals(""))){
			return false;
		}
		return this.stopWords.contains(word.trim().toUpperCase());
	}

}
