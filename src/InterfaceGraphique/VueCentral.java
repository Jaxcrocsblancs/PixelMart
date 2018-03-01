package InterfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	
	
	public VueCentral(final Modele m) {
		super();
		this.m = m;
		this.setPreferredSize(new Dimension(m.getImageModif()[0].length,m.getImageModif().length));
		m.addObserver(this);
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if(m.getStylo()){
					int nb;
					if(m.getInteret()){
						nb = 128;
					}
					else{
						nb = -128;
					}
					for (int i=-m.getTailleStylo();i<=m.getTailleStylo(); i++){
						for(int j = -m.getTailleStylo(); j<=m.getTailleStylo(); j++){
							if( Math.round(Math.sqrt(i*i + j*j)) <= m.getTailleStylo())
								m.modifInterest(arg0.getX()+ i,arg0.getY()+j, nb);
							
						}
					}
					m.setCoordX(arg0.getX());
					m.setCoordY(arg0.getY());
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(m.getStylo()){
					int nb;
					if(m.getInteret()){
						nb = 128;
					}
					else{
						nb = -128;
					}
					for (int i=-m.getTailleStylo();i<=m.getTailleStylo(); i++){
						for(int j = -m.getTailleStylo(); j<=m.getTailleStylo(); j++){
							if( Math.round(Math.sqrt(i*i + j*j)) <= m.getTailleStylo())
								m.modifInterest(arg0.getX()+ i,arg0.getY()+j, nb);
							
						}
					}
					m.setCoordX(arg0.getX());
					m.setCoordY(arg0.getY());
				}
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(m.getStylo()){
					m.setCoordX(arg0.getX());
					m.setCoordY(arg0.getY());
				}
			}
			
		});
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int[][] image;
		    image = m.getImage();
		    for(int i=0; i<image.length;i++){
		    	for(int j=0;j<image[0].length;j++){
		    		g.setColor(new Color(image[i][j],image[i][j],image[i][j]));
		    		g.drawLine(j,i,j,i);
		    	}
		    }
		    if(m.getStylo()){
			    for (int i=-m.getTailleStylo();i<=m.getTailleStylo(); i++){
					for(int j = -m.getTailleStylo(); j<=m.getTailleStylo(); j++){
						if(j+m.getCoordX()>0 && i+m.getCoordY()>0 && j+m.getCoordX()<image[0].length && i+m.getCoordY()<image.length){
							if( Math.round(Math.sqrt(i*i + j*j)) == m.getTailleStylo()){
								if((i+j)%2==0){
									g.setColor(new Color(0,0,0));
								}
								else{
									g.setColor(new Color(255,255,255));
								}
					    		g.drawLine(j+m.getCoordX(),i+m.getCoordY(),j+m.getCoordX(),i+m.getCoordY());
							}
						}
					}
				}	
		    }
	}               

	public void update(Observable o, Object arg) {
		this.setSize(new Dimension(m.getImageModif()[0].length,m.getImageModif().length));
		repaint();
		
	}
}
