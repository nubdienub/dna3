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
	
	/**
	 * Verwijder methode die eerst checkt of deze node kinderen heeft
	 * zo ja: haal elk kind op en maak deze null (maak isBlad als kinderen.size 0 is)
	 * zo nee: is het een blad? Vraag het dus aan zijn parent (1tje omhoog) (recursief)
	 * @param s > te verwijderen woord
	 */
	public void woordVerwijderen(String s){
		System.out.println("Te verwijderen: " + s);
		// Zoeken naar het te verwijderen character
		if(kinderen.size() > 0){ 
			for(int i = 0; i < kinderen.size(); i++) {
				TrieNode<T> kind = kinderen.get(i);
				if(kind != null && kind.kinderen.equals(s)) {
					System.out.println("kind " + s + " wordt null!");
					kind = null;
					kinderen.remove(i);
					isBlad = kinderen.size() == 0;
					break;
				}
				
				//TODO iets met isWord hier nog?
			}
		}
		
		// Indien blad, vraag parent om zich te verwijderen
		if(isBlad) {
			ouder.woordVerwijderen(karakter);
		}

	}
	
	/**
	 * Zoekt s in de kinderen van de huidige node, zodra deze s gelijk is aan karakter van kind,
	 * return dit kind (node)
	 * @param s
	 * @return het kind
	 */
	public TrieNode<T> vindNode(String s) {
		for(TrieNode<T> kind : kinderen) {
			if(s != null && kind.karakter != null && s.equals(kind.karakter)) {
				return kind;
			}
		}
		return null;
	}
	
	
		
	
}
