package nl.dirkkok.wordlebot;

import java.util.Arrays;
import java.util.Optional;

public record WordleState(char[] LettersRuledOut, Optional<Character>[] KnownLetters, char[][] PositionsRuledOut, String[] IgnoredWords) {
	public boolean wordMatches(String word) {
		if (Arrays.asList(IgnoredWords).contains(word)) {
			return false;
		}

		for (char ruledOut : LettersRuledOut) {
			for (int i = 0; i < word.length(); i++) {
				var letter = word.charAt(i);
				var known = KnownLetters[i];
				if (known.isPresent() && known.get() == letter) {
					continue;
				}
				if (letter == ruledOut) {
					return false;
				}
			}
		}

		for (int i = 0; i < KnownLetters.length; i++) {
			Optional<Character> known = KnownLetters[i];
			if (known.isPresent() && word.charAt(i) != known.get()) {
				return false;
			}
		}

		for (char[] ruledOutAtPosition : PositionsRuledOut) {
			for (char ruledOut : ruledOutAtPosition) {
				if (!letterOccursAtCandidatePositionInWord(ruledOut, word)) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean letterOccursAtCandidatePositionInWord(char letter, String word) {
		for (int i = 0; i < word.length(); i++) {
			var known = KnownLetters[i];
			if ((known.isEmpty() || known.get() == letter) && !letterIsRuledOutForPosition(letter, i) && word.charAt(i) == letter) {
				return true;
			}
		}
		return false;
	}

	private boolean letterIsRuledOutForPosition(char letter, int position) {
		for (int i = 0; i < PositionsRuledOut[position].length; i++) {
			if (PositionsRuledOut[position][i] == letter) {
				return true;
			}
		}
		return false;
	}
}
