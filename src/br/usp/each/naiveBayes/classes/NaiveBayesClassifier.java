package br.usp.each.naiveBayes.classes;

import java.util.List;

import br.usp.each.naiveBayes.interfaces.Classifications;
import br.usp.each.processor.Classification;

public final class NaiveBayesClassifier {

	private final Classifications classifications;

	public NaiveBayesClassifier(Classifications classifications) {
		if (notValidClassification(classifications)){
			throw new IllegalArgumentException("Um classificador bayeseano precisa de pelo menos duas classificações definidas.");
		}
		this.classifications = classifications;
	}

	private boolean notValidClassification(Classifications classifications) {
		return (classifications == null) || (classifications.count() < 2);
	}

	public Classifications getClassifications() {
		return classifications;
	}

	public void addClassification(BayesianClassification classificacao) {
		if (classificacao == null ){
			throw new IllegalArgumentException("Uma nova classificação não pode ser vazia.");
		}
		this.classifications.add(classificacao);
	}

	public String classify(List<String> bagOfWords) {
		String resultClass = null;
		double resultProbality = Double.NEGATIVE_INFINITY;		
		
		for (int i = 0; i < classifications.count(); i++){
			Classification classification = classifications.get(i);			
			double classificationProbality = classification.getProbality(bagOfWords);			

			System.out.println(classification.getName() + ": " + classificationProbality);
			
			if (classificationProbality > resultProbality) {
				resultProbality = classificationProbality;
				resultClass = classification.getName();
			}
		}
		
		
		return (resultClass != null) ? resultClass : "none";
		
	}

}
