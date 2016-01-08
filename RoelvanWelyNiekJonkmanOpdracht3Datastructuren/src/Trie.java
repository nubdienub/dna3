
public class Trie<T> {
	private TrieNode<T> root;

	public Trie() {
		root = new TrieNode<T>();
	}

	
	public void insert(String word, T data) {
		
	}

	
	public T search(String prefix) {
		TrieNode<T> lastNode = root;

		// Probeer een node te vinden van de laatste letter van de prefix
		for (int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.vindNode(prefix.substring(i, i + 1));

			// geen node gevonden, null terug geven
			if (lastNode == null) {
				return null;
			}
		}

		return lastNode.getData();
	}

	
	public void delete(String prefix) {
	}

}
