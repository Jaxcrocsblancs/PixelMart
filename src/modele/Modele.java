package modele;
import java.io.File;
import java.util.Observable;
import java.util.ArrayList;
import modele.SeamCarving;

public class Modele  extends Observable{
	int[][] imageInit;
	int[][] imageModif;
	String nom;
	Boolean afficher;
	public Modele(){
		nom = "src/image/ex1";
		imageInit = SeamCarving.readpgm(nom+".pgm");	
		imageModif = interest(imageInit);
		afficher = false;

		printTab(imageInit);
		Graph g = tograph(imageModif);
		g.writeFile("test.dot");
		//supprimeListePixel(imageInit, dijkstra(g,0,1));
	}
	
	int[][] interest (int[][] image){
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
	
	
	private int mincout(int[][] cout, ArrayList<Integer> tab) {
		// TODO Auto-generated method stub
		int min = 9999999;
		int sommet = -1;
		for (int i =0 ;i<cout.length;i++){
			if ((cout[i][0]<min)){
				min =cout[i][0];
				sommet = i;
			}
		}
		return sommet;
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
	
	 public static void main(String[] args)
	 {
		
		new Modele();
	 }

}
