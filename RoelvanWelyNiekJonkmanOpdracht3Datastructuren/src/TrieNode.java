import java.util.ArrayList;

public class TrieNode<T> implements Trie<Object> {

	private boolean isBlad,isRoot;
	private String karakter = "";
	private ArrayList<Object> data;
	private TrieNode<T> ouder;
	private ArrayList<TrieNode<T>> kinderen;
		
	/**
	 * Dit is de constructor
	 * zonder argumenten
	 */
	public TrieNode() {
		kinderen = new ArrayList<TrieNode<T>>();
		isBlad = true;
		isRoot = true;
	}
	
	/**
	 * Dit is de constructor
	 * @param data de data die er bij hoort
	 * @param karakter de karakter die er bij hoort
	 */
	public TrieNode(String karakter) {
		this.kinderen = new ArrayList<TrieNode<T>>();
		this.karakter = karakter;
		this.isBlad = true;
		this.data = new ArrayList<Object>();
	}
	
	/**
	 * Geeft de data terug 
	 * @return data
	 */
	public Object getData(){
		return this.data;
	}
	
	/**
	 * Geeft de karakter terug
	 * @return karakter
	 */
	public String getKarakter(){
		return this.karakter;
	}
	
	/**
	 * Kijkt of er een kind is die het karakter ook heeft
	 * Als dat zo is geeft deze die terug 
	 * anders null
	 * @param subKarakter
	 * @return
	 */
	public TrieNode<T> getKindMetKarakter(String subKarakter){
		for(TrieNode<T> kind : kinderen){
			if(kind.getKarakter().substring(0, 1).equals(subKarakter)){
				return kind;
			}
		}
		return null;
	}
	
	/**
	 * Kijkt of er nog kinderen zijn, als dat zo is
	 * dan ben je geen blad, anders wel
	 * @return
	 */
	public boolean controleerBlad(){
		if(kinderen.size() > 0){
			return false;
		}
		else if(isRoot){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Deze methode (van Evert) geeft een DOTString terug
	 * deze kan je gebruiken om je trie te testen
	 */
	public String toDOTString() {
		StringBuilder sb = new StringBuilder();
		sb.append("diagraph mijngraaf {\n");
		
		ArrayList<TrieNode<T>> lookup = new ArrayList<TrieNode<T>>();
		lookup.add(this);
		
		for (int i = 0; i < lookup.size(); i++) {
			TrieNode<T> currentNode = lookup.get(i);
			for (TrieNode<T> child : currentNode.kinderen) {
				if (!lookup.contains(child)){
					lookup.add(child);
				}
			}
		}
		
		for(TrieNode<T> node : lookup){
			sb.append(String.format("n%d [label=\"%s\"];\n", lookup.indexOf(node),node.karakter));
		}
		
		for(TrieNode<T> node : lookup){
			for (TrieNode<T> child : node.kinderen) {
				sb.append(String.format("n%d -> n%d;\n",lookup.indexOf(node),lookup.indexOf(child)));
			}
		}
		
		sb.append("}");
		
		return sb.toString();
	}

	/**
	 * Voegt woorden toe aan de trie structuur
	 * LET OP: Voegt nog niet data toe op de correcte manier, op het moment voegt die data toe bij het goede element
	 * op het moment van aanmaken, echter wanneer een element aangepast wordt en dus de data op een andere plek moet staan
	 * blijft deze op de oude plek staan.
	 * @param word het woord waar dat we in de trie willen stoppen
	 * @param data het object dat we bij het woord willen opslaan
	 */
	public void insert(String word, Object data) {
		
		isBlad = controleerBlad();

		TrieNode<T> kindMetKarakter = null;
		String subKarakter = word.substring(0,1);

		kindMetKarakter = getKindMetKarakter(subKarakter);
		
		if(kindMetKarakter == null && isBlad ){
			karakter += subKarakter;	
		}else if(kindMetKarakter == null){
			kindMetKarakter = new TrieNode<T>(subKarakter);
			kindMetKarakter.ouder = this;
			kinderen.add(kindMetKarakter);
		}else if(kindMetKarakter != null && kindMetKarakter.karakter.length() > 1){
			TrieNode<T> gespletenKindMetKarakter = new TrieNode<T>(kindMetKarakter.karakter.substring(1, kindMetKarakter.karakter.length()));
			kindMetKarakter.karakter = subKarakter;
			kindMetKarakter.kinderen.add(gespletenKindMetKarakter);
			gespletenKindMetKarakter.ouder = kindMetKarakter;
		}
		
		if(word.length() > 1){
			if(kinderen.size() > 0){
				kindMetKarakter.insert(word.substring(1), data);
			}else{
				insert(word.substring(1), data);
			}
		}else{
			if(kinderen.size() > 0){
				getKindMetKarakter(subKarakter).data.add(data);
			}else{
				this.data.add(data);
			}
		}
		
	}

	/**
	 * Zoekt het data object dat bij een woord hoort 
	 * en geeft dit terug
	 * LET OP: Houd geen rekening met dubbele woorden! (bijvoorbeeld 2x bal)
	 */
	public ArrayList<Object> search(String word) {		
		if(word.length() > 0){
			try {
				String subKarakter = word.substring(0,1);
				TrieNode<T> kindMetKarakter = getKindMetKarakter(subKarakter);
				
				if(kindMetKarakter.karakter.contains(subKarakter)){
					return kindMetKarakter.search(word.substring(1));
				}else{
					return data;
				}
			} catch (NullPointerException e) {
				return data;
			}	
		}else{
			return data;
		}	
	}

	public void delete(String word) {
		
		
	}
	



		
	
}
