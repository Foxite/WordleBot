package nl.dirkkok.wordlebot;

import java.util.LinkedList;

public class WordGenerator {
	private final LinkedList<String> words;

	public WordGenerator(Dictionary dictionary) {
		this.words = new LinkedList<>(dictionary.getWords());
	}

	public String getNextWord(WordleState state) {
		words.removeIf(word -> !state.wordMatches(word));

		return words.peekFirst();
	}
}
