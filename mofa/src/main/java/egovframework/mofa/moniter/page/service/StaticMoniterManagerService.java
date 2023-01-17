package egovframework.mofa.moniter.page.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public interface StaticMoniterManagerService {

	
	    List<Map<String, Object>> selectPageUni01() throws Exception;
		
	    List<Map<String, Object>> selectPageUni02() throws Exception;
		
		List<Map<String, Object>> selectPageUni03(String agentid) throws Exception;
		
		Map<String, Object> selectPageUni04() throws Exception;
		
		List<Map<String, Object>> selectPageUni05() throws Exception;
		
		List<Map<String, Object>> selectPageUni06() throws Exception;
		
		List<Map<String, Object>> selectPageUni07() throws Exception;
		
		List<Map<String, Object>> selectPageUni08() throws Exception;
		
		ModelAndView batchTest() throws Exception;
}
