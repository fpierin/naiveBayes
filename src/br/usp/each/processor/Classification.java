package br.usp.each.processor;

import java.util.List;

public interface Classification {

	String getName();

	double getProbality(List<String> bagOfWords);

}
