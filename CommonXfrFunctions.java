package Hackathon;

import java.io.IOException;
import java.util.LinkedHashMap;

public class CommonXfrFunctions {
	
	public String isDefined(String columnTransformation, LinkedHashMap<String,String> row) {
		String columnValue=row.get(columnTransformation.substring(columnTransformation.indexOf(".")+1,columnTransformation.indexOf(")")));
		if(columnValue.equals("")||columnValue.equals(" ")||columnValue==null)
		return "0";
		else
			return columnValue;
	
	}
	public String isNull(String columnTransformation, LinkedHashMap<String,String> row) {
		String columnValue=row.get(columnTransformation.substring(columnTransformation.indexOf(".")+1,columnTransformation.indexOf(")")));
		if(columnValue.equals("")||columnValue.equals(" ")||columnValue==null)
		return "1";
		else
			return "0";
	
	}
	public String firstDefined(String columnTransformation, LinkedHashMap<String,String> row) {
		String[] inputParameters =columnTransformation.substring(columnTransformation.indexOf("(")+1,columnTransformation.indexOf(")")).split(",");
		for(String input : inputParameters) {
			if(input.contains("in.")) {
				String columnValue=row.get(input.substring(input.indexOf(".")+1));
				if(!(columnValue.equals("")||columnValue.equals(" ")||columnValue==null))
					return columnValue;
			}else if((!(input.equals("")||input.equals(" ")||input==null))) {
					return input;
			}
		}
		return null;
	
	}
	public String lookup(String columnTransformation, LinkedHashMap<String,String> row) throws IOException {
		String lookupFile =columnTransformation.substring(columnTransformation.indexOf("\"")+1,columnTransformation.lastIndexOf("\""));
		String lookupKey=columnTransformation.substring(columnTransformation.indexOf(",")+1,columnTransformation.lastIndexOf(")"));
		String lookupField ;
		if(columnTransformation.lastIndexOf(".")>columnTransformation.lastIndexOf(")"))
			lookupField=columnTransformation.substring(columnTransformation.lastIndexOf(".")+1);
		else 
			lookupField=null;
		LookupXfr lkup =new LookupXfr();
		/*for(String input : inputParameters) {
			if(input.startsWith("\"")) {
				String lookupFile=input.substring(input.indexOf("\"")+1,input.lastIndexOf("\""));
				String lookupKey=input.substring(input.indexOf("\"")+1,input.lastIndexOf("\""));
				lkup.lookup(lookupFile,lookupKey);
					return null;
			}else if((!(input.equals("")||input.equals(" ")||input==null))) {
					return input;
			}
		}*/
		return lkup.lookup(lookupFile,lookupKey,lookupField,row);
	}
	
}
