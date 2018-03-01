package InterfaceGraphique;

import java.awt.GridLayout;
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

public class VueDroite extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Modele m ;
	
	public VueDroite(final Modele m){
		super();
		this.m = m;
		m.addObserver(this);
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);

		final JSlider slide = new JSlider() ;
		slide.setMinimum(0);
		slide.setMaximum(20);
		slide.setMajorTickSpacing(5);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		
		JLabel titre = new JLabel("Taille du Stylo",JLabel.CENTER);
		this.add(titre);
		Hashtable<Integer,JLabel> l = new Hashtable<>();
		l.put(0,new JLabel("0"));
		l.put(10,new JLabel("10"));
		l.put(20,new JLabel("20"));
		slide.setLabelTable(l);
		slide.setValue(10);
		m.setTailleStylo(slide.getValue());
		slide.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				m.setTailleStylo(slide.getValue());
			}
		});
		this.add(slide);
		
		
		final JButton interet = new JButton("+Interet");
		interet .addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				m.setInteret(!m.getInteret());
				if(m.getInteret()){
					interet.setText("+Interet");
				}
				else{
					interet.setText("-Interet");
				}
			}
		});
		
		this.add(interet);

		final JButton styloActivite = new JButton("Stylo/ON");
		styloActivite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				m.setStylo(!m.getStylo());
				if(m.getStylo()){
					styloActivite.setText("Stylo/ON");
				}
				else{
					styloActivite.setText("Stylo/OFF");
				}
			}
		});
		this.add(styloActivite);

		JButton reInit = new JButton("Reinitialisation");
		reInit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				m.setImageModif(m.interest(m.getImageInit()));
			}
		});
		this.add(reInit);
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
