package aten.com.backoffice.sts.sch.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sts.sch.service.ContentScheduleManagerService;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfoVO;
import aten.com.backoffice.sym.monter.service.DisplayPageInfoManageService;
import aten.com.backoffice.uat.service.PartInfoManageService;
import aten.com.backoffice.uat.vo.UniUtilInfo;
import aten.com.backoffice.uat.service.UniUtilManageService;
import aten.com.backoffice.util.web.service.DataNullCheck;
import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import aten.com.backoffice.sts.sch.service.AgentGroupInfoManagerService;
import aten.com.backoffice.sts.sch.vo.AgentGroupInfoVO;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfo;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfoVO;

@RestController
@RequestMapping("/backoffice/contentManage")
public class ContentScheduleManagerController {
	
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentScheduleManagerController.class);
	
   
    
    @Autowired
	protected EgovMessageSource egovMessageSource;
	
	@Autowired
    protected EgovPropertyService propertiesService;
	
	@Autowired
	private EgovCcmCmmnDetailCodeManageService cmmnDetailService;
	
	@Autowired
	private ContentScheduleManagerService contSchedule;
	
	@Autowired
	private UniUtilManageService utilService;
	
	@Autowired
	private PartInfoManageService partService;
	
	
	@Autowired
    protected DisplayPageInfoManageService displayService;	
	
	@Autowired
    protected AgentGroupInfoManagerService agentGroup;
	
	@RequestMapping(value="conSchInfoList.do")
	public ModelAndView selectConSchInfoListInfo(@ModelAttribute("loginVO") AdminLoginVO loginVO
									                                 , @ModelAttribute("ContentScheduleInfoVO") ContentScheduleInfoVO searchVO
									                                 , HttpServletRequest request
						                                			 , BindingResult bindingResult) throws Exception{
		
		ModelAndView model = new ModelAndView();
		DispalyPageInfoVO vo = new DispalyPageInfoVO();
		
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			
			if(!isAuthenticated) {
	    	    model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
	    	    model.setViewName("/backoffice/login");
	    		return model;
		    }else{
		    	HttpSession httpSession = request.getSession(true);
		   	 	loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
			    vo.setAdminLevel(loginVO.getAdminLevel());
			    vo.setPartId(loginVO.getPartId());
			    vo.setDisplayGubun("DispalyGubun_2");
		    }
			if(  searchVO.getPageUnit() > 0  ){    	   
		    	   searchVO.setPageUnit(searchVO.getPageUnit());
			}else {
				   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
			}
			
		    searchVO.setPageSize(propertiesService.getInt("pageSize"));
			searchVO.setPartId(loginVO.getPartId());
			searchVO.setAdminLevel(loginVO.getAdminLevel());
            
			searchVO.setPageIndex(DataNullCheck.IntNullCheck( searchVO.getPageIndex() , 1 ));
			
		    //부서, 전광판화면, 콘텐츠 구분 
			
			model = contSchedule.selectContentSchduleInfoManageListByPagination(searchVO);
			model.addObject("regist", searchVO);
			model.addObject("selectGroupCombo", partService.selectPartInfoCombo());
			model.addObject("selectDisplayCombo", displayService.selectDisplayPageInfoCombo(vo));
			model.addObject("selectConsch", cmmnDetailService.selectCmmnDetailCodeList("CONSCH_GUBUN") );
			model.setViewName("/backoffice/contentManage/conSchduleList");
			
			
		    
		}catch (Exception e){
			LOGGER.error("selectContentSchList ERROR: "  + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
	    return model;
	}
	
	@RequestMapping(value="conPopSchView.do")
	public ModelAndView selectContentSchList(@ModelAttribute("loginVO") AdminLoginVO loginVO
																, @ModelAttribute("ContentScheduleInfoVO") ContentScheduleInfoVO searchVO
																, HttpServletRequest request
																, BindingResult bindingResult		) throws Exception {
		
		ModelAndView model = new ModelAndView();
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	    model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	    model.setViewName("/backoffice/login");
		    		return model;
		    }
		    
		   
		    String displaySeq = request.getParameter("displaySeq") != null ? request.getParameter("displaySeq") : "";	
		    if (!displaySeq.equals(""))
		    	searchVO.setDisplaySeq(displaySeq);
		    model = contSchedule.selectContentSchduleInfoManageListByPagination(searchVO);
		    
		}catch (Exception e){
			LOGGER.error("selectContentSchList ERROR: "  + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));
			model.addObject(Globals.STATUS,  Globals.STATUS_FAIL);
		}
		model.setViewName("/backoffice/popup/conPopSchView");
	    return model;
	}
	@RequestMapping(value="conSchInfoView.do")
	public ModelAndView selectConSchInfoView(@ModelAttribute("loginVO") AdminLoginVO loginVO
			                                 , @RequestBody ContentScheduleInfo vo
			                                 , HttpServletRequest request
                                			 , BindingResult bindingResult) throws Exception{
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
			 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		     if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    		return model;
		     }
			 model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			 model.addObject("conSchInfo", contSchedule.selectConetntSchduleInfoManageView(vo.getConschCode()));
		}catch(Exception e){
			LOGGER.debug("selectConSchInfoView error:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.update"));		
		}
		return model;
	}
	@RequestMapping (value="scheduleReset.do")		
	public ModelAndView scheduleReset(@ModelAttribute("loginVO") AdminLoginVO loginVO
											            , @RequestBody AgentGroupInfoVO vo
											            , HttpServletRequest request                         				 
														, BindingResult result) throws Exception{
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS,  Globals.STATUS_LOGINFAIL);
		    		return model;
		    }
			int ret = agentGroup.updateAgentResetUpdateContent(vo.getConschCode());
			
			if (ret > 0){
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
				model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.common.update"));
			}else {
				model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
				model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
			}
		}catch(Exception e){
			LOGGER.debug("selectGroupInfoList error:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
		} 	
		return model;
		
	}
	
	@RequestMapping ("agentContentList.do")
	public ModelAndView selectagentContentList(@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
											, @RequestBody AgentGroupInfoVO searchVO
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
  		   
  		  searchVO.setPageUnit(propertiesService.getInt("pageUnit")); 
  		  searchVO.setPageSize(propertiesService.getInt("pageSize"));
  	           
  	   	  PaginationInfo paginationInfo = new PaginationInfo();
  		  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
  		  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
  		  paginationInfo.setPageSize(searchVO.getPageSize());

  		  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
  		  searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
  		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

  		  List<AgentGroupInfoVO> list = agentGroup.selectAgentContentListInfo(searchVO);
  	      model.addObject("contentList", list);
  	      int totCnt = list.size() > 0 ? list.get(0).getTotalRecordCount() : 0;
  	      
  		  paginationInfo.setTotalRecordCount(totCnt);
  	      model.addObject("totalCnt", totCnt);
  	      model.addObject("paginationInfo", paginationInfo);
  	      model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
  	      
        }catch(Exception e){
      	  LOGGER.debug("selectagentContentList error: " + e.toString());
        }
	      
	    return model;
		
	}
	
	@RequestMapping (value="conSchDeleteInfo.do")
	public ModelAndView deleteSchInfoManage(@ModelAttribute("loginVO") AdminLoginVO loginVO
			                               , @RequestBody ContentScheduleInfo vo) throws Exception{
		
		
        ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		
		
		UniUtilInfo utilInfo = new UniUtilInfo();
		utilInfo.setInTable("tb_contentschedule");
		utilInfo.setInCondition("CONSCH_CODE=["+vo.getConschCode()+"[");
		try{
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    		return model;
		    }
		
		      int ret = 	utilService.deleteUniStatement(utilInfo);	
		      if (ret > 0 ) {		    	  
		    	  model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.common.delete"));
		    	  model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		      }else {
		    	  throw new Exception();		    	  
		      }
		}catch (Exception e){
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.delete"));
	    	model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}
	@RequestMapping (value="agentGroupInfo.do")		
	public ModelAndView selectGroupInfoList(@ModelAttribute("loginVO") AdminLoginVO loginVO
				                                             , @RequestBody AgentGroupInfoVO searchVO
					                                         , HttpServletRequest request                         				 
															 , BindingResult result) throws Exception{
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
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
		    //좌측 에이전트 
			model = agentGroup.selectAgentGroupInfoListByPagination(searchVO);
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		}catch(Exception e){
			LOGGER.debug("selectGroupInfoList error:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
		} 	
		return model;
	}
	
	@RequestMapping (value="agentUpdateInfo.do")		
	public ModelAndView agentUpdateInfo(@ModelAttribute("loginVO") AdminLoginVO loginVO
							            , @RequestBody AgentGroupInfoVO searchVO
							            , HttpServletRequest request                         				 
										, BindingResult result) throws Exception{
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
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
			
			String returnTxt  = agentGroup.changedeleteAgentGroupInfo(searchVO);
			LOGGER.debug("returnTxt:" + returnTxt);
			model = agentGroup.selectAgentGroupInfoListByPagination(searchVO);
			
			if (!returnTxt.equals(Globals.STATUS_FAIL) &&  !returnTxt.equals(Globals.STATUS_UNIQUE)){
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			}else {
				model.addObject(Globals.STATUS, returnTxt);
				String message = returnTxt.equals(Globals.STATUS_UNIQUE) ? "fail.request.notUnique" : "fail.request.msg" ; 
				model.addObject(Globals.STATUS_MESSAGE,  egovMessageSource.getMessage(message));	
			}
		}catch(Exception e){
			LOGGER.debug("selectGroupInfoList error:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
		} 	
		return model;
		
	}
	
    @RequestMapping (value="conSchInfoUpdate.do")
	public ModelAndView updateContentSchUpdateInfoManage( @ModelAttribute("loginVO") AdminLoginVO loginVO
                                                         , @RequestBody ContentScheduleInfo vo
				                                         , HttpServletRequest request                         				 
														 , BindingResult result) throws Exception{
    	
    	ModelAndView model = new ModelAndView(Globals.JSONVIEW);
    	model.addObject("regist", vo);
    	try{
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    		return model;
		    }
			AdminLoginVO user = (AdminLoginVO) request.getSession().getAttribute("AdminLoginVO");	
			/** 파일 업로드 확인 하기 **/
            vo.setUserId(user.getAdminId());
			String meesage = vo.getMode().equals("Ins") ? "sucess.common.insert" : "sucess.common.update";
			int ret = contSchedule.updateContentSchduleInfoManage(vo);
			if (ret > 0){
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
				model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage(meesage));
			}else {
				throw new Exception();
			}
    	}catch (Exception e){
    		LOGGER.debug("updateequpInfoManage error:" + e.toString());
    		String meesage = vo.getMode().equals("Ins") ? "fail.common.insert" : "fail.common.update";
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage(meesage));		
    	}
    	return model;
    }
}
