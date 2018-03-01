package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.Modele;

public class VueBouton extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Modele m ;
	int[][] image;
	JLabel titre;
	
	public VueBouton(Modele m) {
		super();
		this.m = m;
		m.addObserver(this);
		
		titre = new JLabel("Nombre: 10",JLabel.CENTER);
		this.add(titre);
		final JSlider slide = new JSlider() ;
		slide.setMinimum(0);
		slide.setMaximum(50);
		slide.setMajorTickSpacing(5);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		Hashtable<Integer,JLabel> l = new Hashtable<>();
		l.put(0,new JLabel("0"));
		l.put(10,new JLabel("10"));
		l.put(20,new JLabel("20"));
		l.put(30,new JLabel("30"));
		l.put(40,new JLabel("40"));
		l.put(50,new JLabel("50"));
		slide.setLabelTable(l);
		slide.setValue(10);
		m.setTailleStylo(slide.getValue());
		slide.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				m.setNbCon(slide.getValue());
			}
		});
		this.add(slide);
		
		JButton supprColonne = new JButton("-Colonne");
		supprColonne.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<m.getNbCon();i++){
					if(m.getImage()[0].length>0)
						m.supprColonne();
				}
			}
			
		});
		this.add(supprColonne);
		/*//Attente des fonctions dans le sens de la largeur
		JButton supprLigne = new JButton("-Ligne");
		this.add(supprLigne);
		//Attente deusième partie
		JButton addColonne = new JButton("+Colonne");
		this.add(addColonne);
		//Attente deusième partie
		JButton addLigne = new JButton("+Ligne");
		this.add(addLigne);*/
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
		titre.setText("Nombre: "+m.getNbCon());
	
	}
}
