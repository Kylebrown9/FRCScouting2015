package infoPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scoutingapplication.ColorPanel;

public class LookupInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	String allianceColor;
	String teamNumString;
	String teamNameString;
	
	String[] teamName;
	String[] teamNumber;
	
	JTextField nameField;
	JTextField matchNum;
	JButton setButton;
	
	JLabel outputTeam;
	ColorPanel outputAlliance;
	
	private LookupInfoPanel() {
		nameField = new JTextField();
		matchNum = new JTextField("1");
		setButton = new JButton("Set");
		outputTeam = new JLabel("team info filler");
		outputAlliance = new ColorPanel();
		
		allianceColor = file2("C:/ScoutingApp2015/Configuration/AllianceColor.txt");
		teamNumString = file2("C:/ScoutingApp2015/Configuration/TeamNumbers.txt");
		teamNameString = file2("C:/ScoutingApp2015/Configuration/TeamNames.txt");
		
		teamName = teamNameString.split(System.lineSeparator());
		teamNumber = teamNumString.split(System.lineSeparator());
		
		this.setLayout(new GridLayout(7,1));
		this.add(new JLabel("Observers Name:"));
		this.add(nameField);
		this.add(Box.createHorizontalStrut(15));
		this.add(matchNum);
		this.add(setButton);
		this.add(outputTeam);
		this.add(outputAlliance);
		
		this.update();
	}
	
	public static LookupInfoPanel create() {
		LookupInfoPanel lIP = new LookupInfoPanel();
		lIP.setButton.addActionListener(lIP);
		return lIP;
	}
	
	public String[] getInformation() {
		String[] info = new String[4];
		int mNum = getMatchNum();
		
		info[0] = teamNumber[mNum-1];
		info[1] = matchNum.getText();
		info[2] = nameField.getText();
		info[3] = allianceColor;
		
		return info;
	}
	
	private void update() {
		int mNum = getMatchNum();
		if(mNum >= teamNumber.length)
			return;
		
		outputTeam.setText(teamNumber[mNum-1] + " - " + teamName[mNum-1]);
		
		Color c;
		
		switch(allianceColor) {
		case "BLUE": c = Color.BLUE; break;
		case "RED": c = Color.RED; break;
		default: c = Color.RED;
		}
		
		outputAlliance.setColor(c);
	}
	
	private void incrementMatch() {
		int mNum = getMatchNum();
		
		mNum = (mNum == 0) ? mNum : mNum+1;
		
		matchNum.setText(String.valueOf(mNum));
	}
	
	public int getMatchNum() {
		int mNum;
		
		try{
			mNum = Integer.parseInt(matchNum.getText());
		} catch (NumberFormatException nmf) {
			mNum = 0;
		};
		
		return mNum;
	}

	public void reset() {
		incrementMatch();
		update();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
	}
	
	public String file2(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return " ";
	}
}
