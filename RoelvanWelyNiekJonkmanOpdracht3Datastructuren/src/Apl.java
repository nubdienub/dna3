
public class Apl {

	public static void main(String[] args) {
		TrieNode<String> root = new TrieNode<String>();
		
		root.woordToevoegen("hoi", "onzin");
		root.woordToevoegen("home", "onzin");
		
		System.out.println(root.toString());
	}

}
