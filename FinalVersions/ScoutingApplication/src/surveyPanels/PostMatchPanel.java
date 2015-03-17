package surveyPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class PostMatchPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JSlider coop;
	JSlider rigid;
	JTextArea comments;
	JTextField score;
	JLabel[] prompts = new JLabel[4];
	
	public PostMatchPanel() {	
		coop = new JSlider(JSlider.HORIZONTAL,0,10,5);
		coop.setMajorTickSpacing(10);
		coop.setMinorTickSpacing(1);
		coop.setPaintTicks(true);
		coop.setPaintLabels(true);
		
		rigid = new JSlider(JSlider.HORIZONTAL,0,10,5);
		rigid.setMajorTickSpacing(10);
		rigid.setMinorTickSpacing(1);
		rigid.setPaintTicks(true);
		rigid.setPaintLabels(true);
			
		comments = new JTextArea();
	
		score = new JTextField();
		//
		SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.add(prompts[0] = new JLabel("How well did they cooperate with allies"));
		this.add(coop);
        this.add(prompts[1] = new JLabel("How rigid was their robot"));
		this.add(rigid);
		this.add(prompts[2] = new JLabel("Final Match Score for your alliance"));
        this.add(score);
        this.add(prompts[3] = new JLabel("Comments:"));
		this.add(comments);
		

        //Totes moved constraints
        layout.putConstraint(SpringLayout.NORTH, prompts[0], 30, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, prompts[0], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, coop, 0, SpringLayout.SOUTH, prompts[0]);
        layout.putConstraint(SpringLayout.EAST, coop, 500, SpringLayout.WEST, coop);
        layout.putConstraint(SpringLayout.WEST, coop, 0, SpringLayout.WEST, this);
        
        //Totes stacked constraints
        layout.putConstraint(SpringLayout.NORTH, prompts[1], 30, SpringLayout.SOUTH, coop);
        layout.putConstraint(SpringLayout.WEST, prompts[1], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, rigid, 0, SpringLayout.SOUTH, prompts[1]);
        layout.putConstraint(SpringLayout.EAST, rigid, 500, SpringLayout.WEST, rigid);
        layout.putConstraint(SpringLayout.WEST, rigid, 0, SpringLayout.WEST, this);
        
        //Score
        layout.putConstraint(SpringLayout.NORTH, prompts[2], 30, SpringLayout.SOUTH, rigid);
        layout.putConstraint(SpringLayout.WEST, prompts[2], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, score, 0, SpringLayout.SOUTH, prompts[2]);
//        layout.putConstraint(SpringLayout.SOUTH, comments, 250, SpringLayout.SOUTH, prompts[2]);
        layout.putConstraint(SpringLayout.EAST, score, 500, SpringLayout.WEST, score);
        layout.putConstraint(SpringLayout.WEST, score, 0, SpringLayout.WEST, this);
        
        //comments
        layout.putConstraint(SpringLayout.NORTH, prompts[3], 30, SpringLayout.SOUTH, score);
        layout.putConstraint(SpringLayout.WEST, prompts[3], 0, SpringLayout.WEST, this);
               
        layout.putConstraint(SpringLayout.NORTH, comments, 0, SpringLayout.SOUTH, prompts[3]);
        layout.putConstraint(SpringLayout.SOUTH, comments, 250, SpringLayout.SOUTH, prompts[3]);
        layout.putConstraint(SpringLayout.EAST, comments, 500, SpringLayout.WEST, comments);
        layout.putConstraint(SpringLayout.WEST, comments, 0, SpringLayout.WEST, this);
	}
	
	public String getSummary() {
		StringBuilder summaryBuilder = new StringBuilder();
		
		summaryBuilder.append("|Cooperation: " + coop.getValue() + ' ');
		summaryBuilder.append("|Rigidity: " + rigid.getValue() + ' ');
		summaryBuilder.append("|MP: " + score.getText() + ' ');
		summaryBuilder.append("|Comments2: " + comments.getText() + ' ');
		
		return summaryBuilder.toString();
	}
	
	public void reset() {
		coop.setValue(5);
		rigid.setValue(5);
		score.setText("");
		comments.setText("");
	}
}
