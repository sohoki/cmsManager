package aten.com.backoffice.util.web.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	public static synchronized boolean   deleteFile (String filePath, String fileNm ) {
    	try{  
    		String file_Path = filePath + fileNm;
    		LOGGER.debug("file_Path:" +file_Path);
    		File file = new File(file_Path);
    		
	        if( file.exists() ){
	            if(file.delete()){
	            	LOGGER.debug("파일삭제 성공");
	            }else{
	            	LOGGER.debug("파일삭제 실패");
	            }
	        }else{
	        	LOGGER.debug("파일이 존재하지 않습니다.");
	        }
    		return true;    		
    	}catch(Exception e){
    		LOGGER.debug("file Delete error{0}", e.toString());
    		return false;
    	}    	
    }
	
}
