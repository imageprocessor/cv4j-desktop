package cv4j.desktop.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.cv4j.core.datamodel.CV4JImage;
import com.cv4j.core.filters.ColorFilter;


public class ClientApp extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImagePanel imagepanel;
	private JButton browseBtn;
	private JButton okBtn;
	public ClientApp() {
		imagepanel = new ImagePanel();
		browseBtn = new JButton("Browse...");
		okBtn = new JButton("Process");
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.add(browseBtn);
		btnPanel.add(okBtn);
		
		// layout UI View
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(imagepanel, BorderLayout.CENTER);
		this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		// setup listener
		browseBtn.addActionListener(this);
		okBtn.addActionListener(this);
		
		// size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		setSize(d.width-100, d.height - 80);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == browseBtn) {
				File f = getFilePath();
				if(f.isDirectory())return;
				BufferedImage image = ImageIO.read(f);
				imagepanel.setImage(image);
			} else if(e.getSource() == okBtn){
				ColorFilter filter = new ColorFilter();
				CV4JImage model = new CV4JImage(imagepanel.getImage());
				filter.filter(model.getProcessor());
				imagepanel.setImage(model.toBitmap());
			}
		}catch(IOException ioe) {
			
		}
		
	}
	
	public void showUI() {
		// settings
		centre(this);
		this.setTitle("CV4J Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void centre(Window w) {
	    Dimension us = w.getSize();
	    Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
	    int newX = (them.width - us.width) / 2;
	    int newY = (them.height - us.height) / 2;
	    w.setLocation(newX, newY);
	}
	
	public static void main(String[] args) {
		try {
			String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
			ClientApp ui = new ClientApp();
			ui.showUI();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private File getFilePath() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(ClientApp.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		} else {
			return null;
		}
	}
}
