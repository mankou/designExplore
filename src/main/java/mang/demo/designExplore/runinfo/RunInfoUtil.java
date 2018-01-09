package mang.demo.designExplore.runinfo;

public class RunInfoUtil {
	public static RunInfo runinfo;
	
	public static String getRunInfoStr(){
		if(runinfo!=null){
			return runinfo.getRunInfoStr();			
		}
		
		return "";
	}
}
