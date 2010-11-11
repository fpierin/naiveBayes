package br.usp.each.preprocessing;

import static org.junit.Assert.assertThat;

import java.io.File;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import br.usp.each.settings.Settings;

public class StopWordLibraryDeveria {

	@Test(expected=IllegalArgumentException.class)
	public void lancarExcessaoAoReceberUmParametroNulo() throws Exception {
		new StopWordLibrary(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lancarExcessaoAoReceberUmParametroVazio() throws Exception {
		new StopWordLibrary(new File(""));
	}
	
	@Test
	public void identificarUmaStopWordConhecidaNoSeuDicionario() throws Exception {
		Library stopWordDictionary = new StopWordLibrary(new Settings().getStopWordLibrary());
		boolean valorRecuperado = stopWordDictionary.contains("GoTTen");
		assertThat(valorRecuperado, CoreMatchers.is(true));
	}
	
}