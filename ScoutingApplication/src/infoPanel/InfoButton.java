package infoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class InfoButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	MatchInfoFrame infoPane;
	Runnable submit;
	
	
	private InfoButton() {
		super("Set Information");
		this.setFocusable(true);
		this.setVisible(true);
		this.addActionListener(this);
		
		infoPane = new MatchInfoFrame();
		infoPane.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		infoPane.setVisible(true);
	}
	
	public String[] getInformation() {
		String[] info = new String[3];
		
		info[0] = infoPane.getTeamNumber();
		info[1] = infoPane.getMatchNumber();
		info[2] = infoPane.getUsersName();
		
		return info;
	}
	
	public static InfoButton setupInfoButton() {
		InfoButton iB = new InfoButton();
		iB.addActionListener(iB);
		return iB;
	}
}