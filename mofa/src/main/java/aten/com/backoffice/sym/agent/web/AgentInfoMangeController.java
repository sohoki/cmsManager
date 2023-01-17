package aten.com.backoffice.sym.agent.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import aten.com.backoffice.sts.xml.vo.SendMsgInfo;
import aten.com.backoffice.sts.xml.service.SendMsgInfoManageService;
import aten.com.backoffice.sym.agent.vo.AgentInfo;
import aten.com.backoffice.sym.agent.service.AgentInfoManageService;
import aten.com.backoffice.sym.agent.vo.AgentInfoVO;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import aten.com.backoffice.sym.cnt.service.CenterInfoService;
import aten.com.backoffice.sym.cnt.vo.CenterInfoVO;
import aten.com.backoffice.sym.monter.service.DetailPageInfoManageService;
import aten.com.backoffice.sym.monter.vo.DetailPageInfoVO;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfoVO;
import aten.com.backoffice.sym.monter.service.DisplayPageInfoManageService;
import aten.com.backoffice.uat.service.PartInfoManageService;
import aten.com.backoffice.uat.vo.UniUtilInfo;
import aten.com.backoffice.uat.service.UniUtilManageService;
import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@RestController
@RequestMapping("/backoffice/basicManage")
public class AgentInfoMangeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentInfoMangeController.class);

	@Autowired
	protected EgovMessageSource egovMessageSource;

	@Autowired
	protected EgovPropertyService propertiesService;

	@Autowired
	protected AgentInfoManageService agentService;

	@Autowired
	private EgovCcmCmmnDetailCodeManageService cmmnDetailService;

	@Autowired
	private DisplayPageInfoManageService disService;

	@Autowired
	private DetailPageInfoManageService detailService;

	@Autowired
	private UniUtilManageService utilService;

	@Autowired
	private CenterInfoService centerService;

	@Autowired
	private PartInfoManageService partService;

	@Autowired
	private SendMsgInfoManageService sendMsgService;

	/** TRACE */
	@Resource(name = "leaveaTrace")
	LeaveaTrace leaveaTrace;

	@RequestMapping(value = "AgentInfoPreview.do")
	public ModelAndView selectAgentPreview(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("/backoffice/contentManage/agentInfoPreview");

		String agentCode = request.getParameter("agentCode") != null ? request.getParameter("agentCode") : "";
		String detailSeq_N = request.getParameter("detailSeq_N") != null ? request.getParameter("detailSeq_N") : "";
		AgentInfoVO vo = agentService.selectAgentPageInfoManageDetail(agentCode);
		DetailPageInfoVO detailVO = new DetailPageInfoVO();
		
		vo.setDetailSeq_N(detailSeq_N);
		detailVO.setDisplaySeq(String.valueOf(vo.getDisplaySeq()));
		model.addObject("regist", vo);

		detailVO.setReplacePath(propertiesService.getString("Globals.fileStorePathReplace"));
		detailVO.setAgentCode(agentCode);
		model.addObject("resultDetailList",detailService.selectAgentPreviewList(detailVO));
		model.addObject("totCnt",detailService.selectDetailPageInfoManageListTotCnt_S(detailVO));

		return model;
	}

	@RequestMapping(value = "dashboardInfo.do")
	public ModelAndView selectDashboard(@ModelAttribute("loginVO") AdminLoginVO loginVO,
										@ModelAttribute("searchVO") AgentInfoVO searchVO,
										HttpServletRequest request, BindingResult bindingResult)
										throws Exception {

		ModelAndView model = new ModelAndView("/backoffice/operManage/dashBoardInfo");
		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			
			if (!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
				model.setViewName("/backoffice/login");
				return model;
			} else {
				HttpSession httpSession = request.getSession(true);
				loginVO = (AdminLoginVO) httpSession.getAttribute("AdminLoginVO");
				searchVO.setAdminLevel(loginVO.getAdminLevel());
				searchVO.setPartId(loginVO.getPartId());
			}
			
			if (searchVO.getPageUnit() > 0) {
				searchVO.setPageUnit(searchVO.getPageUnit());
			} else {
				searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			}
			
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
			searchVO.setSearchAgentState("F");
			searchVO.setSearchOrderGubun("CONN_DATE");
			
			model = agentService.selectAgentPageInfoManageListByPagination(searchVO);
			model.addObject("regist", searchVO);
			model.setViewName("/backoffice/operManage/dashBoardInfo");

		} catch (Exception e) {
			LOGGER.debug("ERROR");
		}
		return model;
	}

	@RequestMapping(value = "dashboardInfoAjax.do")
	public ModelAndView selectDashboardJson(@ModelAttribute("loginVO") AdminLoginVO loginVO,
											HttpServletRequest request, BindingResult bindingResult)
											throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		try {
			CenterInfoVO searchVO = new CenterInfoVO();
			// 상태 확인
			agentService.updateAgentState();
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
				model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
				return model;
			} else {
				HttpSession httpSession = request.getSession(true);
				loginVO = (AdminLoginVO) httpSession.getAttribute("AdminLoginVO");
				searchVO.setAdminLevel(loginVO.getAdminLevel());
				searchVO.setPartId(loginVO.getPartId());
			}

			if (searchVO.getPageUnit() > 0) {
				searchVO.setPageUnit(searchVO.getPageUnit());
			} else {
				searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			}
			
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
			model = agentService.selectAgentNowStateInnfo(searchVO);
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			model.setViewName(Globals.JSONVIEW);
		} catch (Exception e) {
			LOGGER.error("selectDashboardJson  error: " + e.toString());
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.select"));
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}

	@RequestMapping("restartAgentInfo.do")
	public ModelAndView restartAgentInfo(HttpServletRequest request,
											@RequestParam(value = "RestartDidInfo") String RestartDidInfo,
											@RequestParam(value = "xmlProceNm") String xmlProceNm)
											throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
			model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			return model;
		}

		String[] AgentArrays = RestartDidInfo.split(",");
		List<SendMsgInfo> sminfoLists = new ArrayList<SendMsgInfo>();

		for (String AgentArray : AgentArrays) {
			try {
				if (AgentArray.contains("/")) {
					SendMsgInfo sminfo = new SendMsgInfo();
					String[] agentInfo = AgentArray.split("/");
					String agentCode = agentInfo[0] != null ? agentInfo[0] : "";
					String agentMac = agentInfo[1] != null ? agentInfo[1] : "";
					sminfo.setAgentCode(agentCode);
					sminfo.setAgentMac(agentMac);
					sminfo.setXmlProcessName(xmlProceNm);
					sminfo.setFrstRegisterId(EgovUserDetailsHelper.getAuthenticatedUser().toString());
					sminfo.setSendResult("N");
					sminfoLists.add(sminfo);
				}
			} catch (Exception e) {
				LOGGER.debug("error:" + e.toString());
			}
		}
		
		model = sendMsgService.insertSendMsgInfoManageList(sminfoLists);
		model.setViewName(Globals.JSONVIEW);

		LOGGER.debug("model:" + model.toString());
		return model;
	}

	@RequestMapping(value = "AgentInfoCenterList.do")
	public ModelAndView selectAgentCenterInfoList(@ModelAttribute("loginVO") AdminLoginVO loginVO,
													@RequestBody AgentInfoVO searchVO, HttpServletRequest request,
													BindingResult bindingResult) 
													throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
				model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
				return model;
			} else {
				HttpSession httpSession = request.getSession(true);
				loginVO = (AdminLoginVO) httpSession.getAttribute("AdminLoginVO");
				searchVO.setAdminLevel(loginVO.getAdminLevel());
				searchVO.setPartId(loginVO.getPartId());
			}
			
			searchVO.setRecordCountPerPage(propertiesService.getInt("AgentPageSize"));
			// searchVO.setPageIndex(0);

			model = agentService.selectAgentPageInfoManageListByPagination(searchVO);
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("success.common.select"));
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			model.setViewName(Globals.JSONVIEW);
		} catch (Exception e) {
			LOGGER.error("deleteEqupInfoManage  error: " + e.toString());
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.delete"));
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}

	@RequestMapping(value = "AgentInfoList.do")
	public ModelAndView selectEqupInfoManageListByPagination(@ModelAttribute("loginVO") AdminLoginVO loginVO,
																@ModelAttribute("searchVO") AgentInfoVO searchVO,
																HttpServletRequest request,
																BindingResult bindingResult) 
																throws Exception {
		ModelAndView model = new ModelAndView("/backoffice/basicManage/agentInfoList");
		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			CenterInfoVO centerInfo = new CenterInfoVO();

			LOGGER.debug("searchCenterid : " + searchVO.getSearchCenterid());
			
			if (!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
				model.setViewName("/backoffice/login");
				return model;
			} else {
				HttpSession httpSession = request.getSession(true);
				loginVO = (AdminLoginVO) httpSession.getAttribute("AdminLoginVO");
				searchVO.setAdminLevel(loginVO.getAdminLevel());
				searchVO.setPartId(loginVO.getPartId());
				centerInfo.setAdminLevel(loginVO.getAdminLevel());
				centerInfo.setPartId(loginVO.getPartId());
			}

			if (searchVO.getPageUnit() > 0) {
				searchVO.setPageUnit(searchVO.getPageUnit());
			} else {
				searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			}
			
			LOGGER.error("getPageUnit:" + searchVO.getPageUnit());
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
			// 에이전트 상태값
			searchVO.setErrorCnt(propertiesService.getInt("agentErrCnt"));
			
			model = agentService.selectAgentPageInfoManageListByPagination(searchVO);
			model.addObject("regist", searchVO);
			model.addObject("selectCenterCombo",centerService.selectCenterCombo(centerInfo));
			model.addObject("selectGroupCombo",partService.selectPartInfoCombo());
			model.addObject("selectAgentGubun",cmmnDetailService.selectCmmnDetailCodeList("OS_TYPE"));
			model.addObject("selectAgentContentGubun",cmmnDetailService.selectCmmnDetailCodeList("AGENT_CONTENT"));
			model.setViewName("/backoffice/basicManage/agentInfoList");
		} catch (Exception e) {
			LOGGER.error("selectEqupInfoManageListByPagination error : " + e.toString());
		}

		return model;
	}

	// 신규 생성 구문
	@RequestMapping(value = "AgentInfoListAjax.do")
	public ModelAndView selectAgentListByPagination(@ModelAttribute("loginVO") AdminLoginVO loginVO,
													@RequestParam Map<String, Object> commandMap,
			HttpServletRequest request, BindingResult bindingResult)
			throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
			model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			return model;
		} else {
			HttpSession httpSession = request.getSession(true);
			loginVO = (AdminLoginVO) httpSession.getAttribute("AdminLoginVO");
			commandMap.put("adminLevel", loginVO.getAdminLevel());
			commandMap.put("partId", loginVO.getPartId());
			commandMap.put("recordCountPerPage",propertiesService.getInt("pageUnit"));
			commandMap.put("agentContentGubun", commandMap.get("displayGubun").equals("DispalyGubun_1") ? "AGENT_CONTENT_1" : "AGENT_CONTENT_2");

			LOGGER.debug("commandMap:" + commandMap.toString());
			model = agentService.selectDisplayStageChangePageList(commandMap);
			model.setViewName(Globals.JSONVIEW);
		}
		return model;
	}

	@RequestMapping(value = "AgentUpdateStateAjax.do")
	public ModelAndView updateAgentState(@ModelAttribute("loginVO") AdminLoginVO loginVO,
											@RequestBody AgentInfo vo, HttpServletRequest request,
											BindingResult bindingResult) 
											throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
			model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			return model;
		}

		model = agentService.updateAgentDisplayChange(vo);
		model.setViewName(Globals.JSONVIEW);
		return model;
	}

	// 신규 끝 부분
	@RequestMapping(value = "agentInfoDetail.do")
	public ModelAndView selecSeatInfoManageDetail(@ModelAttribute("loginVO") AdminLoginVO loginVO,
													@RequestBody AgentInfoVO vo, 
													HttpServletRequest request,
													BindingResult bindingResult) 
													throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
			model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			return model;
		}
		
		model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		model.addObject("agentInfo",agentService.selectAgentPageInfoManageDetail(vo.getAgentCode()));

		return model;
	}

	@RequestMapping(value = "agentInfoView.do")
	public ModelAndView selecEqupInfoManageView(
			@ModelAttribute("loginVO") AdminLoginVO loginVO,
			@ModelAttribute("searchVO") AgentInfoVO vo,
			HttpServletRequest request, BindingResult bindingResult)
			throws Exception {

		ModelAndView model = new ModelAndView("/backoffice/basicManage/agentInfoView");
		DispalyPageInfoVO searchVO = new DispalyPageInfoVO();
		CenterInfoVO centerInfo = new CenterInfoVO();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
			model.setViewName("/backoffice/login");
			return model;
		} else {
			HttpSession httpSession = request.getSession(true);
			loginVO = (AdminLoginVO) httpSession.getAttribute("AdminLoginVO");
			searchVO.setAdminLevel(loginVO.getAdminLevel());
			searchVO.setPartId(loginVO.getPartId());
			centerInfo.setAdminLevel(loginVO.getAdminLevel());
			centerInfo.setPartId(loginVO.getPartId());
		}

		vo.setErrorCnt(propertiesService.getInt("agentErrCnt"));
		if (!vo.getAgentContentgubun().equals("AGENT_CONTENT_1")) {
			searchVO.setDisplayGubun("DispalyGubun_2");
		}

		model.addObject("selectCodeDM",disService.selectDisplayPageInfoCombo(searchVO));
		model.addObject("selectCenterCombo",centerService.selectCenterCombo(centerInfo));
		model.addObject("selectGroupCombo", partService.selectPartInfoCombo());
		model.addObject("selectAgentGubun",cmmnDetailService.selectCmmnDetailCodeList("OS_TYPE"));
		model.addObject("selectAgentContentGubun",cmmnDetailService.selectCmmnDetailCodeList("AGENT_CONTENT"));
		model.addObject("search", vo);
		model.addObject("regist",agentService.selectAgentPageInfoManageView(vo));
		return model;
	}

	@RequestMapping(value = "agentInfoDelete.do")
	public ModelAndView deleteEqupInfoManage(@ModelAttribute("loginVO") AdminLoginVO loginVO,
												@RequestBody AgentInfo vo) 
												throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);

		UniUtilInfo utilInfo = new UniUtilInfo();
		utilInfo.setInTable("tb_agentinfo");
		utilInfo.setInCondition("AGENT_CODE=[" + vo.getAgentCode() + "[");
		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
				model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
				return model;
			}

			int ret = utilService.deleteUniStatement(utilInfo);

			if (ret > 0) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("success.common.delete"));
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			} else {
				throw new Exception();
			
			}
		} catch (Exception e) {
			LOGGER.error("deleteEqupInfoManage error : " + e.toString());
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.delete"));
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}

	@RequestMapping(value = "agentInfoUpdate.do")
	public ModelAndView updateequpInfoManage(@ModelAttribute("loginVO") AdminLoginVO loginVO,
												@RequestBody AgentInfoVO vo, 
												HttpServletRequest request,
												BindingResult result) 
												throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);

		model.addObject("regist", vo);
		String meesage = "";

		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.login"));
				model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
				return model;
			}
			vo.setUserId(EgovUserDetailsHelper.getAuthenticatedUser().toString());

			int ret = 0;
			meesage = vo.getMode().equals("Ins") ? "sucess.common.insert" : "sucess.common.update";
			
			ret = agentService.updateAgentPageInfoManage(vo);
			if (ret > 0) {
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
				model.addObject("agentCode", vo.getAgentCode());
				model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage(meesage));
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			LOGGER.error("updateequpInfoManage ERROR:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.update"));
		}
		return model;
	}
}
