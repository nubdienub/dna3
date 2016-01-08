
public class Apl {

	public static void main(String[] args) {
		TrieNode<String> root = new TrieNode<String>();
			
		root.insert("ball", "het einde van ball");
		root.insert("bat", "het einde van bat");
		root.insert("doll", "het einde van doll");
		root.insert("dork", "het einde van dork");
		root.insert("do", "het einde van do");
		root.insert("dorm", "het einde van dorm");
		root.insert("send", "het einde van send");
		root.insert("sense", "het einde van sense");

		
		System.out.println("Ik zoek data van bat: " + root.search("bat"));
		System.out.println("Ik zoek data van dorm: " + root.search("dorm"));	
		
		System.out.println(root.toDOTString());
	}

}
