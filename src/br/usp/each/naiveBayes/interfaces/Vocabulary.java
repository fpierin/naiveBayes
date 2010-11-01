package br.usp.each.naiveBayes.interfaces;

public interface Vocabulary {

	void add(String term);

	boolean contains(String term);

	int size();

	double getRealFrequencyOf(String term);

	double getLaplaceFrequencyOf(String term, int flush, double fit);

}
