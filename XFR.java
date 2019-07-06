package Hackathon;
import java.io.IOException;
import java.util.*;
public class XFR {
	Deque<String> indentationStack =new ArrayDeque<String>();
	private String xfrFunction;
	
	public String getXfrFunction() {
		return xfrFunction;
	}

	public void setXfrFunction(String xfrFunction) {
		this.xfrFunction = xfrFunction;
	}
	public ArrayList<LinkedHashMap<String, String>> solve(String[] xfr,int i, ArrayList<LinkedHashMap<String, String>> outputDataList,ArrayList<LinkedHashMap<String, String>> inputDataList) throws IOException{

		if(xfr[i].contains("reformat")||xfr[i].contains("rollup")||xfr[i].contains("join")) {
			this.xfrFunction=xfr[i];
			i++;
			if(xfr[i].contains("begin")) {
				indentationStack.push("begin");
				i++;
			}
				while(!indentationStack.isEmpty()) {
					
						
						LinkedHashMap<String,String> outputMap =new LinkedHashMap<String,String>();
						while(!xfr[i].contains("end")) {
							
						String outputVariableName=xfr[i].substring(xfr[i].indexOf(".")+1,xfr[i].indexOf(":"));
						String outputTransformationFunction=xfr[i].substring(xfr[i].indexOf(":")+2,xfr[i].indexOf(";"));
						outputMap.put(outputVariableName,outputTransformationFunction);
							i++;
						}
						indentationStack.pop();
						if(this.xfrFunction.contains("reformat")) {
							Reformat reformatObj =new Reformat();
							outputDataList=reformatObj.reformat(outputMap,outputDataList,inputDataList);
						}else if(this.xfrFunction.contains("rollup")) {
							Rollup rollupObj = new Rollup();
							outputDataList=rollupObj.rollup(outputMap,outputDataList,inputDataList);
						}
			}
		}
		return outputDataList;
	}
	
	
}
