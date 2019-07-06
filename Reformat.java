package Hackathon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class Reformat {
	public ArrayList<LinkedHashMap<String, String>> reformat(LinkedHashMap<String,String> outputMap,ArrayList<LinkedHashMap<String, String>> outputDataList,ArrayList<LinkedHashMap<String, String>> inputDataList) throws IOException{
		

		OutputTransformation outputTransformObj =new OutputTransformation();
		//ColumnName, <DataType> 
		
		Set<String> outpuMapkeySet=outputMap.keySet();
		
		
		for(int i=0;i<inputDataList.size();i++) {
			LinkedHashMap<String,String> row=inputDataList.get(i);
			LinkedHashMap<String,String> outputColumnValueMap= new LinkedHashMap<String,String>();
			for(String key: outpuMapkeySet) {
				if(!key.contains("*")) {
					String columnDataType;
					String columnTransformation;
					if(outputMap.get(key).contains("string")) {
						columnDataType =outputMap.get(key).substring(0,outputMap.get(key).indexOf("_"));
						columnTransformation=outputMap.get(key).substring(outputMap.get(key).indexOf("_")+1);
				}else {
						columnDataType=outputMap.get(key).substring(0,outputMap.get(key).indexOf("("));
					columnTransformation=outputMap.get(key).substring(0);
						}
					String columnName = key; 
					String columnValue=findReformattedColumnValue( columnDataType, columnTransformation, row);
				outputColumnValueMap.put(columnName,columnValue);
			}else { 
				Set<String> rowKeySet= row.keySet();
				for(String rowKey:rowKeySet) 
					outputColumnValueMap.put(rowKey,row.get(rowKey));
					
				}
			}	
			outputDataList.add(outputColumnValueMap);
		}
		
		return outputDataList;
	}
	
	public String findReformattedColumnValue(String columnDataType,String columnTransformation,LinkedHashMap<String,String> row) throws IOException {
		if(columnDataType.contains("string")) {
			StringXfr sXfrObj =new StringXfr();
			return sXfrObj.transformString(columnTransformation, row);
				
		}else {
			CommonXfrFunctions cXFObj = new CommonXfrFunctions();
			if(columnDataType.contains("is_defined")) 
				return cXFObj.isDefined(columnTransformation, row);
			else if(columnDataType.contains("is_null")) 
				return cXFObj.isNull(columnTransformation, row);
			else if(columnDataType.contains("first_defined"))
				return cXFObj.firstDefined(columnTransformation, row);
			else if(columnDataType.contains("lookup"))
				return cXFObj.lookup(columnTransformation, row);
		}
		return null;
	}
}
