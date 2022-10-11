package nl.dirkkok.wordlebot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Optional;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		final String language = "en";
		final int length = 6;

		var factoryFacade = new DictionaryFactoryFacade();
		var dictionary = factoryFacade.getDictionary(language, length);

		WordGenerator wordGenerator = new WordGenerator(dictionary);

		var state = new WordleState(
				new char[] {
				},
				new Optional[]{
						Optional.<Character>empty(),
						Optional.<Character>empty(),
						Optional.<Character>empty(),
						Optional.<Character>empty(),
						Optional.<Character>empty(),
				},
				new char[][] {
						new char[] {
						},
						new char[] {
						},
						new char[] {
						},
						new char[] {
						},
						new char[] {
						},
				},
				new String[] {
						"cacks",
						"cacky",
				}
		);
		String word = wordGenerator.getNextWord(state);

		System.out.println(word);
	}
}
