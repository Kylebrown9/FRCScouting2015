package infoPanel;

import java.awt.GridLayout;

//import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
//import javax.swing.SpringLayout;

public class ManualInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField nameField = new JTextField(5);
    JTextField teamNField = new JTextField(5);
    JTextField matchNField = new JTextField(5);
	JPanel aColorPanel = new JPanel();
	JLabel aColorPrompt;
	private ButtonGroup aColorButtons;
	private JRadioButton aColorBlue;
	private JRadioButton aColorRed;
    
	public ManualInfoPanel() {
		this.setLayout(new GridLayout(10,1));
		this.setSize(400,400);
		this.add(aColorPanel);
		this.add(new JLabel("Please enter your name"));
		this.add(nameField);
		//this.add(Box.createHorizontalStrut(5)); // a spacer
		this.add(new JLabel("Please enter the observed teams number"));
		this.add(teamNField);
		//this.add(Box.createHorizontalStrut(5)); // a spacer
		this.add(new JLabel("Please enter the match number"));
		this.add(matchNField);
		//this.add(Box.createHorizontalStrut(5)); // a spacer

		aColorPrompt = new JLabel("What alliance are they on");
		aColorButtons = new ButtonGroup();
		aColorBlue = new JRadioButton("Blue");
		aColorRed = new JRadioButton("Red");
		aColorButtons.add(aColorRed);
		aColorButtons.add(aColorBlue);
		
		this.add(aColorPrompt);
		this.add(aColorBlue);
		this.add(aColorRed);
		
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public String[] getInformation() {
		String[] info = new String[4];
		
		info[0] = teamNField.getText();
		info[1] = matchNField.getText();
		info[2] = nameField.getText();
		
		if(aColorBlue.isSelected())
			info[3] = "Blue";
		else
			info[3] = "Red";
		
		return info;
	}
}
