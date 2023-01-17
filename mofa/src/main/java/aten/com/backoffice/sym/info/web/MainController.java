package aten.com.backoffice.sym.info.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.exception.CustomerExcetion;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.property.EgovPropertyService;
import aten.com.backoffice.sym.log.annotation.NoLogging;


@RestController
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	
  
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** TRACE */
    @Resource(name="leaveaTrace")
    LeaveaTrace leaveaTrace;
	
	// include 파일 정리 
    @NoLogging
	@RequestMapping(value="/backoffice/inc/top_inc.do")   
	public ModelAndView nhisTop() throws Exception{	
		ModelAndView mav = new ModelAndView("/backoffice/inc/top_inc");
		return mav;
	}
    @NoLogging
	@RequestMapping(value="/backoffice/inc/bottom_inc.do")	
	public ModelAndView nhisBottom() throws Exception{				
		ModelAndView mav = new ModelAndView("/backoffice/inc/bottom_inc");
		return mav;
	}
    
    @RequestMapping(value="/cmm/error/duplication")
    public ModelAndView dubpliacationPage() throws Exception{				
		ModelAndView mav = new ModelAndView("/cmm/error/duplication");
		return mav;
	}
    @RequestMapping(value="/backoffice/ErrorCheck.do")
    public ModelAndView errorPage() throws Exception{			
    	LOGGER.debug("1----------------------------------------------------");
		/*ModelAndView mav = new ModelAndView("/backoffice/bottom_inc");*/
		LOGGER.debug("2----------------------------------------------------");
		throw new CustomerExcetion("중복 에러 확인");
		//return mav;
	}
}
