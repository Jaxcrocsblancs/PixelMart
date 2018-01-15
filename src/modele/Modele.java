package modele;
import java.io.File;

import modele.SeamCarving;

public class Modele {
	int[][] imageInit;
	int[][] imageModif;
	String nom;
	
	public Modele(){
		nom = "C:/Users/JaxCrocsBlanc/Desktop/PixelMart/ex1";
		imageInit = SeamCarving.readpgm(nom+".pgm");	
		imageModif = interest(imageInit);

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
	
	public int[][] getImageInit(){
		return imageInit;
	}
	
	public void setNom(String nom){
		this.nom = nom;
		imageInit = SeamCarving.readpgm(nom);
		imageModif = interest(imageInit);
	}
	
	public String getNom(){
		return nom;
	}

	public void writepgm(String nom) {
		SeamCarving.writepgm(imageInit,nom);
	}
}
