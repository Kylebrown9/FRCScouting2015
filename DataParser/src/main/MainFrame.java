package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JLabel prompt;
	private ButtonGroup teamSelection;
	public JRadioButton allTeams;
	public JRadioButton oneTeam;
	JTextField numberF;
	
	private JRadioButton op0;
	private JRadioButton op1;
	private JRadioButton op2;
	private JRadioButton op3;
	private JRadioButton op4;
	private JRadioButton op5;
	private JRadioButton op6;
	
	Runnable compile;
	JButton compileButton;
	
	private MainFrame(String title, Runnable c) {
		super(title);
		
		compile = c;
		
		prompt = new JLabel("Data Selection");
		
		teamSelection = new ButtonGroup();
		allTeams = new JRadioButton("All Teams");
		oneTeam = new JRadioButton("Team Number:");
		teamSelection.add(allTeams);
		teamSelection.add(oneTeam);
		
		numberF = new JTextField();
		
		op0 = new JRadioButton("Team Name");
		op1 = new JRadioButton("Match Number");
		op2 = new JRadioButton("Tote Total");
		op3 = new JRadioButton("Container Total");
		op4 = new JRadioButton("Max Stack Height");
		op5 = new JRadioButton("Stacks");
		op6 = new JRadioButton("Comments");

		
		compileButton = new JButton("Compile");
		
		this.setLayout(new GridLayout(20,1));
		this.add(prompt);
		this.add(allTeams);
		this.add(oneTeam);
		this.add(numberF);
		this.add(Box.createVerticalStrut(9));
		this.add(op0);
		this.add(op1);
		this.add(op2);
		this.add(op3);
		this.add(op4);
		this.add(op5);
		this.add(op6);
		this.add(Box.createVerticalStrut(9));
		this.add(compileButton);
		
		oneTeam.setSelected(true);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setFocusable(true);
		this.pack();
	}
	
	public static MainFrame create(String title, Runnable c) {
		MainFrame mF = new MainFrame(title, c);
		mF.compileButton.addActionListener(mF);
		mF.allTeams.addActionListener(mF);
		mF.oneTeam.addActionListener(mF);
		return mF;
	}
	
	public String getTeam() {
		if(oneTeam.isSelected())
			return numberF.getText();
		else
			return "";
	}
	
	public String getConfig() {
		StringBuilder out = new StringBuilder();
		
		if(op0.isSelected())
			out.append("1");
		else
			out.append("0");
		
		if(op1.isSelected())
			out.append("1");
		else
			out.append("0");
		
		if(op2.isSelected())
			out.append("1");
		else
			out.append("0");
		
		if(op3.isSelected())
			out.append("1");
		else
			out.append("0");
		
		if(op4.isSelected())
			out.append("1");
		else
			out.append("0");
		
		if(op5.isSelected())
			out.append("1");
		else
			out.append("0");
		
		if(op6.isSelected())
			out.append("1");
		else
			out.append("0");
		
		return out.toString();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(compileButton))
			compile.run();
		else if (arg0.getSource().equals(allTeams))
			numberF.setEnabled(false);
		else if (arg0.getSource().equals(oneTeam))
			numberF.setEnabled(true);
	}
}