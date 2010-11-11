package br.usp.each.naiveBayes.interfaces;

import br.usp.each.processor.Classification;

public interface Classifications {
	
	public void add(Classification classification);
	
	public int count();

	public boolean contains(String classification);

	public Classification get(String classification);
	
	public Classification get(int index);	

	public void remove(Classification classification);

}
