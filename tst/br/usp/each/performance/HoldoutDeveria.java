package br.usp.each.performance;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import br.usp.each.performance.Holdout;


public class HoldoutDeveria {
	
	@Test(expected=IllegalArgumentException.class)
	public void gerarUmaExcessaoSeUmArquivoVazioForParametro() throws Exception {
		String pathname = "";
		File source = new File(pathname);
		new Holdout(source);
	}
	
	@Test
	public void identificarTotalDeArquivosConhecido() throws Exception {
		HoldoutDeveria holdoutDeveria = new HoldoutDeveria();
	}
	
}
