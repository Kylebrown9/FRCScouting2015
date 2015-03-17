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
public class StackViewer{
    
    private Stack[] stacks;
    private int cursor = 0;
    
    private Dimension dim;
    private boolean dimChanged;
    
    private final Graphics g;
    private final Image screen;
    
    private static final int PD_Width = 400;
    private static final int PD_Height = 680;
    private static int PD_X;
    private static final int PD_Y = 20;
    
    Image background;
    
    public StackViewer() throws IOException {
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
    
    public void setStacks(Stack[] temp) {
        stacks = temp;
    }
    
    public void setCursor(int Cursor) {
        cursor = Cursor;
    }
    
    public void setDimensions(Dimension d) {
        dim = d;
        dimChanged = true;
        PD_X = (dim.width-PD_Width)/2;
    }
    
    //*********************Primary Display*************************************************************
    public void drawPD(Graphics g) {
        drawPDBackground(g);
        stacks[cursor].draw(g, PD_X, PD_Y+PD_Height);
    }
    
    public void drawPDBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(PD_X,PD_Y,PD_Width,PD_Height);
    }
    
    //*********************Mini Bar******************************************************************
    public  void drawMB(Graphics g) {
        drawMBBackground(g);
        drawMBOutline(g);
        
        for(int i=0; i<stacks.length; i++) {
            drawMBThumbnail(g, i);
        }
    }
    
    public void drawMBOutline(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(5+100*cursor,dim.height-95,90,5);
        g.fillRect(5+100*cursor,dim.height-90,5,80);
        g.fillRect(90+100*cursor,dim.height-90,5,80);
        g.fillRect(5+100*cursor,dim.height-10,90,5);
       
    }
    
    public void drawMBThumbnail(Graphics g, int pos) {
        g.setColor(Color.BLACK);
        g.drawString("T: "+Integer.toString(stacks[pos].getCounts()[2]), pos*100+15, dim.height-75);
        g.drawString("C:"+Integer.toString(stacks[pos].getCounts()[6]), pos*100+15, dim.height-55);
        g.drawString("O:"+Integer.toString(stacks[pos].getCounts()[4]), pos*100+15, dim.height-30);
    }
    
    public void drawMBBackground(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,dim.height-100,dim.width,100);
    }
    
    //*********************Background********************************************************************
    public void drawBG(Graphics g) {
        g.setColor(Color.BLACK);
        //g.fillRect(0, 0, dim.width, dim.height-100);
        
        g.drawImage(background, 0, 0, null);
    }
}