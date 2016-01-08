
public class Apl {

	public static void main(String[] args) {
		TrieNode<String> root = new TrieNode<String>();
		
		root.woordToevoegen("ball", "1");
		root.woordToevoegen("bat", "2");
		root.woordToevoegen("doll", "3");
		root.woordToevoegen("dork", "4");
		root.woordToevoegen("do", "5");
		root.woordToevoegen("dorm", "6");
		root.woordToevoegen("send", "7");
		root.woordToevoegen("sense", "8");

		System.out.println(root.toDOTString());
	}

}
