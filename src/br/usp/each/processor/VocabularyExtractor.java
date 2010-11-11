package br.usp.each.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import br.usp.each.naiveBayes.classes.BayesianClassification;
import br.usp.each.naiveBayes.classes.BayesianVocabulary;
import br.usp.each.naiveBayes.classes.ClassificationContainer;
import br.usp.each.naiveBayes.classes.NaiveBayesClassifier;
import br.usp.each.naiveBayes.interfaces.Classifications;
import br.usp.each.naiveBayes.interfaces.Vocabulary;
import br.usp.each.preprocessing.StopWordLibrary;
import br.usp.each.settings.Settings;

public class VocabularyExtractor {
	
	private final StopWordLibrary stopWords;

	public VocabularyExtractor(){
		File dataSource = new Settings().getStopWordLibrary();
		stopWords = new StopWordLibrary(dataSource);
	}
	
	public static void main(String[] args) {
		final File compOSMSWindowsMisc = new File("dataSource/docs/Treinamento/comp.os.ms-windows.misc");
		final File sciSpace = new File("dataSource/docs/Treinamento/sci.space");
		final File talkPoliticsMisc = new File("dataSource/docs/Treinamento/talk.politics.misc");
		final VocabularyExtractor vocabularyExtractor = new VocabularyExtractor();
		
		File[] files = {compOSMSWindowsMisc, sciSpace, talkPoliticsMisc};
		Classifications classifications = new ClassificationContainer();
		
		for (File classe: files){
			System.out.println(classe.getName());
			Vocabulary vocabulary = vocabularyExtractor.extractFrom(classe);
			Classification classification = new BayesianClassification(classe.getName(), vocabulary);
			classifications.add(classification);
		}
		
		NaiveBayesClassifier naiveBayesClassifier = new NaiveBayesClassifier(classifications);
//		File source = new File("dataSource/docs/Teste/comp.os.ms-windows.misc/10010");
//		File source = new File("dataSource/docs/Teste/sci.space/61242");		
		File source = new File("dataSource/docs/Teste/talk.politics.misc/178476");		
		List<String> bagOfWords = vocabularyExtractor.extractFrom(source).list();
		String resultClassName = naiveBayesClassifier.classify(bagOfWords);
		System.out.println("\nClassificação: " + resultClassName);
	}

	public Vocabulary extractFrom(File source) {
		if (source.isFile()) {
			return extractFromFile(source);
		} else if (source.isDirectory()) {
			return extractFromDirectory(source);			
		}
		return null;
	}

	private Vocabulary extractFromDirectory(File directory) {
		File[] documents = directory.listFiles();
		Vocabulary vocabulary = new BayesianVocabulary();
		for(File document: documents){
			if (document.isFile()){
				vocabulary.merge(extractFromFile(document));
			} else if (document.isDirectory()){
				vocabulary.merge(extractFromDirectory(document));				
			}
		}
		return vocabulary;
	}

	private Vocabulary extractFromFile(File file) {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			Vocabulary vocabulary = new BayesianVocabulary();
			String line;
			while((line = bufferedReader.readLine()) != null){
				vocabulary.merge(getVocabularyFromLine(line));
			}
			return vocabulary;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Vocabulary getVocabularyFromLine(String line) {
		line = line.trim().toUpperCase();
		if (!isValidLine(line)) {
			return getTokensFromLine(line);
		}
		return null;
	}

	private Vocabulary getTokensFromLine(String line) {
		Vocabulary vocabulary = new BayesianVocabulary();
		StringTokenizer tokens = new StringTokenizer(line);
		while(tokens.hasMoreTokens()){
			String word = tokens.nextToken();			
			if ((word = prepareWord(word)).length() > 1) {
				vocabulary.add(word);
			}
		}
		return vocabulary;
	}

	private String prepareWord(String word) {
		if (!(isEmail(word)) && !(isPhone(word))){
			word = removeBadKeyWordsFrom(word);
			if (!(isNumber(word)) && !(isStopWord(word))){
				return word;
			}
		}
		return "";
	}

	private boolean isNumber(String word) {
		try{
			return (Integer.valueOf(word) != null);
		} catch(Exception e){
			return false;
		}
	}

	private boolean isStopWord(String word) {
		return stopWords.contains(word);
	}

	private String removeBadKeyWordsFrom(String word) {
		final String badKeyWords = "\"!?@#$*()-_=+[]{}^~ÁÉÍÓÚÄËÏÖÜÀÈÌÒÙÇ:;.,<>|\'\\";
		char[] charArray = badKeyWords.toCharArray();
		
		for (char caracter: charArray){
			word = word.replace(String.valueOf(caracter).toUpperCase(), "");
		}
		
		return word;
	}

	private boolean isPhone(String word) {
		return word.contains("/");
	}

	private boolean isEmail(String word) {
		return word.contains("@") && !(word.startsWith("@") || word.endsWith("@"));		
	}

	private boolean isValidLine(String line) {
		return line.startsWith("FROM:") || 
			   line.startsWith("SUBJECT:") ||
			   line.startsWith("ORGANIZATION:") ||
    		   line.startsWith("ARTICLE-I.D.:") ||		
    		   line.startsWith("DISTRIBUTION:") || 
    		   line.startsWith("NNTP-POSTING-HOST:") ||
    		   line.startsWith("LINES:") ||
    		   line.startsWith(">") ||
    		   line.startsWith("|") ||
    		   (line.startsWith("IN ARTICLE <") && line.endsWith("WRITES:")) ||
    		   (line.startsWith("IN <") && line.endsWith("WRITES:")) ||	
			   line.startsWith("REPLY-TO:") ||
			   line.startsWith("ORIGINATOR:") ||
			   line.startsWith("X-HEADER:") ||
			   line.startsWith("X-DISCLAIMER:") ||		
			   line.startsWith("XXFROM:") ||
			   line.startsWith("KEYWORDS:") ||
			   line.startsWith("EXPIRES:") ||
			   line.startsWith("SUPERSEDES:") ||
			   line.startsWith("X-X-FROM:") ||
			   line.startsWith("NEWS-SOFTWARE:") ||
			   line.startsWith("ARCHIVE-NAME:") ||
			   line.startsWith("X-SENDER:") ||
			   line.startsWith("X-ADDED:") ||
			   line.startsWith("ORIGINAL-SENDER:") ||
			   line.startsWith("X-XXMESSAGE-ID:") ||
			   line.startsWith("X-XXDATE:") ||
			   line.startsWith("X-USERAGENT:") ||			   
			   line.startsWith("LAST-MODIFIED:");
	}

}
