package br.usp.each.naiveBayes.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.usp.each.naiveBayes.interfaces.Classifications;
import br.usp.each.processor.Classification;

public class ClassificationContainer implements Classifications {

	private final List<Classification> classifications;
	
	public ClassificationContainer(){
		this.classifications = new ArrayList<Classification>();
	}
	
	@Override
	public void add(Classification classification) {
		this.classifications.add(classification);
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
	public Classification get(String classificationName) {
		classificationName = classificationName.toUpperCase();
		ListIterator<Classification> classificationsIterator = classifications.listIterator();
		while(classificationsIterator.hasNext()){
			Classification classification = classificationsIterator.next();
			if (classification.getName().equals(classification)){
				return classification;
			}
		}
		return null;		
	}

	@Override
	public Classification get(int index) {
		return this.classifications.get(index);
	}

	@Override
	public void remove(Classification classification) {
		this.classifications.remove(classification);		
	}

}
