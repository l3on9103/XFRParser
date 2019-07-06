package Hackathon;

import java.util.LinkedHashMap;

public class StringXfr {
	public String transformString(String columnTransformation,LinkedHashMap<String,String> row){

		if(columnTransformation.contains("concat")) {
			String[] inputParameters=columnTransformation.substring(columnTransformation.indexOf("(")+1,columnTransformation.indexOf(")")).split(",");
			String value=" ";
			for(String input:inputParameters) {
				if(input.contains("in")) {
					value=value + row.get(input.substring(input.indexOf(".")+1));
				}else
					value= value+input;
			}
			return value;
		}
		else if(columnTransformation.contains("lrtrim")){
			String inputParameter = columnTransformation.substring(columnTransformation.indexOf("(")+1,columnTransformation.indexOf(")")).trim();
			StringBuilder value= new StringBuilder(inputParameter);
			if(inputParameter.contains("in")) 
				inputParameter=row.get(inputParameter.substring(inputParameter.indexOf(".")+1)).trim();
			else
				inputParameter= inputParameter.trim();
			return inputParameter;
		}
		return null;
			
	
	}
}
