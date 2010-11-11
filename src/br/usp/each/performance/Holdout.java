package br.usp.each.performance;

import java.io.File;

public class Holdout {

	public Holdout(File source) {
		if (source == null || !source.exists() || !source.isDirectory()){
			throw new IllegalArgumentException("Diretório de classes inválido.");
		}
	}

	public static void main(String[] args) {
		for(String arg: args)
			System.out.println(arg);
	}

}
