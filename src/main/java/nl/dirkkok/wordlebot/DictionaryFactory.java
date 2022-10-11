package nl.dirkkok.wordlebot;

import java.io.IOException;

public interface DictionaryFactory {
	Dictionary getDictionary(String language, int length) throws IOException, InterruptedException;
}
