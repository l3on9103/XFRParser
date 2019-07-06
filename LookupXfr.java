package Hackathon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LookupXfr {
	public String lookup(String lookupFile,String lookupKey,String lookupField,LinkedHashMap<String,String> row) throws IOException {
		FileConverter fileConverter = new FileConverter();
		String dmlLookup=lookupFile.substring(0,lookupFile.indexOf("_"))+"_dml.dml";
		ArrayList< LinkedHashMap<String,String>> lookupDataList = fileConverter.fileConverter(lookupFile,dmlLookup);
		String value =row.get(lookupKey.substring(lookupKey.indexOf(".")+1));
		for(int i=0;i<lookupDataList.size();i++) {
			String lookupRowField = lookupDataList.get(i).get(lookupKey.substring(lookupKey.lastIndexOf(".")+1));
			if(lookupRowField!=null&&lookupRowField.equals(value))
				return (lookupDataList.get(i).get(lookupField.substring(lookupField.lastIndexOf(".")+1))!=null)?(lookupDataList.get(i).get(lookupField.substring(lookupField.lastIndexOf(".")+1))):null;
		}
		return null;
	}
}
