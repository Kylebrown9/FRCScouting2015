package interactivePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stack {
    
    public static final int S_EMPTY = 0;
	public static final int S_STEP = 1;
    public static final int S_GTOTE = 2;
    public static final int S_YTOTE = 3;
    public static final int S_TGTOTE = 4;
    public static final int S_TYTOTE = 5;
    public static final int S_CONTAINER = 6;
    public static final int S_NOODLE = 7;

    BufferedImage[] images;
    
    int[] stack = {0,0,0,0,0,0,0,0};
    int[] counts = {0,0,0,0,0,0,0};
    
    int[] yOffset = {100,100,100,100,100,50};
    
    int length = 0;
    
    public Stack() throws IOException {
    	BufferedImage image1 = ImageIO.read(getClass().getResource("/Resources/final/gtote.png"));
        BufferedImage image2 = ImageIO.read(getClass().getResource("/Resources/final/ytote.png"));
        BufferedImage image3 = ImageIO.read(getClass().getResource("/Resources/final/tgtote.png"));
        BufferedImage image4 = ImageIO.read(getClass().getResource("/Resources/final/tytote.png"));
        BufferedImage image5 = ImageIO.read(getClass().getResource("/Resources/final/gcontainer.png"));
        //BufferedImage image6 = ImageIO.read(new File("Resource\\noodle.png"));
        
        BufferedImage[] temp = {image1, image2, image3, image4, image5};
        
        images = temp;
    }
    
    public void addItem(int itemID) {
        if(itemID == S_STEP && !(length == 0))
            return;
            
        if(itemID == S_NOODLE && stack[length-1] != S_CONTAINER)
            return;
        
        if(length > 6)
        	return;
        
        stack[length] = itemID;
        counts[itemID]++;
        length++;
    }
    
    public void removeItem() {
    	if(length == 0)
    		return;
    	
        counts[stack[length-1]]--;
        stack[length-1] = S_EMPTY;
        length--;
    }
    
    public void draw(Graphics g, int x, int y) {
        int imageID, yCursor = y - 100;
        
        for (int i=0; i<length; i++) {
            imageID = stack[i];
            
            if(!(imageID < 2)) {
                yCursor -= yOffset[imageID-2];
                g.drawImage(images[imageID-2],x+30,yCursor,null);
            }
        }
    }
    
    public int[] getCounts() {
    	return counts;
    }
    
    public String getSummary() {
    	StringBuilder summaryBuilder = new StringBuilder();
    	
    	for(int i=0;i<stack.length;i++) {
    		summaryBuilder.append(stack[i]);
    		if(i != stack.length-1)
    			summaryBuilder.append(", ");
    	}
    	
    	return summaryBuilder.toString();
    }
}