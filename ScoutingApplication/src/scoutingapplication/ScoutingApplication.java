package scoutingapplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.UIManager;

/*
 * @author Kyle
 */
public class ScoutingApplication {

    boolean running = true;
    Runnable submit;
    
    //Frame and Panel
    MainFrame mF;
    
    public ScoutingApplication() throws IOException {
    	setLookAndFeel();
    	
    	submit = new Runnable() {
        	public void run() {
        		compileReport();
        	}
        };
        
        mF = MainFrame.create(submit);
    }
    
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("unused")
		ScoutingApplication main = new ScoutingApplication();
    }
    
    public static void setLookAndFeel() {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        };
    }
    
    public void compileReport() {
    	DateFormat dF = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    	String[] info = mF.getInformation();
    	String fileName = "T" + info[0] + "M" + info[1] + " " + dF.format(new Date());
    	String summary = "|T:" + info[0] + "|A:" + info[3] + "|M:" + info[1] + "|ON:" + info[2] + mF.getSummary();
    	
    	//Output to file***************************************************************************
    	Writer writer = null;
    	try {
    		File current = new File(".");
			File parent = current.getParentFile();
			File data = new File(parent, "Data");
			File file = new File(data, fileName);
    	    writer = new BufferedWriter(new OutputStreamWriter(
    	          new FileOutputStream(file), "utf-8"));
    	    writer.write(summary);
    	    writer.close();
    	    //Data\\
    	} catch (IOException ex) {
    	  // report
    	} finally {
    	   try {writer.close();} catch (Exception ex) {}
    	}
    	
    	mF.reset();
    }
}