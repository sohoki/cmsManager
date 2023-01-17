package aten.com.backoffice.util.web.service;

public class DataNullCheck {

	
	public static int IntNullCheck(int i, int defaultVal){
		
		try{
			return i;
		}catch(Exception e){
			System.out.println("E:" + e.toString());
			return defaultVal;
		}
	}
    public static int IntNullCheck(String i, int defaultVal){
		
		try{
			return Integer.parseInt(i);
		}catch(Exception e){
			System.out.println("String E:" + e.toString());
			return defaultVal;
		}
	}
	
}
