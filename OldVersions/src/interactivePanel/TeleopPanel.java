package interactivePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

/*
 * @author Kyle
 */
public class TeleopPanel extends JPanel implements KeyListener, ComponentListener {
    
	private static final long serialVersionUID = 1L;
	private final int maxCursor = 8;
    private Image screen;
    private int cursor;
    private final StackViewer sV;
    private Stack[] stacks;
    
    public TeleopPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 500));
        this.addKeyListener(this);
        this.addComponentListener(this);
        //this.setFocusable(true);
        
        sV = new StackViewer();
        
        Stack[] tempStacks = {
            new Stack(),new Stack(),new Stack(), new Stack(), 
            new Stack(),new Stack(),new Stack(), new Stack()
        }; 
        stacks = tempStacks;
        this.cursor = 0;
        
        sV.setDimensions(getSize());
        sV.setStacks(stacks);
        sV.setCursor(cursor);
        
        this.output();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(screen,0,0,this); 
    }
    
    public final void output() {
        screen = sV.getOutput(); 
        repaint();
    }
    
    public String getSummary() {
    	StringBuilder summaryBuilder = new StringBuilder();
    	
    	for(int i = 0; i<stacks.length; i++) {
    		summaryBuilder.append("|Stack" + i + ":" + stacks[i].getSummary());
    	}
    	
    	return summaryBuilder.toString();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int kCode = ke.getKeyCode();
        if(kCode == KeyEvent.VK_BACK_SPACE) {
            stacks[cursor].removeItem();

        } else if (kCode == KeyEvent.VK_LEFT) {
            if(cursor > 0)
                cursor--;
        } else if (kCode == KeyEvent.VK_RIGHT) {
            if(cursor < maxCursor)
                cursor++;
            
        } else if (kCode == KeyEvent.VK_B) {
            stacks[cursor].addItem(Stack.S_EMPTY);
            
        } else if (kCode == KeyEvent.VK_T) {
            stacks[cursor].addItem(Stack.S_GTOTE);
            
        } else if (kCode == KeyEvent.VK_C) {
            stacks[cursor].addItem(Stack.S_CONTAINER);
        } else if (kCode == KeyEvent.VK_F) {
            stacks[cursor].addItem(Stack.S_TGTOTE);
        }
        
        sV.setStacks(stacks);
        sV.setCursor(cursor);
        this.output();
    }

    public void reset() throws IOException {
    	Stack[] tempStacks = {
                new Stack(),new Stack(),new Stack(), new Stack(), 
                new Stack(),new Stack(),new Stack(), new Stack()
        }; 
        this.stacks = tempStacks;
        this.cursor = 0;
    }
    
    @Override
    public void componentResized(ComponentEvent ce) {
        sV.setDimensions(getSize());
        this.output();
    }
    
    //leftover keylistener methods
    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke) {}

    //leftover componentlistener methods
    @Override
    public void componentMoved(ComponentEvent ce) {}
    @Override
    public void componentShown(ComponentEvent ce) {}
    @Override
    public void componentHidden(ComponentEvent ce) {}
}
