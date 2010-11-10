package br.usp.each.preprocessing;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

/* Motivação:
 * "Stop words (ou palavras de parada – tradução livre) são palavras que podem ser consideradas 
 * irrelevantes para o conjunto de resultados a ser exibido em uma busca realizada em uma search engine. 
 * Exemplos: as, e, os, de, para, com, sem, foi."
 */

public class StopWordDictionaryDeveria {

	@Test(expected=IllegalArgumentException.class)
	public void lancarExcessaoAoReceberUmParametroNulo() throws Exception {
		new StopWordDictionary(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lancarExcessaoAoReceberUmParametroVazio() throws Exception {
		new StopWordDictionary("");
	}
	
	@Test
	public void identificarUmaStopWordConhecidaNoSeuDicionario() throws Exception {
		StopWordDictionary stopWordDictionary = new StopWordDictionary("dataSource/utils/stopWords.dat");
		boolean valorRecuperado = stopWordDictionary.contains("GoTTen");
		assertThat(valorRecuperado, CoreMatchers.is(true));
	}
	
}