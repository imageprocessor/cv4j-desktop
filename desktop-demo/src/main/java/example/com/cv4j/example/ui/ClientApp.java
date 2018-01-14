package example.com.cv4j.example.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.cv4j.core.datamodel.CV4JImage;
import com.cv4j.core.datamodel.ImageProcessor;
import com.cv4j.core.filters.FastEPFilter;

import example.com.cv4j.example.controller.DefaultController;
import example.com.cv4j.example.controller.EventHandler;
import example.com.cv4j.example.model.EventData;
import example.com.cv4j.example.model.MenuConstants;
import example.com.cv4j.example.model.MenuItemsFactory;


public class ClientApp extends JFrame implements EventHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImagePanel imagepanel;
	private JButton browseBtn;
	private JButton okBtn;
	public ClientApp() {
		imagepanel = new ImagePanel();
		
		// layout UI View
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(imagepanel, BorderLayout.CENTER);
		this.getContentPane().add(createMenus(), BorderLayout.NORTH);
		
		// size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		setSize(d.width-100, d.height - 80);
	}

	private JMenuBar createMenus() {
		// setup listener
		DefaultController controller = new DefaultController(this);
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		List<String> fileMenuItems = MenuItemsFactory.initFileMenuItems();
		for(String s : fileMenuItems) {
			JMenuItem item = new JMenuItem(s);
			item.addActionListener(controller);
			fileMenu.add(item);
		}
		JMenu filterMenu = new JMenu("Filter");
		List<String> filterMenuItems = MenuItemsFactory.initFilterMenuItems();
		for(String s : filterMenuItems) {
			JMenuItem item = new JMenuItem(s);
			item.addActionListener(controller);
			filterMenu.add(item);
		}

		mb.add(fileMenu);
		mb.add(filterMenu);
		return mb;
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

	@Override
	public void handleEvent(EventData e) {
		try {
			if(e.getFileOpen(MenuConstants.FILE_OPEN)) {
				File f = getFilePath();
				if(f.isDirectory())return;
				BufferedImage image = ImageIO.read(f);
				imagepanel.setImage(image);
			} else if(MenuConstants.FastEPFilter.equals(e.getString(MenuConstants.FastEPFilter))){
				CV4JImage model = new CV4JImage(imagepanel.getImage());
				FastEPFilter epf = new FastEPFilter();
				ImageProcessor processor = epf.filter(model.getProcessor());
				CV4JImage result = new CV4JImage(processor);
				//FileOutputStream fos = new FileOutputStream(newImgFile);
				//ImageIO.write(temp, "jpg",fos);
				//fos.close();
				BufferedImage temp = result.toBitmap();
				imagepanel.setImage(temp);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
