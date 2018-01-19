import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import InterfaceGraphique.VueBarMenu;
import InterfaceGraphique.VueBouton;
import InterfaceGraphique.VueCentral;
import InterfaceGraphique.VueDroite;
import modele.Modele;

public class PixelMart extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Modele m;
	
	public PixelMart(){
		super("PixelMart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setPreferredSize(new Dimension(900,500));
		m = new Modele();		
		VueCentral vueCentre = new VueCentral(m);
		this.add(vueCentre,BorderLayout.CENTER);
		VueBarMenu vueMenu = new VueBarMenu(m);
		this.setJMenuBar(vueMenu) ;
		VueBouton vueBouton = new VueBouton(m);
		this.add(vueBouton,BorderLayout.SOUTH);
		VueDroite vueDroite = new VueDroite(m);
		this.add(vueDroite,BorderLayout.EAST);
        pack();
        setVisible(true);
	}

	public static void main(String[] args) {
		new PixelMart() ;
    }
}

