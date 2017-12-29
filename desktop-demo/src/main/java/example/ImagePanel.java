package example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JComponent {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	public ImagePanel(){
		image = null;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		this.repaint();
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.BLACK);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(image != null){
			g2.drawImage(image, 10, 10, image.getWidth(), image.getHeight(),null);
		} 
		g2.setFont(new Font("Serif", Font.BOLD, 12));
		g2.setPaint(Color.WHITE);
		g2.drawString("CV4J Demo", 20, 20);
	}

}
