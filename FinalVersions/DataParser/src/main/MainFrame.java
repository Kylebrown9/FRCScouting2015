package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	Runnable compile;
	JButton compileButton;
	JLabel prompt;
	JTextField numberF;
	
	private MainFrame(String title, Runnable c) {
		super(title);
		
		compile = c;
		prompt = new JLabel("Team Number:");
		numberF = new JTextField();
		compileButton = new JButton("Compile");
		
		this.setLayout(new GridLayout(3,1));
		this.add(prompt);
		this.add(numberF);
		this.add(compileButton);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setFocusable(true);
		this.pack();
	}
	
	public static MainFrame create(String title, Runnable c) {
		MainFrame mF = new MainFrame(title, c);
		mF.compileButton.addActionListener(mF);
		return mF;
	}
	
	public String getTeam() {
		System.err.println(numberF.getText());
		return numberF.getText();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		compile.run();
	}
}