package surveyPanels;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class AutoPanel extends JPanel {
	
	static final long serialVersionUID = 1L;
	ButtonGroup autoMove;
	JRadioButton autoMoveYes;
	JRadioButton autoMoveNo;
	JSlider totes;
	JSlider totesStacked;
	JSlider containers;
	JTextArea comments;
	JLabel[] prompts;
	
	public AutoPanel() {
		autoMove = new ButtonGroup();
		autoMoveYes = new JRadioButton("Yes");
		autoMoveNo = new JRadioButton("No");
		autoMove.add(autoMoveNo);
		autoMove.add(autoMoveYes);
		
		prompts = new JLabel[5];
		
		totes = new JSlider(JSlider.HORIZONTAL,0,3,0);
		totes.setMajorTickSpacing(10);
		totes.setMinorTickSpacing(1);
		totes.setPaintTicks(true);
		totes.setPaintLabels(true);
		
		
		totesStacked = new JSlider(JSlider.HORIZONTAL,0,3,0);
		totesStacked.setMajorTickSpacing(10);
		totesStacked.setMinorTickSpacing(1);
		totesStacked.setPaintTicks(true);
		totesStacked.setPaintLabels(true);
		
		
		containers = new JSlider(JSlider.HORIZONTAL,0,3,0);
		containers.setMajorTickSpacing(10);
		containers.setMinorTickSpacing(1);
		containers.setPaintTicks(true);
		containers.setPaintLabels(true);
		
		
		comments = new JTextArea();
		
		
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.add(prompts[0] = new JLabel("Did they move in autonomous"));
		this.add(autoMoveYes);
		this.add(autoMoveNo);
        this.add(prompts[1] = new JLabel("How many totes did they move to the center"));
		this.add(totes);
        this.add(prompts[2] = new JLabel("How many totes did they stack"));
		this.add(totesStacked);
        this.add(prompts[3] = new JLabel("How many containers did they move to the middle"));
		this.add(containers);
        this.add(prompts[4] = new JLabel("Comments:"));
		this.add(comments);
        
		//Automove layout constraints
        layout.putConstraint(SpringLayout.NORTH, prompts[0], 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, prompts[0], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, autoMoveYes, 0, SpringLayout.SOUTH, prompts[0]);
        layout.putConstraint(SpringLayout.WEST, autoMoveYes, 0, SpringLayout.WEST, this);
        
        layout.putConstraint(SpringLayout.NORTH, autoMoveNo, 0, SpringLayout.SOUTH, prompts[0]);
        layout.putConstraint(SpringLayout.WEST, autoMoveNo, 0, SpringLayout.EAST, autoMoveYes);
       
        //Totes moved constraints
        layout.putConstraint(SpringLayout.NORTH, prompts[1], 30, SpringLayout.SOUTH, autoMoveNo);
        layout.putConstraint(SpringLayout.WEST, prompts[1], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, totes, 0, SpringLayout.SOUTH, prompts[1]);
        layout.putConstraint(SpringLayout.EAST, totes, 250, SpringLayout.WEST, totes);
        layout.putConstraint(SpringLayout.WEST, totes, 0, SpringLayout.WEST, this);
        
        //Totes stacked constraints
        layout.putConstraint(SpringLayout.NORTH, prompts[2], 30, SpringLayout.SOUTH, totes);
        layout.putConstraint(SpringLayout.WEST, prompts[2], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, totesStacked, 0, SpringLayout.SOUTH, prompts[2]);
        layout.putConstraint(SpringLayout.EAST, totesStacked, 250, SpringLayout.WEST, totesStacked);
        layout.putConstraint(SpringLayout.WEST, totesStacked, 0, SpringLayout.WEST, this);
        
        //Containers moved constraints
        layout.putConstraint(SpringLayout.NORTH, prompts[3], 30, SpringLayout.SOUTH, totesStacked);
        layout.putConstraint(SpringLayout.WEST, prompts[3], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, containers, 0, SpringLayout.SOUTH, prompts[3]);
        layout.putConstraint(SpringLayout.EAST, containers, 250, SpringLayout.WEST, containers);
        layout.putConstraint(SpringLayout.WEST, containers, 0, SpringLayout.WEST, this);
        
        //comments
        layout.putConstraint(SpringLayout.NORTH, prompts[4], 30, SpringLayout.SOUTH, containers);
        layout.putConstraint(SpringLayout.WEST, prompts[4], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, comments, 0, SpringLayout.SOUTH, prompts[4]);
        layout.putConstraint(SpringLayout.SOUTH, comments, 250, SpringLayout.SOUTH, prompts[4]);
        layout.putConstraint(SpringLayout.EAST, comments, 500, SpringLayout.WEST, comments);
        layout.putConstraint(SpringLayout.WEST, comments, 0, SpringLayout.WEST, this);
        
        this.setVisible(true);
		
	}
	
	public String getSummary() {
		StringBuilder summaryBuilder = new StringBuilder();
		
		summaryBuilder.append("|AutoMove: ");
		
		if(autoMoveYes.isSelected())
			summaryBuilder.append("yes");
		else if(autoMoveNo.isSelected())
			summaryBuilder.append("no");
		else
			summaryBuilder.append("no");
		
		summaryBuilder.append("|Totes: " + totes.getValue() + ' ');
		summaryBuilder.append("|TotesStacked: " + totesStacked.getValue() + ' ');
		summaryBuilder.append("|Containers: " + containers.getValue() + ' ');
		summaryBuilder.append("|Comments1: " + comments.getText() + ' ');
		
		return summaryBuilder.toString();
	}
	
	public void reset() {
		totes.setValue(0);
		containers.setValue(0);
		totesStacked.setValue(0);
		comments.setText("");
		autoMove.clearSelection();
	}
}
