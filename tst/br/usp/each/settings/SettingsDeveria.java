package br.usp.each.settings;

import java.io.File;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class SettingsDeveria {
	
	@Test
	public void reconhecerOArquivoDeConfiguracoesEDevolverODicionarioDeStopWords() throws Exception {
		Settings settings = new Settings();
		File file = settings.getStopWordLibrary();
		Assert.assertThat(file.getAbsolutePath(), Matchers.notNullValue());
	}

}
