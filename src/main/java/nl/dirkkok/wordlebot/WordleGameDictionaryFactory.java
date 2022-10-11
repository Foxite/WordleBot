package nl.dirkkok.wordlebot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;

public class WordleGameDictionaryFactory implements DictionaryFactory {
	private final HttpClient http;

	public WordleGameDictionaryFactory(HttpClient http) {
		this.http = http;
	}

	@Override
	public Dictionary getDictionary(String language, int length) throws IOException, InterruptedException {
		final URI uri;
		try {
			uri = new URI("https://wordlegame.org/files/wordle/%s/dictionary.json?v40.18".formatted(language));
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Invalid language", e);
		}

		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(uri)
				.build();

		HttpResponse<InputStream> response = http.send(request, HttpResponse.BodyHandlers.ofInputStream());
		InputStream responseBody = response.body();

		ObjectReader reader = new ObjectMapper().readerForArrayOf(String.class);
		String[] allWords = reader.readValue(responseBody);

		Dictionary ret = new Dictionary(language, length);

		for (String word : allWords) {
			if (word.length() == length) {
				ret.getWords().add(word);
			}
		}

		ret.getWords().sort(Comparator.naturalOrder());

		return ret;
	}
}
