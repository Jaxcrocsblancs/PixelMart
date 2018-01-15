package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import modele.Modele;

public class VueBarMenu extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Modele m ;
	
	public VueBarMenu(Modele m) {
		super();
		this.m = m;
		JMenuItem ouvrir = new JMenuItem("Ouvrir") ;
		
		ouvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Fichiers pgm.", "pgm");
                chooser.addChoosableFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setDialogTitle("Importation d'une liste de modules");
			    int retour = chooser.showOpenDialog(getParent());
			    if(retour == JFileChooser.APPROVE_OPTION) {
			    	//chooser.getSelectedFile().getName();    
			    	m.setNom(chooser.getSelectedFile().getAbsolutePath());
				} else ; 
			}
		});
		this.add(ouvrir) ;
		
		JMenuItem enregistrer = new JMenuItem("Enregistrer");
		enregistrer.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent arg0) {
						m.writepgm(m.getNom()+"Modifier");
	          }
			
		});
		this.add(enregistrer);
		
	}
}
