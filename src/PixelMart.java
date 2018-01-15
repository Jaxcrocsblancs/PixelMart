
import java.awt.BorderLayout;
import javax.swing.JFrame;

import InterfaceGraphique.VueCentral;
import modele.Modele;

public class PixelMart extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Modele m;
	
	PixelMart(){
		super("PixelMart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		m = new Modele();
		this.setPreferedSize(1000, 1500);
		VueCentral vueCentre = new VueCentral(m);
		
		this.setContentPane(vueCentre);     	
        pack();
        setVisible(true);
	}

    private void setPreferedSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new PixelMart() ;
    }
}

