package example;

import com.cv4j.core.datamodel.CV4JImage;
import com.cv4j.core.datamodel.ImageProcessor;
import com.cv4j.core.datamodel.Scalar;
import com.cv4j.core.pixels.NormRotate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


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
				CV4JImage model = new CV4JImage(imagepanel.getImage());
				NormRotate rtt = new NormRotate();
				ImageProcessor processor = rtt.rotate(model.getProcessor(), 45, new Scalar(0, 0, 255));
				CV4JImage result = new CV4JImage(processor);
		    	File newImgFile = new File("D:\\kkkkk.jpg");
		    	FileOutputStream fos = new FileOutputStream(newImgFile);
		    	BufferedImage temp = result.toBitmap();
				ImageIO.write(temp, "jpg",fos);
				fos.close();
				imagepanel.setImage(temp);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
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
			// 适配当前平台
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
