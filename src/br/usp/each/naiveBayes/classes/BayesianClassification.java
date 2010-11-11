package br.usp.each.naiveBayes.classes;

import java.util.Iterator;
import java.util.List;

import br.usp.each.naiveBayes.interfaces.Vocabulary;
import br.usp.each.processor.Classification;

public class BayesianClassification implements Classification {

	private final String name;
	private final Vocabulary vocabulary;
	private double probality = 0.0;
	
	public BayesianClassification(String name, Vocabulary vocabulary){
		if ((name == null) || (name.isEmpty())){
			throw new IllegalArgumentException("Uma classe bayesiana precisa ser identificada com um nome.");
		}
		this.name = setName(name);
		this.vocabulary = vocabulary;
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

	public double getProbality(List<String> termList) {
		if (termList.size() > 0){
			double probalityValue = 1.0;
		    Iterator<String> termIterator = termList.iterator();
		    while (termIterator.hasNext()){
		    	double laplaceFrequencyOf = vocabulary.getLaplaceFrequencyOf(termIterator.next(), termList.size(), 0.5);
				probalityValue += Math.log(laplaceFrequencyOf);
		    }			
		    return (this.probality != 0.0) ? (this.probality + probalityValue) : probalityValue;
		}
	    return 0.0;		
	}

	public void setProbality(double probality) {
		this.probality = Math.log(probality);
	}

	public double getProbality() {
		return probality;
	}

}
