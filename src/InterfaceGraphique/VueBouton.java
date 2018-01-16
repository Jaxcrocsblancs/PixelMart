package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import modele.Modele;

public class VueBouton extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Modele m ;
	int[][] image;
	
	public VueBouton(Modele m) {
		super();
		this.m = m;
		m.addObserver(this);
		JButton supprColonne = new JButton("-Colonne");
		this.add(supprColonne);
		JButton supprLigne = new JButton("-Ligne");
		this.add(supprLigne);
		JButton addColonne = new JButton("+Colonne");
		this.add(addColonne);
		JButton addLigne = new JButton("+Ligne");
		this.add(addLigne);
		JButton interet = new JButton("Interet");
		interet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				m.setAfficher();
				if(!m.getAfficher()){
					interet.setText("Interet");
				}
				else{
					interet.setText("Image");
				}
			}
		});
		this.add(interet);
		
		
		
		
		
	}

	public void update(Observable o, Object arg) {
		
	
	}
}
