package br.usp.each.naiveBayes.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.usp.each.naiveBayes.interfaces.Classifications;

public class ClassificationContainer implements Classifications {

	private final List<BayesianClassification> classifications;
	
	public ClassificationContainer(){
		this.classifications = new ArrayList<BayesianClassification>();
	}
	
	@Override
	public void add(BayesianClassification bayesianClassification) {
		this.classifications.add(bayesianClassification);
	}

	@Override
	public int count() {
		return this.classifications.size();
	}

	@Override
	public boolean contains(String classification) {
		return this.get(classification) == null ? false : true;
	}

	@Override
	public BayesianClassification get(String classification) {
		classification = classification.toUpperCase();
		ListIterator<BayesianClassification> classificationsIterator = classifications.listIterator();
		while(classificationsIterator.hasNext()){
			BayesianClassification bayesianClassification = classificationsIterator.next();
			if (bayesianClassification.getName().equals(classification)){
				return bayesianClassification;
			}
		}
		return null;		
	}

	@Override
	public void remove(BayesianClassification bayesianClassification) {
		this.classifications.remove(bayesianClassification);
	}

}
