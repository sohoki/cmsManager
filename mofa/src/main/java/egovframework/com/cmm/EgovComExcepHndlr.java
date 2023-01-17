package egovframework.com.cmm;

import javax.annotation.Resource;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import aten.com.backoffice.sym.log.service.EgovSysLogService;
import aten.com.backoffice.sym.log.vo.SysLog;

/**
 * @Class Name : EgovComExcepHndlr.java
 * @Description : 공통서비스의 exception 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 13.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 13.
 * @version
 * @see
 *
 */
public class EgovComExcepHndlr implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovComExcepHndlr.class);

    /**
     * 발생된 Exception을 처리한다.
     */
	@Resource(name="EgovSysLogService")
	private EgovSysLogService sysLogService;
	
	
    public void occur(Exception ex, String packageName) {
    	
    	
    	try{
    		//여기는 타는데 페이지 이동을 안하네 
    		if (LOGGER.isDebugEnabled()){
    			LOGGER.debug("[HANDLER][PACKAGE]::: {}", packageName);
        		LOGGER.debug("[HANDLER][method]::: {}",  packageName.substring(packageName.lastIndexOf(".") + 1, packageName.length()) );
        		LOGGER.debug("-------------------------------------------------------------------------------");
    		}
    		SysLog sysLog = new SysLog();
    		String processSeCode =  "E";
    		String className = packageName;
    		String methodName = packageName.substring(packageName.lastIndexOf(".") + 1, packageName.length());
    		String processTime = "0";
    		String uniqId = "";
    		String ip = "";
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	        
        	if(isAuthenticated.booleanValue()) {
        		AdminLoginVO user = (AdminLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    			uniqId = user.getAdminId();// .getUniqId();
    			ip = user.getIp() == null ? "": user.getIp();
        	}
        	sysLog.setErrorCode("500");
    		sysLog.setSrvcNm(className);
    		sysLog.setMethodNm(methodName);
    		sysLog.setProcessSeCode(processSeCode);
    		sysLog.setProcessTime(processTime);
    		sysLog.setRqesterId(uniqId);
    		sysLog.setRqesterIp(ip);
    		sysLog.setMethodResult(ex.toString());
    		
    		try {
    			sysLogService.logInsertSysLog(sysLog);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}catch (Exception e){
    		
    	}
		
		
    }
}
