package br.usp.each.naiveBayes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import br.usp.each.naiveBayes.classes.BayesianVocabulary;
import br.usp.each.naiveBayes.interfaces.Vocabulary;


public class BayesianVocabularyDeveria {
	
	@Test
	public void permitirQuePalavrasSejamAdicionadasEmMaiusculoUmaAUmaAoVocabulario(){
		Vocabulary vocabulary = new BayesianVocabulary();
		String palavraChave = "felipe";
		vocabulary.add(palavraChave);
		assertTrue(vocabulary.contains("FELIPE"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void adicionarApenasAsPalavrasComTamanhoSuperiorAUmCaracter(){
		Vocabulary vocabulary = new BayesianVocabulary();
		vocabulary.add("a");
	}
	
	@Test
	public void guardarAQuantidadeDePalavrasQueFormamOSeuVocabulario(){
		int valorEsperado = 3;
		Vocabulary vocabulary = new BayesianVocabulary();
		vocabulary.add("Um");
		vocabulary.add("Dois");
		vocabulary.add("Tres");		
		int valorEncontrado = vocabulary.size();
		assertEquals(valorEsperado, valorEncontrado);
	}
	
	@Test
	public void retornarFrequenciaZeroParaUmaListaVazia(){
		Vocabulary vocabulary = new BayesianVocabulary();
		double valorRetornado = vocabulary.getRealFrequencyOf("teste");
		assertThat(valorRetornado, CoreMatchers.is(0.0));
	}
	
	@Test
	public void retornarUmaFrequenciaConhecidaParaUmaPalavraConhecidaDeUmaListaConhecida(){
		Vocabulary vocabulary = new BayesianVocabulary();
		vocabulary.add("felipe");
		vocabulary.add("fernando");
		vocabulary.add("fernanda");
		vocabulary.add("Oliver");
		vocabulary.add("Samba");
		vocabulary.add("Digo");
		vocabulary.add("SI");
		vocabulary.add("FeLiPE");		
		double valorRetornado = vocabulary.getRealFrequencyOf("fELIPE");
		assertThat(valorRetornado, CoreMatchers.is(0.25));
	}	
	
	@Test
	public void retornarUmaDizimaConhecidaDeUmaPalavraConhecidaEmUmaListaConhecida(){
		Vocabulary vocabulary = new BayesianVocabulary();
		vocabulary.add("felipe");
		vocabulary.add("fernando");
		vocabulary.add("fernanda");
		double valorRetornado = vocabulary.getRealFrequencyOf("FeLiPE");
		assertThat(valorRetornado, CoreMatchers.is(0.3333333333333333));
	}		
	
}
