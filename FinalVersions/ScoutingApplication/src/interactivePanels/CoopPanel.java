package interactivePanels;

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
public class CoopPanel extends JPanel implements KeyListener, ComponentListener {
    
	private static final long serialVersionUID = 1L;
    private Image screen;
    private final CoopStackViewer sV;
    private Stack stack;
    
    public CoopPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 500));
        this.addKeyListener(this);
        this.addComponentListener(this);
        //this.setFocusable(true);
        
        sV = new CoopStackViewer();

        stack = new Stack();
        
        sV.setDimensions(getSize());
        sV.setStacks(stack);
        
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
    	
    	summaryBuilder.append("|CoopStack" + ":" + stack.getSummary());
    	
    	return summaryBuilder.toString();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int kCode = ke.getKeyCode();
        if(kCode == KeyEvent.VK_BACK_SPACE) {
            stack.removeItem();
        } else if (kCode == KeyEvent.VK_T) {
            stack.addItem(Stack.S_YTOTE);
        } else if (kCode == KeyEvent.VK_F) {
            stack.addItem(Stack.S_TYTOTE);
        }
        
        sV.setStacks(stack);
        this.output();
    }

    public void reset() throws IOException {
    	stack = new Stack();
        this.output();
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
