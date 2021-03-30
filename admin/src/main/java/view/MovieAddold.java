//package view;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.net.URI;
//import java.net.URL;
//
//import javax.swing.ImageIcon;
//import javax.swing.JPanel;
//import javax.swing.OverlayLayout;
//import javax.swing.JButton;
//import java.awt.BorderLayout;
//
//public class MovieAddold extends JPanel {
//
//	Image image;
//	/**
//	 * Create the panel.
//	 */
//	public MovieAddold() {
//
//		JButton btnNewButton = new JButton("New button");
//		add(btnNewButton);
//		
//
////		this.setVisible(true);
//		
//		URL url = getClass().getResource("/image/movieadd.png");
//		ImageIcon imageIcon= new ImageIcon(url);
//		image=imageIcon.getImage();
//
//		 
//		this.setLayout(new OverlayLayout(this));
////		this.setLayout(null);
//		MovieAdd movieAdd = new MovieAdd(image);
//		movieAdd.setLayout(new BorderLayout());
//		this.add(movieAdd);
//		movieAdd.setOpaque(false);
//		
//		
//		MovieAdmin.menuFrame.getContentPane().add(this);
//		MovieAdmin.menuFrame.validate();
//		MovieAdmin.menuFrame.repaint();
//
//	}
//	@Override
//	public boolean isOptimizedDrawingEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	
//
//}
