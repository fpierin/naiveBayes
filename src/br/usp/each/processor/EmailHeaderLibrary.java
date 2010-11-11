package br.usp.each.processor;

import java.util.ArrayList;
import java.util.List;

import br.usp.each.preprocessing.Library;

public final class EmailHeaderLibrary implements Library {
	
	private final List<String> headers = new ArrayList<String>();
	
	public EmailHeaderLibrary() {
		loadHeaders();
	}

	private void loadHeaders() {
		headers.add("SUBJECT:");
		headers.add("Article-I.D.:".toUpperCase());		
		headers.add("FROM:");
		headers.add("REPLY-TO:");
		headers.add("DISTRIBUTION:");
		headers.add("ORGANIZATION:");
		headers.add("NNTP-POSTING-HOST:");
		headers.add("ORIGINATOR:");
		headers.add("LINES:");
		headers.add("X-Header:".toUpperCase());
		headers.add("X-Disclaimer:".toUpperCase());		
		headers.add("XXFROM:");
		headers.add("Keywords:".toUpperCase());
		headers.add("Expires:".toUpperCase());
		headers.add("Supersedes:".toUpperCase());
		headers.add("X-X-From:".toUpperCase());
		headers.add("News-Software:");
		headers.add("Archive-name:"); 
		headers.add("Last-modified:");
		headers.add(">");
		headers.add("|");		
	}

	@Override
	public boolean contains(String line) {
		for(String header: headers){
			if(line.trim().toUpperCase().startsWith(header)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String process(String token) {
		return null;
	}

}
