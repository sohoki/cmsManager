package aten.com.backoffice.sts.message.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import aten.com.backoffice.sts.message.vo.SendMessageInfoVO;
import aten.com.backoffice.sts.message.service.SendMessageInfoManageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@RestController
@RequestMapping("/backoffice")
public class SendMessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageController.class);
	
	
	@Autowired
	private SendMessageInfoManageService sendService;
	
	@Autowired
	protected EgovMessageSource egovMessageSource;
	
	@Autowired
    protected EgovPropertyService propertiesService;

	@RequestMapping("schAgentList.do")
	public ModelAndView selectSchAgentCode(@ModelAttribute("loginVO") AdminLoginVO loginVO,
																@ModelAttribute("searchVO") SendMessageInfoVO searchVO,
					                                            HttpServletRequest request		) throws Exception {
		 
		 
		 ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
//		 String schCode =  request.getParameter("schCode") != null ? request.getParameter("schCode") : "";
		 try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if(!isAuthenticated) {
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    	return model;
			}else {
				HttpSession httpSession = request.getSession(true);
				loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
			  	searchVO.setAdminLevel(loginVO.getAdminLevel());
			    searchVO.setPartId(loginVO.getPartId());
			}
			model.addObject("schState", sendService.selectSendMessageAgentList(searchVO));
		 }catch(Exception e){
			LOGGER.debug("selectGroupInfoList error:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
		 } 	
			
		 
		 return model;
	 }
	 @RequestMapping("schAgentUpdate.do")
	 public ModelAndView updateSchAgentCode (@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                                           @RequestParam Map<String, String> commandMap,  
                                                         HttpServletRequest request		) throws Exception {
		 
		 ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		
		 try{
			 
			 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			 if(!isAuthenticated) {
			    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
			    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			    	return model;
			 }
			 String schCode =  commandMap.get("schCode") != null ? commandMap.get("schCode") : "";
			 String agentCodes =  commandMap.get("agentCodes")  != null ?  commandMap.get("agentCodes") : "";
			 SendMessageInfoVO vo = new SendMessageInfoVO();
			 vo.setSchCode(schCode);
			 List<String> agentCode = new ArrayList<String>(Arrays.asList(agentCodes.split("\\s*,\\s*")) ); 
			 LOGGER.debug("size:" + agentCode.size() + "/agentCodes:" + agentCodes);
			 vo.setAgentCodeLst( agentCode );
			 
//			 int ret = sendService.insertSendMessage(vo);
			 model.addObject(Globals.STATUS,  Globals.STATUS_SUCCESS); 
		 }catch(Exception e){
			 
			 LOGGER.error("e:" + e.toString() );
			 model.addObject(Globals.STATUS,  Globals.STATUS_FAIL);
		 }	
		 return model;
	 }
	 @RequestMapping ("pop_msgLst.do")
		public ModelAndView selectPopMsgResult(@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
												, @RequestBody SendMessageInfoVO searchVO
												, HttpServletRequest request
												, BindingResult bindingResult) throws Exception {
			
			ModelAndView model = new ModelAndView(Globals.JSONVIEW);
			  //공용 확인 하기 
	        
	        try{
	      	  Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	  	      if(!isAuthenticated) {
		  	    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS,  Globals.STATUS_LOGINFAIL);
	  	    		return model;
	  	      }
	  		      
	  		  
	  		  searchVO.setPageUnit(propertiesService.getInt("pageUnit")); 
	  		  searchVO.setPageSize(propertiesService.getInt("pageSize"));
	  	      
	  	      LOGGER.debug("searchVO:" + searchVO.getAgentCode());
	  	      /** pageing */       
	  	   	  PaginationInfo paginationInfo = new PaginationInfo();
	  		  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	  		  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	  		  paginationInfo.setPageSize(searchVO.getPageSize());

	  		  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	  		  searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
	  		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	  		  List<SendMessageInfoVO> list = sendService.selectSendMessageAgentHistoryList(searchVO);
	  	      model.addObject("msgLst", list);
	  	      int totCnt = list.size() > 0 ? list.get(0).getTotalRecordCount() : 0;
	  	      
	  		  paginationInfo.setTotalRecordCount(totCnt);
	  	      model.addObject("totalCnt", totCnt);
	  	      model.addObject("paginationInfo", paginationInfo);
	  	      model.addObject(Globals.STATUS, "SUCCESS");
	  	      
	        }catch(Exception e){
	      	  LOGGER.debug("selectPopMsgResult error: " + e.toString());
	        }
		      
		    return model;
			
		}
	  
}
