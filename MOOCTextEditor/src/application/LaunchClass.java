package src.application;

import java.util.Random;


public class LaunchClass {
	
	public String dictFile = "data/dict.txt";
	
	public LaunchClass() {
		super();
	}
	
	public src.document.Document getDocument(String text) {
		// Change this to BasicDocument(text) for week 1 only
		return new src.document.EfficientDocument(text);
	}
	
	public src.textgen.MarkovTextGenerator getMTG() {
		return new src.textgen.MarkovTextGeneratorLoL(new Random());
	}
	
	public src.spelling.WordPath getWordPath() {
		return new src.spelling.WPTree();
	}
	
    public src.spelling.AutoComplete getAutoComplete() {
        src.spelling.AutoCompleteDictionaryTrie tr = new src.spelling.AutoCompleteDictionaryTrie();
		src.spelling.DictionaryLoader.loadDictionary(tr, dictFile);
        return tr;
    }
    
    public src.spelling.Dictionary getDictionary() {
		src.spelling.Dictionary d = new src.spelling.DictionaryBST();
		src.spelling.DictionaryLoader.loadDictionary(d, dictFile);
    	return d;
    }
    
    public src.spelling.SpellingSuggest getSpellingSuggest(src.spelling.Dictionary dic) {
    	//return new spelling.SpellingSuggestNW(new spelling.NearbyWords(dic));
    	return new src.spelling.NearbyWords(dic);
    
    }
}
