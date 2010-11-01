package br.usp.each.naiveBayes.interfaces;

import br.usp.each.naiveBayes.classes.BayesianClassification;

public interface Classifications {
	
	public void add(BayesianClassification bayesianClassification);
	
	public int count();

	public boolean contains(String classification);

	public BayesianClassification get(String classification);

	public void remove(BayesianClassification bayesianClassification);

}
