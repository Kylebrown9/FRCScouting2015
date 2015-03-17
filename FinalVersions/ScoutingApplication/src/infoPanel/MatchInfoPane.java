//No longer in use
package infoPanel;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MatchInfoPane extends JFrame {

	private static final long serialVersionUID = 1L;
	JTextField nameField = new JTextField(5);
    JTextField teamNField = new JTextField(5);
    JTextField matchNField = new JTextField(5);
	
	public MatchInfoPane() {
		super("Set Information");
		
		this.setLayout(new GridLayout(8,1));
		this.setSize(400,300);
		
		this.add(new JLabel("Please enter your name"));
		this.add(nameField);
		this.add(Box.createHorizontalStrut(15)); // a spacer
		this.add(new JLabel("Please enter the observed teams number"));
		this.add(teamNField);
		this.add(Box.createHorizontalStrut(15)); // a spacer
		this.add(new JLabel("Please enter the match number"));
		this.add(matchNField);

		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public String getUsersName() {
		return nameField.getText();
	}
	
	public String getTeamNumber() {
		return teamNField.getText();
	}
	
	public String getMatchNumber() {
		return matchNField.getText();
	}
}
