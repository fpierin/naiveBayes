package br.usp.each.processor;

import java.util.ArrayList;
import java.util.List;

import br.usp.each.preprocessing.Library;

public class BadCaracteres implements Library {

	private final List<String> badCaracteres = new ArrayList<String>();
	
	public BadCaracteres() {
		load();
	}

	private void load() {
//		badCaracteres.add("+");
//		badCaracteres.add(".");
//		badCaracteres.add(",");
//		badCaracteres.add(":");
//		badCaracteres.add(">");
//		badCaracteres.add("<");
//		badCaracteres.add("?");
//		badCaracteres.add("!");
//		badCaracteres.add("#");
//		badCaracteres.add("/");
//		badCaracteres.add("\\");
//		badCaracteres.add("/");
//		badCaracteres.add("-");
//		badCaracteres.add("@");
//		badCaracteres.add("\"");
//		badCaracteres.add("\'");
//		badCaracteres.add("{");
//		badCaracteres.add("}");
//		badCaracteres.add("[");
//		badCaracteres.add("]");
//		badCaracteres.add("(");
//		badCaracteres.add(")");
//		badCaracteres.add("^");
//		badCaracteres.add("~");
//		badCaracteres.add(";");
//		badCaracteres.add("_");
//		badCaracteres.add("*");
//		badCaracteres.add("&");
//		badCaracteres.add("|");
		badCaracteres.add("=");			
	}

	@Override
	public boolean contains(String token) {
		return token.contains(token);
	}

	@Override
	public String process(String token) {
		for(String caracter: badCaracteres){
			token = token.replace(caracter, "");
		}
		return token.trim().toUpperCase();
	}

}
