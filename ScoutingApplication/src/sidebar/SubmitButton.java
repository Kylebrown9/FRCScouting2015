package sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SubmitButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	Runnable submit;
	
	public SubmitButton(Runnable s) {
		super("Submit");
		submit = s;
		this.setFocusable(true);
		this.setVisible(true);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
    	//Verify match submition*****************************************************************
		
		int n = JOptionPane.showConfirmDialog(
    		    new JFrame(),
    		    "Would you like to submit the match data?",
    		    "Confirm match submition",
    		    JOptionPane.YES_NO_OPTION);
    	
    	if(n != JOptionPane.YES_OPTION)
    		return;
    	
		submit.run();
	}
}
