package sidebar;

import infoPanel.LookupInfoPanel;
//import infoPanel.ManualInfoPanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class SideBar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	Runnable submit;
	SubmitButton submitButton;
	//InfoButton informationButton;
//	ManualInfoPanel iP;
	LookupInfoPanel iP;
	String matchNumber;
	String teamNumber;
	String observeName;
	
	public SideBar(Runnable s) {
		submit = s;
		submitButton = new SubmitButton(s);
		//informationButton = InfoButton.setupInfoButton();
		iP = LookupInfoPanel.create();
		this.setLayout(new GridLayout(3,1));
		this.add(new LogoPanel());
		this.add(iP);
		this.add(submitButton);
	}
	
	public String[] getInformation() {
		return iP.getInformation();
	}

	public void reset() {
		iP.reset();
	}
}
