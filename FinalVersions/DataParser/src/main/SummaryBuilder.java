package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SummaryBuilder {

	public static String[] stackNames = {"Stack0","Stack1","Stack2","Stack3","Stack4","Stack5","Stack6","Stack7"};
	
	public static void makeSummary(String teamNum) {
		
		List<String> stringData = getFiles(teamNum);
		
		String[] temp;
		Map<String,String> fields = new TreeMap<String, String>();
		StringBuilder output = new StringBuilder();
		
		output.append("Team,Match,Totes,Containers,MaxHeight,MatchPoints");
		output.append(System.lineSeparator());
		
		for(String dataS: stringData) {
			temp = dataS.split("\\|");

			for(int j=0;j<temp.length;j++){
				if(temp[j].split(":").length > 1)
					fields.put(temp[j].split(":")[0],temp[j].split(":")[1]);
			}
			
			output.append(getSummary(fields));
			output.append(System.lineSeparator());
		}
		
		stringToCSV(teamNum,output.toString());
	}
	
//	public static List<String> getFiles(Date start, Date end, String teamNum) {
//		
//	}
//	
//	public static List<String> getFiles(Date start, Date end) {
//		
//	}
	
	public static List<String> getFiles(String teamNum) {
		List<String> stringData = new ArrayList<String>();
		
		File current = new File(".");
		File parent = current.getParentFile();
		File data = new File(parent, "Data");
		File[] matchFiles = data.listFiles();
		
		System.err.println(teamNum);
		for(int i=0;i<matchFiles.length;i++) {
			if(matchFiles[i].getName().contains("T" + teamNum)){
				stringData.add(fileToString(matchFiles[i].getName()));	
			}
		}
		
		return stringData;
	}
	
	public static List<String> getFiles() {
		List<String> stringData = new ArrayList<String>();
		
		File current = new File(".");
		File parent = current.getParentFile();
		File data = new File(parent, "Data");
		File[] matchFiles = data.listFiles();
		
		for(int i=0;i<matchFiles.length;i++) {
			stringData.add(fileToString(matchFiles[i].getName()));
		}
		
		return stringData;
	}
	
	public static String getSummary(Map<String,String> fields) {
		StringBuilder output = new StringBuilder();
		
		//Team Number
		output.append(fields.get("T"));
		output.append(",");
		
		//Match Number
		output.append(fields.get("M"));
		output.append(",");
		
		//Totes
		output.append(countItem(fields,"2"));
		output.append(",");
		
		//Containers
		output.append(countItem(fields,"6"));
		output.append(",");
		
		//Max stack height
		output.append(maxHeight(fields));
		output.append(",");
		
		//Match Points
		output.append(fields.get("MP"));
		
		return output.toString();
	} 
	
	public static int countItem(Map<String,String> fields, String item) {
		String stack;
		String[] stackElements;
		int counter = 0;
		for(int i=0;i<stackNames.length;i++) {
			stack = fields.get(stackNames[i]);
			stackElements = stack.split(",");
			
			for(int j=0;j<stackElements.length;j++) {
				if(stackElements[j].equals(item))
					counter++;
			}
		}
		
		return counter;
	}

	public static int maxHeight(Map<String,String> fields) {
		String stack;
		String[] stackElements;
		int counter = 0, max = 0;
		
		for(int i=0;i<8;i++) {
			counter = 0;
			stack = fields.get(stackNames[i]);
			stackElements = stack.split(",");
			
			for(int j=0;j<stackElements.length;j++) {
				if(!stackElements[j].equals("0"))
					counter++;
				else
					break;
			}
		
			max = Math.max(max, counter);
		}
	
		return max;
	}

	//Converts the contents of a file to a string
	public static String fileToString(String fileName) {
		try {
			File current = new File(".");
			File parent = current.getParentFile();
			File data = new File(parent, "Data");
			File file = new File(data, fileName);
			return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return " ";
	}
	
	//Converts a string to a file
	public static void stringToCSV(String fileName, String contents) {
		Writer writer = null;
    	try {
    		File current = new File(".");
			File parent = current.getParentFile();
			File summaries = new File(parent, "Summaries");
			File file = new File(summaries, fileName + ".csv");
    	    writer = new BufferedWriter(new OutputStreamWriter(
    	          new FileOutputStream(file), "utf-8"));
    	    writer.write(contents);
    	    writer.close();
    	} catch (IOException ex) {
    	} finally {
    	   try {writer.close();} catch (Exception ex) {}
    	}
	}
}