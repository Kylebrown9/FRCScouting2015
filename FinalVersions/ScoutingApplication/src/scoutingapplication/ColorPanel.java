package scoutingapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Color c;
	Dimension size;
	
	public ColorPanel() {
		c = Color.BLUE;
	}
	
	public void setColor(Color C) {
		c = C;
		repaint();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        size = getSize();
        g.setColor(c);
        g.fillRect(0, 0, size.width, size.height);
    }
}
