package src.spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
   private int size;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
    public DictionaryBST() {
        dict = new TreeSet<String>();
    }
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
        if (!isValidWord(word))
            return false;

        word = word.toLowerCase();
        if (dict.contains(word))
            return false;

        dict.add(word);
        size++;
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return size;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String input) {
        if (!isValidWord(input))
            return false;

        return dict.contains(input.toLowerCase());
    }

    private boolean isValidWord(String word) {
        return word != null && !word.isEmpty();
    }
}
