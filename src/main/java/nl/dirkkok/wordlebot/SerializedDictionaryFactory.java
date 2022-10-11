package nl.dirkkok.wordlebot;

import java.io.*;

public class SerializedDictionaryFactory implements DictionaryFactory {
	@Override
	public Dictionary getDictionary(String language, int length) throws IOException {
		try (var fs = new FileInputStream(getFileName(language, length))) {
			try (var serializer = new ObjectInputStream(fs)) {
				return (Dictionary) serializer.readObject();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void save(Dictionary dictionary) throws IOException {
		try (var fs = new FileOutputStream(getFileName(dictionary.getLanguage(), dictionary.getWordLength()))) {
			try (var serializer = new ObjectOutputStream(fs)) {
				serializer.writeObject(dictionary);
			}
		}
	}

	private String getFileName(String language, int length) {
		return "dictionary-%s-%d.obj".formatted(language, length);
	}
}
