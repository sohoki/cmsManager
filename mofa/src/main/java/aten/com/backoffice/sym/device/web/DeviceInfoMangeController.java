package aten.com.backoffice.sym.device.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sym.device.service.DeviceInfoManageService;
import aten.com.backoffice.sym.device.vo.DeviceInfoVO;

import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@RestController
@RequestMapping("/backoffice/basicManage")
public class DeviceInfoMangeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInfoMangeController.class);

	@Autowired
	protected EgovMessageSource egovMessageSource;

	@Autowired
	protected EgovPropertyService propertiesService;

	@Autowired
	protected DeviceInfoManageService agentService;

	@RequestMapping(value = "devicelist.do")
	public ModelAndView selectAgentPreview(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("/backoffice/basicManage/agentInfoPreview");



		return model;
	}

	@RequestMapping(value = "dashboardInfo.do")
	public ModelAndView selectDashboard(@ModelAttribute("loginVO") AdminLoginVO loginVO,
										@ModelAttribute("searchVO") DeviceInfoVO searchVO,
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



/*	@RequestMapping(value = "AgentUpdateStateAjax.do")
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
	}*/
}
