package interactivePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * @author Kyle
 */
public class CoopStackViewer{
    
    private Stack stack;
    
    private Dimension dim;
    private boolean dimChanged;
    
    private final Graphics g;
    private final Image screen;
    
    private static final int PD_Width = 400;
    private static final int PD_Height = 680;
    private static int PD_X;
    private static final int PD_Y = 20;
    
    Image background;
    
    public CoopStackViewer() throws IOException {
        screen = new BufferedImage(1500, 1000, BufferedImage.TYPE_INT_ARGB);
        g = screen.getGraphics();
        background = ImageIO.read(getClass().getResource("/Resources/final/Background_test.jpg"));
    }
    
    public Image getOutput() {
        if(dimChanged)
            drawBG(g);
        
        drawPD(g);
        drawMB(g);
        
        dimChanged = false;
        
        return screen;
    }
    
    public void setStacks(Stack temp) {
        stack = temp;
    }
    
    public void setDimensions(Dimension d) {
        dim = d;
        dimChanged = true;
        PD_X = (dim.width-PD_Width)/2;
    }
    
    //*********************Primary Display*************************************************************
    public void drawPD(Graphics g) {
        drawPDBackground(g);
        stack.draw(g, PD_X, PD_Y+PD_Height);
    }
    
    public void drawPDBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(PD_X,PD_Y,PD_Width,PD_Height);
    }
    
    //*********************Mini Bar******************************************************************
    public  void drawMB(Graphics g) {
        drawMBBackground(g);
        drawMBOutline(g);    
        drawMBThumbnail(g);
    }
    
    public void drawMBOutline(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(5,dim.height-95,90,5);
        g.fillRect(5,dim.height-90,5,80);
        g.fillRect(90,dim.height-90,5,80);
        g.fillRect(5,dim.height-10,90,5);
       
    }
    
    public void drawMBThumbnail(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("T: "+Integer.toString(stack.getCounts()[Stack.S_YTOTE]), 15, dim.height-75);
        g.drawString("O:"+Integer.toString(stack.getCounts()[Stack.S_TYTOTE]), 15, dim.height-55);
    }
    
    public void drawMBBackground(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,dim.height-100,dim.width,100);
    }
    
    //*********************Background********************************************************************
    public void drawBG(Graphics g) {
        g.setColor(Color.BLACK);    
        g.drawImage(background, 0, 0, null);
    }
}