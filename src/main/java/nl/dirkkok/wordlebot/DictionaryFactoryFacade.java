package nl.dirkkok.wordlebot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;

public class DictionaryFactoryFacade {
	public Dictionary getDictionary(String language, int length) throws IOException, InterruptedException {
		Dictionary dictionary;
		SerializedDictionaryFactory serializedDictionaryFactory = new SerializedDictionaryFactory();
		try {
			dictionary = serializedDictionaryFactory.getDictionary(language, length);
		} catch (FileNotFoundException e) {
			DictionaryFactory httpFactory = new WordleGameDictionaryFactory(HttpClient.newHttpClient());
			dictionary = httpFactory.getDictionary(language, length);
			serializedDictionaryFactory.save(dictionary);
		}

		return dictionary;
	}
}
