package br.usp.each.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.usp.each.preprocessing.Library;
import br.usp.each.preprocessing.StopWordLibrary;
import br.usp.each.settings.Settings;

public class TextProcessor {
	
	private Library emailHeaders;
	private Library stopWords;
	private Library badCaracteres; 
	
	public TextProcessor() {
		File dataSource = new Settings().getStopWordLibrary();
		stopWords = new StopWordLibrary(dataSource);
		emailHeaders = new EmailHeaderLibrary();
		badCaracteres = new BadCaracteres(); 
	}

	public List<String> getBagOfWords(InputStream inputStream) {
		List<String> bagOfWords;
		InputStreamReader inputStreamReader;
		BufferedReader bufferedReader;
		try {
			bagOfWords = new ArrayList<String>();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				List<String> lineBagOfWords = processLine(line);
				bagOfWords.addAll(lineBagOfWords);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return bagOfWords;
	}

	private List<String> processLine(String line) {
		List<String> lineBagOfWords = new ArrayList<String>();
		
		if (!lineIsAHeader(line)){
			StringTokenizer stringTokenizer = new StringTokenizer(line);
			while(stringTokenizer.hasMoreTokens()){
				String token = stringTokenizer.nextToken();
				if (!tokenIsAStopWord(token)){
					String finalToken = removeBadCaracteres(token);
					finalToken = finalToken.trim().toUpperCase();
					if (finalToken.length() > 1){
						lineBagOfWords.add(finalToken);
					}
				}
			}
		}
		
		return lineBagOfWords;
	}

	private String removeBadCaracteres(String token) {
		if(isInvalidRegex(token)){
			return "";
		}
		return badCaracteres.process(token);
	}

	private boolean isInvalidRegex(String token) {
		String regex = "^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(token);
		return m.matches();
	}

	private boolean tokenIsAStopWord(String token) {
		return stopWords.contains(token.trim().toUpperCase());
	}

	private boolean lineIsAHeader(String line) {		
		return emailHeaders.contains(line);
	}

	public static void main(String[] args) throws FileNotFoundException {
		final String string = "dataSource/samples/Treinamento/sci.space";		
		final TextProcessor textProcessor = new TextProcessor();
		File diretorio = new File(string);
		File[] arquivos = diretorio.listFiles(); 
		for(int i = 0; i < arquivos.length ; i++){
			InputStream inputStream = new FileInputStream(arquivos[i]);
			List<String> bagOfWordsFrom;
			bagOfWordsFrom = textProcessor.getBagOfWords(inputStream);
			
			for (String word : bagOfWordsFrom) {
				System.out.println(word);
			}
		}
	}

}
