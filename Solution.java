import java.io.*;
import java.math.*;
import java.nio.file.Files;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import Hackathon.FileConverter;
import Hackathon.XFR;
import com.opencsv.*;


public class Solution {
	public static void writeDataLineByLine(String filePath,ArrayList<LinkedHashMap<String,String>> outputdataList) 
	{ 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        Set<String> keySet= outputdataList.get(0).keySet();
	        String[] header =new String[keySet.size()];
	        String[] data =new String[keySet.size()]; 
	        int i=0;
	        for(String key:keySet) {
	        	header[i]=key;
	        	i++;	
	        }
	        writer.writeNext(header); 
	        for( i=0;i<outputdataList.size();i++) {
	        	int j=0;
	        	for(String key:keySet) { 
	        		data[j]=outputdataList.get(i).get(key);
	        		j++;
	        	}
	        	 writer.writeNext(data);
	        	}
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	} 
	
    public static void main(String[] args) throws IOException {
    	FileConverter fileConverter = new FileConverter();
    	
    	/////////////////////// ENTER FILE NAMES HERE //////////////////////
    	String rawDataFileName="Raw_Data.dat";
    	String rawDataDmlFileName="inputDML.dml";
    	String outputDataDmlFileName="outputDML.dml";
    	String xfrFileName="XFR4.xfr";
    	
    	/////////////////////// RAW DATA MANIPULATION /////////////////////
    	ArrayList<LinkedHashMap<String,String>> inputDataList=fileConverter.fileConverter(rawDataFileName,rawDataDmlFileName);
    	
    	///////////////////////       FOR XFR      /////////////////////////
    	String[] xfr = Files.readAllLines(new File("C:\\Users\\718728\\Downloads\\"+xfrFileName).toPath()).toArray(new String[0]);
    	ArrayList< LinkedHashMap<String,String>> outputDataList = new ArrayList< LinkedHashMap<String,String>>();
    	XFR xfrObj = new XFR();
    	int i=0;
    	while(i<xfr.length) {
    		if(xfr[i].contains("out") && xfr[i].contains("::")){
    	    	if(xfr[i].contains("reformat")||xfr[i].contains("rollup")||xfr[i].contains("join")) {
    	    	outputDataList=xfrObj.solve(xfr,i,outputDataList,inputDataList);
    	    	}
    	    	i++; //increment i after each transformation function in XFR
    		}
    		i++;
    	}
    	
    	/////////////////// Writing Output ////////////////////////////////// 
    	System.out.println("");
    	writeDataLineByLine("D:\\testout.csv",outputDataList);
      
    }
}
