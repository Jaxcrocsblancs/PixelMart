import java.awt.Dimension;

import javax.swing.JFrame;

import InterfaceGraphique.VueBarMenu;
import InterfaceGraphique.VueCentral;
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
		this.setPreferredSize(new Dimension(500,500));
		m = new Modele();
		
		VueCentral vueCentre = new VueCentral(m);
		this.setContentPane(vueCentre);     
		VueBarMenu vueMenu = new VueBarMenu(m);
		this.setJMenuBar(vueMenu) ;
        pack();
        setVisible(true);
	}

	public static void main(String[] args) {
		new PixelMart() ;
    }
}

