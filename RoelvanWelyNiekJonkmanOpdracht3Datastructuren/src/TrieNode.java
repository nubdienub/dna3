import java.util.ArrayList;

public class TrieNode<T> {

	private boolean isBlad;
	private String karakter;
	private T data;
	private TrieNode<T> ouder;
	private ArrayList<TrieNode<T>> kinderen;
		
	/**
	 * Dit is de constructor
	 * zonder argumenten
	 */
	public TrieNode() {
		kinderen = new ArrayList<TrieNode<T>>();
		isBlad = true;
	}
	
	/**
	 * Dit is de constructor
	 * @param data de data die er bij hoort
	 * @param karakter de karakter die er bij hoort
	 */
	public TrieNode(T data,String karakter) {
		this.kinderen = new ArrayList<TrieNode<T>>();
		this.karakter = karakter;
		this.isBlad = true;
		this.data = data;	
	}
	
	/**
	 * Geeft de data terug 
	 * @return data
	 */
	public T getData(){
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
	 * Gaat een woord toevoegen aan tree/trie
	 * @param woord het woord dat wordt toegevoegd 
	 * @param data de extra data die wordt toegevoegd
	 */
	public void woordToevoegen(String woord,T data){
		this.isBlad = false;
		
		TrieNode<T> kindMetKarakter = null;
		String karakter = woord.substring(0,1);
		
		for(TrieNode<T> kind : kinderen){
			if(kind.getKarakter() == karakter){
				kindMetKarakter = kind;
			}
		}
		
		if(kindMetKarakter == null){
			kindMetKarakter = new TrieNode<T>(data,karakter);
			kindMetKarakter.ouder = this;
			kinderen.add(kindMetKarakter);
		}
		
		if(woord.length() > 1){
			kindMetKarakter.woordToevoegen(woord.substring(1), data);
		}else{
			kindMetKarakter.data = data;
		}			
	}
	
	public void woordVerwijderen(String woord){
		
		if(kinderen.size() > 0){	
			for(int i = 0; i < kinderen.size(); i++) {
				TrieNode<T> kindMetWoord = kinderen.get(i);
				if(kindMetWoord.karakter.equals(woord)){
					kindMetWoord = null;
					kinderen.remove(i);
					isBlad = kinderen.size() == 0;
					break;
				}
			}		
		}
		
		if(isBlad) {
			ouder.woordVerwijderen(woord);
		}
	}
	
	@Override
	public String toString() {
		String result = "digraph heap {\n";
		return result + toDot() + "\n}";
	}
	
	private String toDot() {
		String res = " [label=\"" + karakter + " data" + "\"]\n";
		for (TrieNode<T> aChild : kinderen) {
			res += aChild.toDot();
		}
		return res;
	}
	
	
	
}
