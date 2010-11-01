package br.usp.each.naiveBayes.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.usp.each.naiveBayes.interfaces.Vocabulary;


public final class BayesianVocabulary implements Vocabulary {
	
	private final List<String> vocabulary;
	
	public BayesianVocabulary() {
		super();
		this.vocabulary = new ArrayList<String>();
	}
	
	@Override
	public void add(String word) {
		if (word.length() > 1){
			this.vocabulary.add(word.toUpperCase());
		}else{
			throw new IllegalArgumentException("Somente palavras com duas letras ou mais podem ser adicionadas ao vocabulário");
		}
	}

	@Override
	public boolean contains(String word) {
		return this.vocabulary.contains(word.toUpperCase());
	}

	@Override
	public int size() {
		return this.vocabulary.size();
	}

	public List<String> getWordList() {
		return vocabulary;
	}

	@Override
	public double getRealFrequencyOf(String term) {
		return getLaplaceFrequencyOf(term, 0, 0);		
	}

	@Override
	public double getLaplaceFrequencyOf(String term, int flush, double fit) {
		double wordCount   = fit;
		double laplaceBase = (double)(this.size() + flush);
		if (laplaceBase != 0){
			if (this.contains(term)) {
			    Iterator<String> vocabularyIterator = vocabulary.iterator();
			    while (vocabularyIterator.hasNext()){
			    	if (term.toUpperCase().equals(vocabularyIterator.next())){
			    		wordCount += 1.0; 
			    	}
			    }
			}
			return (double) wordCount / laplaceBase;			
		}else{
			return 0.0;
		}
	}

}
