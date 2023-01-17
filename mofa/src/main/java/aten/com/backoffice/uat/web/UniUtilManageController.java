package aten.com.backoffice.uat.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import aten.com.backoffice.uat.service.UniUtilManageService;
import aten.com.backoffice.uat.vo.UniUtilInfo;


@RestController
public class UniUtilManageController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UniUtilManageController.class);
	
    @Autowired
	private UniUtilManageService utilService;
	
	@Autowired
	protected EgovMessageSource egovMessageSource;
	
	
	@RequestMapping(value="/sohoki/util/UniCheck.do")
	public ModelAndView selectUniCheck(@ModelAttribute("vo") UniUtilInfo vo
														 , HttpServletRequest request
														 , BindingResult bindingResult	)throws Exception{
		
		ModelAndView  model = new ModelAndView(Globals.JSONVIEW);
		try{
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	          if(!isAuthenticated) {
	      		model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
	      		model.addObject("status", Globals.STATUS_LOGINFAIL);
	      		return model;
	      		
	          }
			int ret = utilService.selectIdDoubleCheck(vo);
			LOGGER.debug("ret:"+ret);
			if (ret > 0){
				model.addObject("status", Globals.STATUS_NTUNIQUE);
				model.addObject("message", egovMessageSource.getMessage("common.codeFail.msg") );
			}else {
				model.addObject("status", Globals.STATUS_UNIQUE);
				model.addObject("message", egovMessageSource.getMessage("common.codeOk.msg") );
			}
		}catch(Exception e){
			LOGGER.debug("selectUniCheck error:" + e.toString());
			model.addObject("message", egovMessageSource.getMessage("fail.common.select") );
			model.addObject("status", Globals.STATUS_UNIQUE);
		}
		return model;
	}
	@RequestMapping(value="/sohoki/util/UnideleteParam.do")
	public ModelAndView uniDeleteParam(@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                                              HttpServletRequest request,
			                                              @RequestParam Map<String, String> paramMap) throws Exception {
		
		  ModelAndView  model = new ModelAndView(Globals.JSONVIEW);
	      try{
				
	    	  Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	          if(!isAuthenticated) {
	      		model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
	      		model.addObject("status", Globals.STATUS_LOGINFAIL);
	      		return model;
	      		
	          }
			  String tableId =  paramMap.get("tableId"); // request.getParameter("tableId");
			  String conField = paramMap.get("conField"); //request.getParameter("conField");
			  String value =  paramMap.get("value"); //request.getParameter("value");
				
	    	  UniUtilInfo utilInfo = new UniUtilInfo();
	  		  utilInfo.setInTable(tableId);
	  		  utilInfo.setInCondition(conField+"=["+value+"[");
				
			  int ret = utilService.deleteUniStatement(utilInfo);
				
				LOGGER.debug("ret:"+ret);
				if (ret > 0){
					
					model.addObject("status", Globals.STATUS_SUCCESS);
					
				}else {
					model.addObject("message", egovMessageSource.getMessage("fail.common.delete") );
					model.addObject("status", Globals.STATUS_FAIL);
				}
		  }catch(Exception e){
				LOGGER.debug("uniDelete error:" + e.toString());
				model.addObject("message", egovMessageSource.getMessage("fail.common.delete") );
				model.addObject("status", Globals.STATUS_FAIL);
				
		  }
	      return model;
	}
	@RequestMapping(value="/sohoki/util/Unidelete.do")
	public ModelAndView uniDelete(@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                                     @ModelAttribute("vo") UniUtilInfo vo
												, HttpServletRequest request
												, BindingResult bindingResult	)throws Exception{
		ModelAndView  model = new ModelAndView(Globals.JSONVIEW);
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	          if(!isAuthenticated) {
	      		model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
	      		model.addObject("status", Globals.STATUS_LOGINFAIL);
	      		return model;
	      		
	        }
			int ret = utilService.deleteUniStatement(vo);
			
			LOGGER.debug("ret:"+ret);
			if (ret > 0){
				model.addObject("status", Globals.STATUS_SUCCESS);
			}else {
				model.addObject("message", egovMessageSource.getMessage("fail.common.delete") );
				model.addObject("status", Globals.STATUS_FAIL);
			}
		}catch(Exception e){
			LOGGER.error("uniDelete error:" + e.toString());
			model.addObject("message", egovMessageSource.getMessage("fail.common.delete") );
			model.addObject("status", Globals.STATUS_FAIL);
			
		}
		return model;
	}
	public int callUniDelete(UniUtilInfo vo)throws Exception{
		//임시 조치
		vo.setOtCnt(-1);
		utilService.deleteUniStatement(vo);
		int otCnt =vo.getOtCnt();
		LOGGER.debug("otCnt:" + otCnt);
		return 1;
	}
}
