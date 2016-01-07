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
	 * Kijkt of er een kind is die het karakter ook heeft
	 * Als dat zo is geeft deze die terug 
	 * anders null
	 * @param subKarakter
	 * @return
	 */
	public TrieNode<T> getKindMetKarakter(String subKarakter){
		for(TrieNode<T> kind : kinderen){
			if(kind.getKarakter() == subKarakter){
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
		}else{
			return true;
		}
	}
	
	
	/**
	 * Gaat een woord toevoegen aan tree/trie
	 * @param woord het woord dat wordt toegevoegd 
	 * @param data de extra data die wordt toegevoegd
	 */
	public void woordToevoegen(String woord,T data){
		
		//Check of het niet t laatste node is van een woord
		isBlad = controleerBlad();
		
		TrieNode<T> kindMetKarakter = null;
		String subKarakter = woord.substring(0,1);
		
		//Kijk of er kinderen zijn die het karakter al bevatten
		//Als dat zo is dan sla je de node van dat kind op
		kindMetKarakter = getKindMetKarakter(subKarakter);
	
		//Als het karakter nog niet bekend is en je bent bij een blad, dan wordt het karakter toegevoegd
		//Als het karakter nog niet bekend is maar je bent niet bij een blad
		//maak dan een nieuwe node met de data en karakter(s)
		if(kindMetKarakter == null && isBlad ){
			karakter += subKarakter;
		}else if(kindMetKarakter == null){
			kindMetKarakter = new TrieNode<T>(data,subKarakter);
			kindMetKarakter.ouder = this;
			kinderen.add(kindMetKarakter);
		}else if(kindMetKarakter != null && karakter.length() > 1){
			//het kind met karakter moet nu alleen komen te staan
			//en de rest van de karakters worden een nieuwe node
			
		}
		
		//Als het woord nog een lengte heeft meer dan 1 dan zijn
		//er nog karakters om te verdelen.
		if(woord.length() > 1){
			kindMetKarakter.woordToevoegen(woord.substring(1), data);
		}else{
			kindMetKarakter.data = data;
		}			
	}
	
	public void woordVerwijderen(String woord){
		

	}
		
	
}
