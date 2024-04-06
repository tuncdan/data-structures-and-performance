package src.textgen;

import java.util.*;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
//	private List<ListNode> wordList;

	private HashMap<String, ListNode> wordList;
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;

//	private boolean isTrained;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
//		wordList = new LinkedList<ListNode>();
		wordList = new HashMap<String, ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if (sourceText == null || sourceText.isEmpty()) {
			return;
		}

		String[] words = sourceText.split("\\s+");
		if (words.length == 0) {
			return;
		}

		starter = words[0];
		AddOrUpdate(starter);
		String prevWord = starter;
		String currentWord = "";

		for (int i = 1; i < words.length - 1; i++) {
			currentWord = words[i];
			AddOrUpdate(currentWord);
			wordList.get(prevWord).addNextWord(currentWord);
			prevWord = currentWord;
		}

		wordList.get(currentWord).addNextWord(starter);
//		isTrained = true;
	}

	private ListNode AddOrUpdate(String word) {
		ListNode node;
		if (wordList.containsKey(word)) {
			return wordList.get(word);
		}

		node = new ListNode(word);
		wordList.put(word, node);
		return node;
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
//		if (!isTrained) {
//			throw new IllegalArgumentException("The generator is not trained yet.");
//		}

		if (numWords == 0 || wordList.isEmpty()) {
			return "";
		}

		String currentWord = starter;
		StringBuilder output = new StringBuilder();

		while (numWords > 0) {
			output.append(currentWord);
			output.append(" ");
			ListNode node = wordList.get(currentWord);
			currentWord = node.getRandomNextWord(rnGenerator);
			numWords--;
		}

		return output.toString();
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder toReturn = new StringBuilder();
		for (String word : wordList.keySet())
		{
			toReturn.append(word);
		}
		return toReturn.toString();
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		clear();
		train(sourceText);
	}

	private void clear() {
		wordList.clear();
		starter = "";
	}

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
	    // the MarkovTextGeneratorLoL class
		Integer index = generator.nextInt(nextWords.size());
		return nextWords.get(index);
	}

	@Override
	public String toString()
	{
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(word);
		toReturn.append(": ");
		for (String s : nextWords) {
			toReturn.append(s);
			toReturn.append("->");
		}
		toReturn.append("\n");
		return toReturn.toString();
	}
}


