package br.usp.each.preprocessing;


public interface Library {

	public abstract boolean contains(String word);

	public abstract String process(String token);

}