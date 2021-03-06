/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sidebar;

/*
 * @author Kyle
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

    public LogoPanel() {
       try {                
          image = ImageIO.read(getClass().getResource("/Resources/RecycleRushIcon.png"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}
