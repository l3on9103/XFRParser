package Hackathon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class FileConverter {
	public ArrayList< LinkedHashMap<String,String>> fileConverter(String fileName,String dmlFileName) throws IOException {
		
	String[] fileArray = Files.readAllLines(new File("C:\\Users\\718728\\Downloads\\"+fileName).toPath()).toArray(new String[0]);
	String[] delimiterVariableArray = Files.readAllLines(new File("C:\\Users\\718728\\Downloads\\"+dmlFileName).toPath()).toArray(new String[0]);
	return convertInputToArrayList(convertInputToLinkedHashMap(fileArray),convertInputDmlToLinkedHashMap(delimiterVariableArray));
	
	}

	static  LinkedHashMap<Integer,String> convertInputToLinkedHashMap(String[] fileArray) {
		int itr=0;
		LinkedHashMap<Integer,String> dataMap=new  LinkedHashMap<Integer,String>();
		for(String line:fileArray) {
			dataMap.put(itr, line);
			itr++;
		}
		return dataMap;
	}	
	static  LinkedHashMap<String,String> convertInputDmlToLinkedHashMap(String[] fileArray) {
		LinkedHashMap<String,String> delimiterVariableMap =new LinkedHashMap<String,String>();
		for(int i=0;i<fileArray.length;i++) {
    	    if(i!=0 && i!=fileArray.length-1) {
    	    	int indexOfSpace=fileArray[i].indexOf(" ");
    	    	int indexOfSemicolon=fileArray[i].indexOf(";");
    	    	String delimiter=fileArray[i].substring(0,indexOfSpace);
    	    	String variable=fileArray[i].substring(indexOfSpace+1,indexOfSemicolon);
    	    	delimiterVariableMap.put(variable, delimiter);
    	    }
    	}
		return delimiterVariableMap;
	}
	static ArrayList< LinkedHashMap<String,String>> convertInputToArrayList( LinkedHashMap<Integer,String> rawDataMap, LinkedHashMap<String,String> delimiterVariableInput) {
		ArrayList< LinkedHashMap<String,String>> listConvertedData= new ArrayList< LinkedHashMap<String,String>>();
		Set<String> keySet=delimiterVariableInput.keySet();
		String delimiter=new String();
		
		
		for(int i=0;i<rawDataMap.size();i++) {
			 LinkedHashMap<String,String> columnsMap= new  LinkedHashMap<String,String>();
			int keyIndex=keySet.size()-1;
			int previousDelimiterIndex=-1;
			String rowData=rawDataMap.get(i);
			for(String key:keySet) {
				String dataTypeDelimiter=delimiterVariableInput.get(key);
				String dataType=dataTypeDelimiter.substring(0, dataTypeDelimiter.indexOf("("));
				delimiter=dataTypeDelimiter.substring(dataTypeDelimiter.indexOf("(")+1, dataTypeDelimiter.indexOf(")"));
				
					/*if(delimiter.contains("|")) {
						delimiter="|";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rawDataMap.get(i).substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							String sub=rawDataMap.get(i).substring(previousDelimiterIndex+1);
							int subDelimiterIndex=sub.indexOf(delimiter);
							col=sub.substring(0,subDelimiterIndex);
							previousDelimiterIndex+=sub.indexOf(delimiter);
						}
						columnsMap.put(key,col);
					}*/if(delimiter.contains("|")) {
						delimiter="|";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData.substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							col=rowData.substring(0,rowData.indexOf(delimiter));
						}
						rowData=rowData.substring(rowData.indexOf(delimiter)+1);
						columnsMap.put(key,col);
					}
					else if(delimiter.contains("?")) {
						delimiter="?";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData.substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							String sub=rowData.substring(previousDelimiterIndex+1);
							int subDelimiterIndex=sub.indexOf(delimiter);
							col=rowData.substring(0,rowData.indexOf(delimiter));
							previousDelimiterIndex+=sub.indexOf(delimiter);
						}
						rowData=rowData.substring(rowData.indexOf(delimiter)+1);
						columnsMap.put(key,col);
					}
					else if(delimiter.contains("*")) {
						delimiter="*";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData.substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							String sub=rowData.substring(previousDelimiterIndex+1);
							int subDelimiterIndex=sub.indexOf(delimiter);
							col=rowData.substring(0,rowData.indexOf(delimiter));
							previousDelimiterIndex+=sub.indexOf(delimiter);
						}
						rowData=rowData.substring(rowData.indexOf(delimiter)+1);
						columnsMap.put(key,col);
					}
					else if(delimiter.contains("/")) {
						delimiter="/";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData.substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							String sub=rowData.substring(previousDelimiterIndex+1);
							int subDelimiterIndex=sub.indexOf(delimiter);
							col=rowData.substring(0,rowData.indexOf(delimiter));
							previousDelimiterIndex+=sub.indexOf(delimiter);
						}
						rowData=rowData.substring(rowData.indexOf(delimiter)+1);
						columnsMap.put(key,col);
					}
					else if(delimiter.contains("_")) {
						delimiter="_";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData.substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							String sub=rowData.substring(previousDelimiterIndex+1);
							int subDelimiterIndex=sub.indexOf(delimiter);
							col=rowData.substring(0,rowData.indexOf(delimiter));
							previousDelimiterIndex+=sub.indexOf(delimiter);
						}
						rowData=rowData.substring(rowData.indexOf(delimiter)+1);
						columnsMap.put(key,col);
					}
					else if(delimiter.contains("-")) {
						delimiter="-";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData.substring(0,rawDataMap.get(i).indexOf(delimiter));
							previousDelimiterIndex=rawDataMap.get(i).indexOf(delimiter);
						}else {
							String sub=rowData.substring(previousDelimiterIndex+1);
							int subDelimiterIndex=sub.indexOf(delimiter);
							col=rowData.substring(0,rowData.indexOf(delimiter));
							previousDelimiterIndex+=sub.indexOf(delimiter);
						}
						rowData=rowData.substring(rowData.indexOf(delimiter)+1);
						columnsMap.put(key,col);
					}
					/*else if(delimiter.contains("n")) {
						delimiter=" ";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rawDataMap.get(i);
							previousDelimiterIndex=-1;
						}else {
							String sub=rawDataMap.get(i).substring(previousDelimiterIndex+2);
							col=sub;
							previousDelimiterIndex=-1;
						}
						columnsMap.put(key,col);
					}*/else if(delimiter.contains("n")) {

						delimiter=" ";
						String col;
						if(previousDelimiterIndex==-1) {
							col=rowData;
						}else {
							col=rowData.substring(0);
						}
						columnsMap.put(key,col);
					
					}
				

				keyIndex--;
				
			}
			listConvertedData.add(columnsMap);
		}	
		
		return listConvertedData;
		
	}
}

