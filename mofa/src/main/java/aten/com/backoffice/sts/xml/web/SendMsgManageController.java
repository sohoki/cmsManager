package aten.com.backoffice.sts.xml.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sts.xml.service.SendMsgInfoManageService;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import aten.com.backoffice.sts.xml.vo.SendMsgInfoVO;

@RestController
@RequestMapping("/backoffice/operManage")
public class SendMsgManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgManageController.class);
	
	@Autowired
	protected EgovMessageSource egovMessageSource;
    
	@Autowired
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
    /** EgovPropertyService */
	@Autowired
    protected EgovPropertyService propertiesService;
    
    @Autowired
    private SendMsgInfoManageService sendService;

	@RequestMapping ("pop_sendLst.do")
	public ModelAndView selectPopSendResult(@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
											, @RequestBody SendMsgInfoVO searchVO
											, HttpServletRequest request
											, BindingResult bindingResult) throws Exception {
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		  //공용 확인 하기 
        
        try{
	      	  Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	  	      if(!isAuthenticated) {
		  	    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
	  	    		return model;
	  	      }   
	  		  if(  searchVO.getPageUnit() > 0  ){    	   
	  	    	    searchVO.setPageUnit(searchVO.getPageUnit());
	  		  }else {
	  				searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
	  		  }
	  		  searchVO.setPageSize(propertiesService.getInt("pageSize"));
	  	      
	  	      model = sendService.selectSendMsgInfoManageListByPagination(searchVO);
	  	      model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
	  	      model.setViewName(Globals.JSONVIEW);
	  	      LOGGER.debug("model: " + model.toString());
        }catch(Exception e){
      	     LOGGER.debug("selectXmlLst error: " + e.toString());
        }
	      
	    return model;
		
	}
	@RequestMapping ("sendResultList.do")
	public ModelAndView selectSendResult(@ModelAttribute("loginVO") AdminLoginVO loginVO
									 , @ModelAttribute("searchVO") SendMsgInfoVO searchVO
									 , HttpServletRequest request
									 , BindingResult bindingResult) throws Exception {
		ModelAndView model = new ModelAndView("/backoffice/operManage/sendResultList");
		  //공용 확인 하기 
      
	    try{
	    	  Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		      if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.setViewName("/backoffice/login");
		    		return model;
		      }
			      
			  if(  searchVO.getPageUnit() > 0  ){    	   
		    	      searchVO.setPageUnit(searchVO.getPageUnit());
			  }else {
					   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
			  }
			  searchVO.setPageSize(propertiesService.getInt("pageSize"));
		      model = sendService.selectSendMsgInfoManageListByPagination(searchVO);
		      model.addObject("selectWorkgubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("WORKGUBUN"));
		      model.addObject("regist", searchVO);
		      model.setViewName("/backoffice/operManage/sendResultList");
	    }catch(Exception e){
	    	  LOGGER.debug("selectXmlLst error: " + e.toString());
	    } 
	    return model;
	}
    
}
