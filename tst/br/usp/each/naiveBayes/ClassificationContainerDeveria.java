package br.usp.each.naiveBayes;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import br.usp.each.naiveBayes.classes.BayesianClassification;
import br.usp.each.naiveBayes.classes.ClassificationContainer;
import br.usp.each.naiveBayes.interfaces.Classifications;


public class ClassificationContainerDeveria {

	@Test
	public void informarAQuantidadeConhecidasDeClassificacoesGuardadas() throws Exception {
		Classifications classifications = new ClassificationContainer();
		
		classifications.add(new BayesianClassification("A"));
		classifications.add(new BayesianClassification("B"));
		classifications.add(new BayesianClassification("C"));
		classifications.add(new BayesianClassification("D"));		
		
		int countEncontrado = classifications.count();
		assertThat(countEncontrado, CoreMatchers.is(4));
	}
	
	@Test
	public void terACapacidadeDeResponderSeContemUmaClassificacaoPeloNomeIndependentementeDeSuaFormatacao(){
		Classifications classifications = new ClassificationContainer();
		
		classifications.add(new BayesianClassification("RobErto"));
		classifications.add(new BayesianClassification("CaroL"));
		classifications.add(new BayesianClassification("JulianA"));
		classifications.add(new BayesianClassification("FELipE"));		
		
		boolean valorEncontrado = classifications.contains("FELipe");
		assertThat(valorEncontrado, CoreMatchers.is(true));
	}
	
	@Test
	public void retornarTrueParaUmaClassificacaoContidaNoContainer(){
		Classifications classifications = new ClassificationContainer();
		
		classifications.add(new BayesianClassification("RODOLFO"));
		classifications.add(new BayesianClassification("ETTORE"));
		
		boolean valorEncontrado = classifications.get("rodoLfo") == null ? false : true;
		assertThat(valorEncontrado, CoreMatchers.is(true));		
	}
	
	@Test
	public void informarUmNumeroConhecidoDeClassificacoesAposUmaRemocao(){
		Classifications classifications = new ClassificationContainer();
		
		classifications.add(new BayesianClassification("RobErto"));
		classifications.add(new BayesianClassification("CaroL"));
		classifications.add(new BayesianClassification("JulianA"));
		
		BayesianClassification bayesianClassification = classifications.get("CaroL");
		classifications.remove(bayesianClassification);
		
		int valorEncontrado = classifications.count();
		assertThat(valorEncontrado, CoreMatchers.is(2));
	}
	
}
