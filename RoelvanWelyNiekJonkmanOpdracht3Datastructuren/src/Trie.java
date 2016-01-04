public class Trie<T> {

	private TrieNode<T> root;

	public Trie() {
		root = new TrieNode<T>();
	}
	
	
	/**
	 * Voegt een woord aan de Trie toe.
	 * @param word Woord om toe te voegen
	 */
	public void insert(String word, T data) {
		if(word != null && !word.isEmpty() && word.matches("[a-zA-Z]+")) {
			root.insert(word.toLowerCase(), data);
		}
	}
	
	
	/**
	 * Doorzoekt de Trie met een bepaalde prefix
	 * @param prefix TrieNode prefix
	 * @return De data van de gevonden TrieNode, of null
	 */
	public T search(String prefix) {
		TrieNode<T> lastNode = root;
		
		// Proberen een node te vinden van de laatste letter van de prefix
		for(int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.findNode(prefix.substring(i, i+1));
			
			// Indien er geen node gevonden is: null terug geven
			if(lastNode == null) {
				return null;
			}
		}
		
		return lastNode.getData();
	}
	

}
