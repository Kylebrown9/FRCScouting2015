package scoutingapplication;

import interactivePanel.TeleopPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sidebar.SideBar;
import surveyPanels.AutoPanel;
import surveyPanels.PostMatchPanel;

public class MainFrame extends JFrame implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
	TeleopPanel tP;
	AutoPanel aP;
	PostMatchPanel pMP;
	JTabbedPane tabs;
	
	SideBar sBar;
	
	private MainFrame(Runnable submit) throws IOException {
	    super("SApp_V4.0");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		BufferedImage image1 = ImageIO.read(getClass().getResource("/Resources/final/Clipboard.png"));
		this.setIconImage(image1);
		
		//JTabbedPane setup********************
		tabs = new JTabbedPane();
		tabs.setFocusable(true);
		
	    //AutoPanel setup
		aP = new AutoPanel();
		aP.setFocusable(true);
		
		//TeleopPanel setup
		tP = new TeleopPanel();
		tP.setFocusable(true);
	
		//Post Match panel
		pMP = new PostMatchPanel();
		pMP.setFocusable(true);
		
		//Add panels to the JTabbedPane
		tabs.addTab("Auton", aP);
	    tabs.addTab("Teleop", tP);
	    tabs.addTab("Post-Match", pMP);
		
		//SideBar setup*************************
		sBar = new SideBar(submit);
		sBar.setMaximumSize(new Dimension(250,1000));
	    
		//Layout setup*****************************************************************************
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        contentPane.add(tabs);
        contentPane.add(sBar);
        
        layout.putConstraint(SpringLayout.NORTH, sBar, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, sBar, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, sBar, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, sBar, -250, SpringLayout.EAST, contentPane);
               
        layout.putConstraint(SpringLayout.NORTH, tabs, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, tabs, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, tabs, -10, SpringLayout.WEST, sBar);
        layout.putConstraint(SpringLayout.WEST, tabs, 0, SpringLayout.WEST, contentPane);
        
        this.pack();
        this.setVisible(true);
	}
	
	public static MainFrame create(Runnable r) throws IOException {
		MainFrame mF = new MainFrame(r);
		mF.tabs.addChangeListener(mF);
		return mF;
	}
	
	//Returns a string with all of the match data
	public String getSummary() {
		StringBuilder summaryBuilder = new StringBuilder();
		
		summaryBuilder.append(aP.getSummary());
		summaryBuilder.append(tP.getSummary());
		summaryBuilder.append(pMP.getSummary());
		
		return summaryBuilder.toString();
	}
	
	//Passes the sidebar's information
	public String[] getInformation() {
		return sBar.getInformation();
	}
	
	//Gets the application window ready for the next match
	public void reset() {
		aP.reset();
		try {
			tP.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pMP.reset();
		sBar.reset();
	}
	
	
	//Change Listener method******************************************************************************************************
	public void stateChanged(ChangeEvent e) {
        if (1 == tabs.getSelectedIndex())
        	 tP.requestFocus();
    }
}