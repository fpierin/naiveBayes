package br.usp.each.empiric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import br.usp.each.preprocessing.StopWordLibrary;
import br.usp.each.preprocessing.Library;
import br.usp.each.settings.Settings;

public class Checker {

	/*
	 * From: leech@cs.unc.edu (Jon Leech) Subject: Space FAQ 12/15 -
	 * Controversial Questions Keywords: Frequently Asked Questions
	 * Article-I.D.: cs.controversy_733694426 Expires: 6 May 1993 20:00:26 GMT
	 * Distribution: world Organization: University of North Carolina, Chapel
	 * Hill Lines: 252 Supersedes: <controversy_730956589@cs.unc.edu>
	 * NNTP-Posting-Host: mahler.cs.unc.edu Archive-name: space/controversy
	 * Last-modified: $Date: 93/04/01 14:39:06 $
	 */
	public static void main(String[] args) throws IOException {
		Library stopWordDictionary = new StopWordLibrary(new Settings().getStopWordLibrary());
		File directory = new File("dataSource/docs/Teste/comp.os.ms-windows.misc");
		String[] arquivos = directory.list();
		if (arquivos != null) {
			for (int i = 0; i < arquivos.length; i++) {
				String filePath = directory.getAbsolutePath() + "/"
						+ arquivos[i];
				InputStream inputStream = new FileInputStream(filePath);
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if (isValidLine(line)) {
						StringTokenizer stringTokenizer = new StringTokenizer(line);
						while (stringTokenizer.hasMoreTokens()){
							String nextToken = stringTokenizer.nextToken();
							nextToken = nextToken.replace(",", "");
							nextToken = nextToken.replace(".", "");
							//nextToken = nextToken.replace("/", "");
							nextToken = nextToken.replace("-", "");							
							nextToken = nextToken.replace("_", "");
							nextToken = nextToken.replace("^", "");
							nextToken = nextToken.replace("=", "");
							nextToken = nextToken.replace(":", "");											
							nextToken = nextToken.replace("+", "");							
							nextToken = nextToken.replace("{", "");							
							nextToken = nextToken.replace("}", "");
							nextToken = nextToken.replace("[", "");							
							nextToken = nextToken.replace("]", "");
							nextToken = nextToken.replace("~", "");
							nextToken = nextToken.replace("(", "");
							nextToken = nextToken.replace(")", "");
							nextToken = nextToken.replace("|", "");
							nextToken = nextToken.replace("?", "");
							nextToken = nextToken.replace("!", "");							
							nextToken = nextToken.toUpperCase().trim();
							if (!nextToken.equals("") && (nextToken.length() > 1)){
								if (!stopWordDictionary.contains(nextToken)){
									try{
										Integer.parseInt(nextToken);
									}catch(NumberFormatException e){
										System.out.println(nextToken);
									}
								}
							}
						}
						//System.out.println(line);
					}
				}
			}
			System.out.println("\n\n\n####################\n\n\n");
		}
	}

	private static boolean isValidLine(String line) {
		line = line.toUpperCase().trim();
		return !(line.startsWith(">") ||
				 line.startsWith("FROM:") ||
				 line.startsWith("SUBJECT:") ||
				 line.startsWith("SUMMARY:") ||				 
				 line.startsWith("KEYWORDS:") ||
				 line.startsWith("ARTICLE-I.D.:") ||
				 line.startsWith("EXPIRES:") ||
				 line.startsWith("DISTRIBUTION:") ||
				 line.startsWith("ORGANIZATION:") ||
		 		 line.startsWith("LINES:") ||
		 		 line.startsWith("SUPERSEDES:") ||
		 		 line.startsWith("NNTP-POSTING-HOST:") ||
				 line.startsWith("ARCHIVE-NAME:") ||
				 line.startsWith("LAST-MODIFIED:") ||
				 line.startsWith("REPLY-TO:") ||
				 line.endsWith("WROTE:") ||
				 (line.contains("@") && !line.contains("@ECHO")) ||  
				 (line.startsWith("IN ARTICLE") && line.endsWith("WRITES:")) ||
				 (line.startsWith("---") && line.endsWith("---")) ||
				 (line.startsWith(">>>") && line.endsWith("<<<")) ||
				 (line.startsWith(">>>") && line.endsWith(">>>")) ||
				 (line.startsWith("<<<") && line.endsWith("<<<")) ||				 
				 (line.startsWith("===") && line.endsWith("===")) ||				 
				 (line.startsWith("+++") && line.endsWith("+++")) ||				 
				 (line.startsWith("***") && line.endsWith("***")) ||
				 (line.startsWith("###") && line.endsWith("###")) ||				 
				 (line.startsWith("___") && line.endsWith("___")));				 
	}
}

/*
 * File dir = new File("directoryName");
 * 
 * String[] children = dir.list(); if (children == null) { // Either dir does
 * not exist or is not a directory } else { for (int i=0; i<children.length;
 * i++) { // Get filename of file or directory String filename = children[i]; }
 * }
 * 
 * // It is also possible to filter the list of returned files. // This example
 * does not return any files that start with `.'. FilenameFilter filter = new
 * FilenameFilter() { public boolean accept(File dir, String name) { return
 * !name.startsWith("."); } }; children = dir.list(filter);
 * 
 * 
 * // The list of files can also be retrieved as File objects File[] files =
 * dir.listFiles();
 * 
 * // This filter only returns directories FileFilter fileFilter = new
 * FileFilter() { public boolean accept(File file) { return file.isDirectory();
 * } }; files = dir.listFiles(fileFilter);
 */
