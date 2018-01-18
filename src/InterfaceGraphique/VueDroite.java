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

public class VueDroite extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Modele m ;
	int tailleStylo;
	
	public VueDroite(Modele m){
		super();
		this.m = m;
		m.addObserver(this);
		tailleStylo = 10;
		final JSlider slide = new JSlider() ;
		slide.setMinimum(0);
		slide.setMaximum(20);
		slide.setMajorTickSpacing(5);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		
		Hashtable<Integer,JLabel> l = new Hashtable<>();
		l.put(0,new JLabel("0"));
		l.put(10,new JLabel("10"));
		l.put(20,new JLabel("20"));
		slide.setLabelTable(l);
		
		slide.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				
			}
		});

		this.add(slide);
		
		JButton moreInteret = new JButton("+Interet");
		moreInteret.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		this.add(moreInteret);
		JButton lessInteret = new JButton("-Interet");
		lessInteret.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		this.add(lessInteret);
		JButton styloActivite = new JButton("Stylo/OFF");
		styloActivite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		this.add(styloActivite);
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
