package br.usp.each.naiveBayes;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.usp.each.naiveBayes.classes.BayesianClassification;
import br.usp.each.naiveBayes.classes.NaiveBayesClassifier;
import br.usp.each.naiveBayes.interfaces.Classifications;

@RunWith(JMock.class)
public class NaiveBayesClassifierDeveria {

	private Mockery contexto = new Mockery();
	private final Classifications classifications = contexto.mock(Classifications.class);
	
	@Before
	public void permitirOMockDeUmaClassificacao(){
		contexto.setImposteriser(ClassImposteriser.INSTANCE);
	}
	
	@Test
	public void delegarACapacidadeDeArmazenarClassificacoesParaUmContainerDeClassicacoes(){
		final BayesianClassification classificacao = contexto.mock(BayesianClassification.class);
		
		contexto.checking(new Expectations(){{
			
			allowing(classifications).count();
			will(returnValue(45));
			
			one(classifications).add(classificacao);
		}});
		
		NaiveBayesClassifier naiveBayesClassifier = new NaiveBayesClassifier(classifications);
		naiveBayesClassifier.addClassification(classificacao);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lancarUmaExcessaoAoReceberUmContainerDeClassificacoesNulo() throws Exception {
		new NaiveBayesClassifier(null);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lancarUmaExcessaoAoReceberComoParametroUmContainerDeClassificacoesComApenasUmaClassificacao() throws Exception {
		
		contexto.checking(new Expectations(){{
			one(classifications).count();
			will(returnValue(1));
		}});
		
		new NaiveBayesClassifier(classifications);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lancarUmaExcessaoAoReceberComoParametroUmContainerDeClassificacoesComZeroClassificacoes() throws Exception {
		
		contexto.checking(new Expectations(){{
			one(classifications).count();
			will(returnValue(0));
		}});
		
		new NaiveBayesClassifier(classifications);
	}	
	
	@Test
	public void calcularAProbabilidadeConhecidaDeUmDadoUmDocumentoDeTextoPertencerAUmaDeterminadaClasse() throws Exception {
		final List<String> bagOfWords = Arrays.asList("Uni", "Dune", "Teti");
		final String classification = "C";
		final BayesianClassification classeC = new BayesianClassification("C", null);
		
		contexto.checking(new Expectations(){{
			allowing(classifications).count();
			will(returnValue(2));
//			one(classifications).get(classification);
//			will(returnValue(classeC));
		}});
		
//		NaiveBayesClassifier naiveBayesClassifier = new NaiveBayesClassifier(classifications);
//		double valorEncontrado = naiveBayesClassifier.classify(bagOfWords);
//		Assert.assertEquals(0.5, valorEncontrado, 0.000001);
	}
	
	
	
}
