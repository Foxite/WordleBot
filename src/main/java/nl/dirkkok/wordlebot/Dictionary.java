package nl.dirkkok.wordlebot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dictionary implements Serializable {
	private final String language;
	private final int wordLength;
	private final ArrayList<String> words;

	public Dictionary(String language, int wordLength) {
		this.language = language;
		this.wordLength = wordLength;
		this.words = new ArrayList<>();
	}

	public String getLanguage() {
		return language;
	}

	public int getWordLength() {
		return wordLength;
	}

	public List<String> getWords() {
		return words;
	}
}
