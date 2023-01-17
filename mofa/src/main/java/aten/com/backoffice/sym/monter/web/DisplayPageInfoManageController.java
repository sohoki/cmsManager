package aten.com.backoffice.sym.monter.web;


import java.util.List;
import java.util.Map;
import egovframework.com.cmm.AdminLoginVO;
import aten.com.backoffice.sts.report.vo.ReportPageInfoVO;
import aten.com.backoffice.sts.report.service.ReportPageInfoManageService;
import aten.com.backoffice.sym.agent.service.AgentInfoManageService;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfo;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfoVO;
import aten.com.backoffice.sym.monter.service.DisplayPageInfoManageService;
import aten.com.backoffice.sym.monter.vo.DetailPageInfo;
import aten.com.backoffice.sym.monter.vo.DetailPageInfoVO;
import aten.com.backoffice.sym.monter.service.DetailPageInfoManageService;


import aten.com.backoffice.sym.rnt.service.AuthorInfoManageService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import aten.com.backoffice.uat.service.PartInfoManageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/backoffice/contentManage")
public class DisplayPageInfoManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(DisplayPageInfoManageController.class);
	
	@Autowired
	protected EgovMessageSource egovMessageSource;
	
	@Autowired
    protected EgovPropertyService propertiesService;
	
	@Autowired
    protected AgentInfoManageService agentService;
	
	@Autowired
	private EgovCcmCmmnDetailCodeManageService cmmnDetailService;
	
	@Autowired
	private DetailPageInfoManageService detailService;
	
	
	
	@Autowired
    protected DisplayPageInfoManageService displayService;
	
	@Autowired
	private ReportPageInfoManageService reportService;
	
	@Autowired
	private PartInfoManageService partService;
	
	@Autowired
	private AuthorInfoManageService authorInfoManageService;
	
	
	@RequestMapping(value="displayList.do")
	public ModelAndView  selectDisplayInfoManageListByPagination(@ModelAttribute("loginVO") AdminLoginVO loginVO
														, @ModelAttribute("searchVO") DispalyPageInfoVO searchVO
														, HttpServletRequest request
														, BindingResult bindingResult) throws Exception {
		 
		 ModelAndView  model = new  ModelAndView("/backoffice/contentManage/displayList");
		 
		 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	     if(!isAuthenticated) {
	    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
	    		model.setViewName("/backoffice/login");
	    		return model;
	     }else{
	    	 HttpSession httpSession = request.getSession(true);
	    	 loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
		     searchVO.setAdminLevel(loginVO.getAdminLevel());
		     searchVO.setPartId(loginVO.getPartId());
	     }
	       
	     model = displayService.selectDisplayPageInfoManageListByPagination(searchVO);
	     model.addObject("regist", searchVO);
	     model.addObject("userInfo",loginVO);
	     model.addObject("selectDisplayCombo", cmmnDetailService.selectCmmnDetailCombo("DispalyGubun"));
	     // 콘텐츠 관련 내용도 미리 넣기 
	     model.addObject("selectDisplayNextCombo", displayService.selectDisplayPageInfoCombo(searchVO));
	     model.addObject("selectState", authorInfoManageService.selectAuthorIInfoManageCombo());		
		 model.addObject("selectGroup", partService.selectPartInfoCombo());
		 model.setViewName("/backoffice/contentManage/displayList");
		 return model;	
	}
	@RequestMapping (value="displayPageList.do")
	public ModelAndView  selectDisplayInfoManageListPagination(@ModelAttribute("loginVO") AdminLoginVO loginVO
																								, @ModelAttribute("searchVO") DispalyPageInfoVO searchVO
																								, HttpServletRequest request
																								, BindingResult bindingResult) throws Exception {
			
			ModelAndView  model = new  ModelAndView(Globals.JSONVIEW);
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if(!isAuthenticated) {
					model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
					model.addObject(Globals.STATUS,  Globals.STATUS_LOGINFAIL);
					return model;
			}else{
					HttpSession httpSession = request.getSession(true);
					loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
					searchVO.setAdminLevel(loginVO.getAdminLevel());
					searchVO.setPartId(loginVO.getPartId());
			}
			
			model = displayService.selectDisplayPageInfoManageListByPagination(searchVO);
			return model;	
	}
	@RequestMapping (value="displayDetail.do")
	public ModelAndView selecDispalyInfoManageDetail(@ModelAttribute("loginVO") AdminLoginVO loginVO
                                              , @RequestBody  DispalyPageInfoVO vo
                                              , HttpServletRequest request
                                			  , BindingResult bindingResult ) throws Exception{	
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		model.addObject("regist", vo);
		model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
	    model.addObject("display", displayService.selectDisplayPageInfoManageDetail(vo.getDisplaySeq()));	
		return model;
	}
	
	@RequestMapping(value="detailUpdate.do")
	public ModelAndView detailUpdate(@ModelAttribute("loginVO") AdminLoginVO loginVO,
				                                     @RequestBody DetailPageInfo vo,
							                          WebRequest request) throws Exception {
		
		
					ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
					try{
						
						Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
					    if(!isAuthenticated) {
					    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
					    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
					    	return model;
					    }
						vo.setUserId(EgovUserDetailsHelper.getAuthenticatedUser().toString());
						int ret = detailService.insertDetailPageInfoManage(vo);
						if (ret > 0){
							model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("sucess.common.insert"));
							model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
						}else {
							throw new Exception(); 
						}
						model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("sucess.common.insert"));
						model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
					
					}catch(Exception e){
						LOGGER.debug("detailUpdate error:" + e.toString());
						model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.insert"));	
						model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
					}
					return model;	
	}
	
	@RequestMapping(value="contentPreview.do")
	public String selectContentPreview(HttpServletRequest request) throws Exception{
		String jsonTest = "";
		String displaySeq = request.getParameter("displaySeq") != null ? request.getParameter("displaySeq") : "";		
		try{	
			
			jsonTest = detailService.returnHtmlPage(displaySeq, "S");
			boolean fileCheck = detailService.ContentFileCreate(jsonTest, displaySeq, "S");
			if (fileCheck == true){
				LOGGER.debug("fileCheck:true" );
				detailService.ContentFileCreate(detailService.returnHtmlPage(displaySeq, "L"), displaySeq, "L");
			}			
		}catch(Exception e){
			LOGGER.debug("selectContentPreview error" + e.toString());
		}		
		return jsonTest;
	}
	
	@RequestMapping(value="disPlayUpdate.do")
	public ModelAndView displayUpdare(@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                          @RequestBody DispalyPageInfo vo	) throws Exception {
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	return model;
		    }
		    model=  displayService.updateDisplayReSendManage(vo.getDisplaySeq());
		}catch(Exception e){
			LOGGER.error("detailUpdate error:" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.request.msg"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		model.setViewName(Globals.JSONVIEW);
		return model;	
	}
	@RequestMapping(value="disPreview.do")
	public ModelAndView  selectDisPreview( HttpServletRequest request,
			                                                 @RequestParam Map<String, String> params) throws Exception {
		
		ModelAndView model = new ModelAndView("/backoffice/contentManage/disPreview");
		
		String displaySeq =  request.getParameter("displaySeq") != null ? request.getParameter("displaySeq") : "";
		String detailSeq_N =  request.getParameter("detailSeq_N") != null ? request.getParameter("detailSeq_N") : "";
		
		
		DispalyPageInfoVO  vo = new DispalyPageInfoVO();
		vo.setDisplaySeq(displaySeq);
		vo.setDetailSeq_N(detailSeq_N);
		DetailPageInfoVO detailVO = new DetailPageInfoVO();
		detailVO.setDisplaySeq(displaySeq);	
		detailVO.setReplacePath(propertiesService.getString("Globals.fileStorePathReplace"));
		model.addObject("regist", vo);
		List<Map<String, Object>> detailList = detailService.selectDisPlayPreviewList(detailVO);
		int totalCnt = detailList.size() > 0 ?  Integer.valueOf(  detailList.get(0).get("total_record_count").toString()) : 0;  
		model.addObject("resultDetailList",   detailList );
		model.addObject("totCnt", totalCnt);
		return model;
	}
	
	
	
	@RequestMapping(value="detailDelete.do")
	public ModelAndView detailDelete(@ModelAttribute("loginVO") AdminLoginVO loginVO,
							                         @RequestBody  DetailPageInfo vo,
				                                     HttpServletRequest request		) throws Exception {
						
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	return model;
		    }else {
		    	return detailService.deleteDetailPageInfoManage(vo);
		    	
		    }
	}
	@RequestMapping(value="detailTimeUpdate.do")
	public ModelAndView detailTimeChange(@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                             @RequestBody DetailPageInfo vo,
                                         HttpServletRequest request		) throws Exception {
		
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	return model;
		    }
		    
		    
			int ret = detailService.updateDetailTimeChangeManage(vo);			
			
			if (ret > 0){
				DispalyPageInfoVO disInfo =  displayService.selectDisplayPageInfoManageView(vo.getDisplaySeq() );
				model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("sucess.common.changeTime"));
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
				model.addObject("totalTime", disInfo.getDisplayTotalTime());
			}else{
				throw new Exception();
			}
		}catch(Exception e){
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.changeTime"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;	
	}
	@RequestMapping (value="detailOrderUpdatePage.do" ,  method = {RequestMethod.POST})
	public ModelAndView detailOrderUpdatePage (@ModelAttribute("loginVO") AdminLoginVO loginVO,
                                                                     @RequestBody List<DetailPageInfo> detailLists	) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if(!isAuthenticated) {
	    	ModelAndView model = new ModelAndView(Globals.JSONVIEW);
	    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
	    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
	    	return model;
	    }
	    return detailService.updateDitailOrderUpdatePage(detailLists);
		
	}
	@RequestMapping (value="detailOrder.do")
	public ModelAndView detailOrderChange (@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                               @RequestBody DetailPageInfoVO vo	) throws Exception {
		
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		
		
		try{
			
			
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	return model;
		    }
			
			int ret = detailService.updateDetailSortOrderInfoManage(vo);
		    if (ret > 0){
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.request.msg"));	
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		    	
		    }else {
		    	throw new Exception();
		    }
		}catch(Exception e){
			LOGGER.error("--------------------------------------------------------detailUpdate error:" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.request.msg"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;	
	}	
	
	@RequestMapping (value="detailTimeInfo.do")
	public ModelAndView selectDetailTimePage (@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                                  @RequestBody DetailPageInfo vo		) throws Exception {
		
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
		    
			DetailPageInfoVO detailPageInfo = (DetailPageInfoVO) detailService.selectDetailInfo(vo.getDetailSeq());        
			model.addObject("detailInfo", detailPageInfo); 
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);	
		}catch(Exception e){
			LOGGER.error("selectDetailTimePage error:" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.changeTime"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);	
		    //System.out.println("error:" + e.toString());
		}
		return model;
	}
	
	
	
	@RequestMapping (value="displayDetailInfo.do")
	public ModelAndView selectDetailPage (@ModelAttribute("loginVO") AdminLoginVO loginVO,
                                           HttpServletRequest request		) throws Exception {
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
			
		     String displaySeq =  request.getParameter("displaySeq") != null ? request.getParameter("displaySeq") : "";
		     DetailPageInfoVO searchVO = new DetailPageInfoVO();
		     searchVO.setDisplaySeq(displaySeq);
		     searchVO.setReplacePath(propertiesService.getString("Globals.fileStorePathReplace"));
		     model = detailService.selectDetailPageInfoManageListByPagination(searchVO); 
			
		}catch(Exception e){
			 model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
			 model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}
	@RequestMapping(value="displayCombo.do")
	public ModelAndView selectDisplayCombo (@ModelAttribute("loginVO") AdminLoginVO loginVO
		                                    , HttpServletRequest request
		                            		, BindingResult bindingResult ) throws Exception{	
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		String agentContentgubun = request.getParameter("agentContentgubun") != null ? request.getParameter("agentContentgubun") : "";
		DispalyPageInfoVO searchVO = new DispalyPageInfoVO();
		
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.setViewName("/backoffice/login");
		    		return model;
		    }else{
		    	 HttpSession httpSession = request.getSession(true);
		    	 loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
			     searchVO.setAdminLevel(loginVO.getAdminLevel());
			     searchVO.setPartId(loginVO.getPartId());
		    }
		     
			//여기 부분 나중에 수정 
		    model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		    if (!agentContentgubun.equals("AGENT_CONTENT_1")){
		    	searchVO.setDisplayGubun("DispalyGubun_2");
		    }
		   
		    model.addObject("disList", displayService.selectDisplayPageInfoCombo(searchVO));
		}catch(Exception e){
			LOGGER.debug("selecEqupInfoManageView :" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}
	//신규 작업
	@RequestMapping (value="displayView.do")
	public ModelAndView selecEqupInfoManageView(@ModelAttribute("loginVO") AdminLoginVO loginVO
		                                          , @ModelAttribute("DispalyPageInfoVO")  DispalyPageInfoVO vo
		                                          , HttpServletRequest request
		                            			  , BindingResult bindingResult ) throws Exception{	
		
		
		ModelAndView model = new ModelAndView("/backoffice/contentManage/displayView");
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
		    
	    }
		
		try{
			
			DispalyPageInfoVO disInfo = displayService.selectDisplayPageInfoManageView(vo.getDisplaySeq()); 
			model.addObject("regist", disInfo);
			
			if ( disInfo.getDisplayGubun().equals("DispalyGubun_2")){
				model.addObject("selectDisplayNextCombo", displayService.selectDisplayPageInfoCombo(vo));	
			}
		    
		    //변경 구문 
		    //추가 구문 넣기
			model.addObject("selectDisplayCombo", cmmnDetailService.selectCmmnDetailCombo("DispalyGubun"));
		    model.addObject("selectState", authorInfoManageService.selectAuthorIInfoManageCombo());		
			model.addObject("selectGroup", partService.selectPartInfoCombo());
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		}catch(Exception e){
			LOGGER.debug("selecEqupInfoManageView :" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL );
		}
		return model;
	}
	@RequestMapping (value="ajaxDisplayView.do")
	public ModelAndView selectajaxDisplayView(@ModelAttribute("loginVO") AdminLoginVO loginVO
													             , @RequestParam Map<String, Object> reportMap
													             , HttpServletRequest request
																 , BindingResult bindingResult ) throws Exception{	


			ModelAndView model = new ModelAndView(Globals.JSONVIEW);
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			ReportPageInfoVO searchVO = new ReportPageInfoVO();
			
			
			if(!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
				model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
				return model;
			}else{
				HttpSession httpSession = request.getSession(true);
				loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
				searchVO.setAdminLevel(loginVO.getAdminLevel());
				searchVO.setPartId(loginVO.getPartId());
			}
			//여기 부분 수정 
			searchVO.setPageIndex( Integer.valueOf(  reportMap.get("pageIndex").toString() ) );
			searchVO.setReportUseYn("Y");
			if (!reportMap.get("searchKeyword").equals("") ){
				searchVO.setSearchCondition(reportMap.get("searchCondition").toString());
				searchVO.setSearchKeyword(reportMap.get("searchKeyword").toString());
			}
            String displayGubun = reportMap.get("displayGubun") != null ? request.getParameter("displayGubun") : ""; 
			
            model = reportService.selectReportPageInfoManageListByPaginationAjax(searchVO, displayGubun);
			model.setViewName(Globals.JSONVIEW);
			
            return model;
	}
	//여기 구문 찾기 
	@RequestMapping (value="displayDelete.do", method = {RequestMethod.POST})
	public ModelAndView deleteEqupInfoManage(@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                                                        @RequestParam Map<String, String> params,
																	HttpServletRequest request	) throws Exception {
		
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
			
			String displaySeq =params.get("displaySeq");
			LOGGER.debug("displaySeq:" + displaySeq);
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	return model;
		    }
		    //연동 단말기 확인 후 업으면 삭제 
		    model = displayService.deleteDisplayPageInfoManage(displaySeq);
		    
		}catch (Exception e){
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.delete"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}	
		model.setViewName(Globals.JSONVIEW); 
		return model;
	}
	
	
	@RequestMapping (value="displayBasicUpdate.do")
	public ModelAndView updateequpInfoManage(@ModelAttribute("loginVO") AdminLoginVO loginVO,
										     @RequestBody DispalyPageInfoVO vo	
                                             , HttpServletRequest request                         				 
										     , BindingResult result) throws Exception{
		
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		//여기 부분도 수정 해야함 
		
		try{
			 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		     if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    		return model;
		     }
			
			vo.setUserId(EgovUserDetailsHelper.getAuthenticatedUser().toString());
			int ret  = displayService.updateDisplayPageInfoManage(vo);
			
			
			if (ret >0){
				String message = vo.getMode().equals("Ins") ? egovMessageSource.getMessage("sucess.common.insert") : egovMessageSource.getMessage("sucess.common.update");
				if (vo.getMode().equals("Ins")){
					String displaySeq = displayService.selectDisplayMaxSeq();
					vo.setDisplaySeq(displaySeq);
					model.addObject("regist", vo);
				}else {
					model.addObject("regist", displayService.selectDisplayPageInfoManageView(vo.getDisplaySeq()));
				}	
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
				model.addObject(Globals.STATUS_MESSAGE, message);
						
			}else {
				throw new Exception();
			}
			
		}catch (Exception e){
			LOGGER.error("updateequpInfoManage ERROR:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.insert"));			
		}		
		return model;	
	}
}
