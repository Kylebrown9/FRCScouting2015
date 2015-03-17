package scoutingapplication;

import interactivePanels.TeleopPanel;

import java.awt.Frame;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sidebar.SideBar;
import surveyPanels.AutoPanel;

public class ScoutingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel label;
	
	TeleopPanel tP;
	AutoPanel aP;
	JTabbedPane tabs;
	
	SideBar sBar;
	
	// constructor
	public ScoutingFrame(String title, Runnable submit) throws IOException {
		super(title);                      // invoke the JFrame constructor
	  
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setVisible(true);
	    
		tabs = new JTabbedPane();
		tabs.setFocusable(true);
		
		aP = new AutoPanel();
		aP.setFocusable(true);
		
		tP = new TeleopPanel();
		tP.setFocusable(true);
	    
		sBar = new SideBar(submit);
		
	    this.setLayout(new GridLayout(1,2));
	    this.add(tabs);
	    this.add(sBar);
	    
	    tabs.addTab("Auton", aP);
	    tabs.addTab("Teleop", tP);
	}

	public String getSummary() {
		StringBuilder summaryBuilder = new StringBuilder();
		
		summaryBuilder.append("MatchData:");
		summaryBuilder.append(aP.getSummary());
		summaryBuilder.append(", ");
		summaryBuilder.append(tP.getSummary());
		
		return summaryBuilder.toString();
	}
	
	public String[] getInformation() {
		return sBar.getInformation();
	}
	
	public void reset() {
		
	}
} 