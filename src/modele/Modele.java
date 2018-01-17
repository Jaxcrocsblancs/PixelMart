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
		Graph g = tograph(imageInit);	
		supprimeListePixel(imageInit, dijkstra(g,0,imageInit.length*imageInit[0].length+1));
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
		System.out.println("bite");
		ArrayList<Integer> tabSommet =new ArrayList<>();
		ArrayList<Integer> tab = new ArrayList<>();
		int[][] cout =new int[g.getNBSommet()][2];
		
		for (int i = 0 ;i<cout.length;i++){
			cout[i][0]=999999999;
		}
		cout[s][0]=0;
		cout[s][1]=0;
		int min = s;
		tab.add(min);
		int nb=0;
		while (tab.size()!=g.getNBSommet()){
			for(Edge e:g.adj(min)){
				int to = e.getTo();
				if (cout[to][0]>cout[e.getFrom()][0]+e.getCost()){
					cout[to][0]=cout[e.getFrom()][0]+e.getCost();
					cout[to][1]=e.getFrom();
				}
			}
			min = mincout(cout,tab);
			tab.add(min);
			nb +=1;
			System.out.println(nb);
		}
		min = t;
		min = cout[min][1];
		while (min!=s){
			tabSommet.add(min);
			min = cout[min][1];
		}
		
		
		return tabSommet;
	}
	
	private int mincout(int[][] cout, ArrayList<Integer> tab) {
		// TODO Auto-generated method stub
		int min =0;
		for (int i =1 ;i<cout.length;i++){
			if ((cout[i][0]<cout[i-1][0])||tab.contains(i-1)){
				min =i;
			}
		}
		return min;
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
