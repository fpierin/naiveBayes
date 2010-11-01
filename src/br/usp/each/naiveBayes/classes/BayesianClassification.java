package br.usp.each.naiveBayes.classes;

import java.util.Iterator;
import java.util.List;

import br.usp.each.naiveBayes.interfaces.Vocabulary;


public class BayesianClassification {

	private final String name;
	private final Vocabulary vocabulary;	

	public BayesianClassification(String name) {
		if ((name == null) || (name.isEmpty())){
			throw new IllegalArgumentException("Uma classe bayesiana precisa ser identificada com um nome.");
		}
		this.name = setName(name);
		this.vocabulary = new BayesianVocabulary(); 
	}

	private String setName(String name) {
		return name.toUpperCase();
	}

	public Vocabulary getVocabulary() {
		return this.vocabulary;
	}

	public String getName() {
		return name;
	}

	public double getClassificationProbality(List<String> termList) {
		if (termList.size() > 0){
			double probalityValue = 1.0;
		    Iterator<String> termIterator = termList.iterator();
		    while (termIterator.hasNext()){
		    	probalityValue *= vocabulary.getLaplaceFrequencyOf(termIterator.next(), termList.size(), 0.5);
		    }			
		    return probalityValue;
		}
	    return 0.0;		
	}

}
