package br.usp.each.naiveBayes.classes;

import java.util.List;

import br.usp.each.naiveBayes.interfaces.Classifications;



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

	public double classify(List<String> bagOfWords) {
		return 0;
	}

}
