package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import modele.Modele;

public class VueCentral extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Modele m ;
	int[][] image;
	
	public VueCentral(Modele m) {
		super();
		this.m = m;
	}

	public void paintComponent(Graphics g){
		    image = m.getImageInit();
		    for(int i=0; i<image.length;i++){
		    	for(int j=0;j<image[0].length;j++){
		    		g.setColor(new Color(image[i][j],image[i][j],image[i][j]));
		    		g.drawLine(j,i,j,i);
		    	}
		    }
	}               

	public void update(Observable o, Object arg) {
		
	
	}
}
