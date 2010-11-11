package br.usp.each.naiveBayes.interfaces;

import java.util.List;

public interface Vocabulary {

	void add(String term);

	boolean contains(String term);

	int size();

	double getRealFrequencyOf(String term);

	double getLaplaceFrequencyOf(String term, int flush, double fit);

	void merge(Vocabulary extractFromFile);

	List<String> list();

}
