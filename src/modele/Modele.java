package modele;
import java.util.Observable;
import java.util.ArrayList;
import modele.SeamCarving;

public class Modele  extends Observable{
	int[][] imageInit;
	int[][] imageModif;
	String nom;
	Boolean afficher;
	int tailleStylo;
	boolean stylo;
	boolean interet;
	int coordX;
	int coordY;
	
	public Modele(){
		nom = "src/image/ex1";
		imageInit = SeamCarving.readpgm(nom+".pgm");	
		imageModif = interest(imageInit);
		afficher = false;

		interet = true;
		stylo = true;
		//Graph g = tograph(imageInit);	
		//supprimeListePixel(imageInit, dijkstra(g,0,imageInit.length*imageInit[0].length+1));
	}
	
	public int[][] interest (int[][] image){
		int hauteur;
		int largeur;
		hauteur = image.length;
		largeur = image[0].length;
		int[][] rep = new int[hauteur][largeur];
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				if(j>0 && j<largeur-1){
					int moy = image[i][j-1]+image[i][j+1];
					moy = moy/2;
					moy = Math.abs(image[i][j]-moy);
					rep[i][j]=moy;
				}
				else if(j>0){
					int moy = image[i][j-1];
					moy = Math.abs(image[i][j]-moy);
					rep[i][j]=moy;
				}
				else{
					int moy = image[i][j+1];
					moy = Math.abs(image[i][j]-moy);
					rep[i][j]=moy;
				}
			}
		}
		update();
		return rep;		
	}
	
	public int[][] getImage(){
		if(afficher){
			return imageModif;
		}
		return imageInit;
	} 
	
	
	public void setNom(String nom){
		this.nom = nom;
		imageInit = SeamCarving.readpgm(nom);
		imageModif = interest(imageInit);
		update();
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setAfficher(){
		afficher = !afficher;
		update();
	}
	
	public boolean getAfficher() {
		return afficher;
	}
	
	public void writepgm(String nom) {
		SeamCarving.writepgm(imageInit,nom);
	}
	
	static Graph tograph(int[][] itr){
		int nbSommet = itr.length*itr[0].length+2;
		int cmp=1;
		Graph g = new Graph(nbSommet);
		for (int i =0;i<itr[0].length;i++){
			g.addEdge(new Edge(0, cmp, 0));
			g.addEdge(new Edge((nbSommet-1)-(itr[0].length-cmp)-1, nbSommet-1, itr[itr.length-1][cmp-1]));
			cmp++;
		}
		cmp=1;
		for (int i =0;i<itr.length-1;i++){
			for (int j=0;j<itr[0].length;j++)
			{
			g.addEdge(new Edge(cmp, cmp+itr[0].length, itr[i][j]));
			if (j>0)
				g.addEdge(new Edge(cmp, cmp+itr[0].length-1, itr[i][j]));
			if (j<itr[0].length-1)
				g.addEdge(new Edge(cmp, cmp+itr[0].length+1, itr[i][j]));
			cmp++;
			}
		}		
		return g;
	}

	public void update(){
		setChanged();
		notifyObservers();
		clearChanged();		
	}

	public ArrayList<Integer> dijkstra(Graph g, int s, int t){
		ArrayList<Integer> tabSommet = new ArrayList<>() ;
		int [] predecesseur = new int[g.getNBSommet()];
		int min ;
		/* init*/
		Heap f= new Heap(g.getNBSommet());
		f.decreaseKey(s ,0);
		

		while (!f.isEmpty()){
			min = f.pop();
			for(Edge e : g.adj(min)){
				if(f.priority(min) + e.getCost() < f.priority(e.getTo())){

					f.decreaseKey(e.getTo(), f.priority(min) + e.getCost());
					predecesseur[e.getTo()] = min;


				}
				
			}

		}
		
		
		min=t;

		min=predecesseur[min];
		while (min!=s){
			
			tabSommet.add(min);
			min=predecesseur[min];
		}
		return tabSommet;
	}
	
	
	public int nbLargeur(int nb, int largeur, int hauteur){
		int rep;
		rep = (nb-1)%largeur;
		return rep;
	}
	
	public int nbHauteur(int nb, int largeur, int hauteur){
		int rep;
		rep = (nb-1)/largeur;
		return rep;
	}
	
	public int[][] supprimeListePixel(int[][] image, ArrayList<Integer> L){
		int hauteur;
		int largeur;
		hauteur = image.length;
		largeur = image[0].length;
		int[][] rep = new int[hauteur][largeur-1];
		int supprL;
		for(int i= 0 ; i<hauteur ; i++ ){
			supprL = nbLargeur(L.get(L.size()-i-1),largeur,hauteur);
			for(int j = 0 ; j<largeur-1 ; j++){				
				if(j<supprL){
					rep[i][j]=image[i][j];
				}
				else{
					rep[i][j]=image[i][j+1];
				}
			}
		}
		printTab(rep);
		return rep;
		
	}
	
	void printTab(int[][] image){
		int hauteur;
		int largeur;
		hauteur = image.length;
		largeur = image[0].length;
		System.out.println("");
		
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				System.out.print(image[i][j]+"  ");
			}
			System.out.println("");
		}
	}
	
	public void modifInterest(int y, int x, int nb){
		if(y<imageModif[0].length && x<imageModif.length && y>0 && x>0){
			int som = imageModif[x][y]+nb;
			if(som<0){
				imageModif[x][y] = 0;
			}
			else if(som>255){
				imageModif[x][y] = 255;
			}
			else{
				imageModif[x][y] = som;
			}
			update();
		}
	}
	
	public int getTailleStylo() {
		return tailleStylo;
	}

	public void setTailleStylo(int tailleStylo) {
		this.tailleStylo = tailleStylo;
	}
	
	public boolean getInteret() {
		return interet;
	}

	public void setInteret(boolean interet) {
		this.interet = interet;
	}

	public int[][] getImageInit() {
		return imageInit;
	}

	public void setImageInit(int[][] imageInit) {
		this.imageInit = imageInit;
	}
	
	public int[][] getImageModif() {
		return imageModif;
	}

	public void setImageModif(int[][] imageModif) {
		this.imageModif = imageModif;
	}
	
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
		update();
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
		update();
	}
	
	public boolean getStylo() {
		return stylo;
	}

	public void setStylo(boolean stylo) {
		this.stylo = stylo;
	}
	//public static void main(String[] args)
	// {
	//	new Modele();
	// }

}
