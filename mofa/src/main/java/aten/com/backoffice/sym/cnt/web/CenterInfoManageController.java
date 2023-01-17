package aten.com.backoffice.sym.cnt.web;

import aten.com.backoffice.etc.rest.service.RestInfoManageService;
import aten.com.backoffice.etc.rest.service.RestInfoVO;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import aten.com.backoffice.sym.cnt.vo.CenterInfo;
import aten.com.backoffice.sym.cnt.service.CenterInfoService;
import aten.com.backoffice.sym.cnt.vo.CenterInfoVO;
import aten.com.backoffice.uat.service.PartInfoManageService;
import aten.com.backoffice.util.web.service.fileService;
import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "/backoffice/basicManage" })
public class CenterInfoManageController {
	
	
	private static final Logger LOGGER = LoggerFactory .getLogger(CenterInfoManageController.class);

	@Autowired
	protected EgovMessageSource egovMessageSource;

	@Autowired
	protected EgovPropertyService propertiesService;

	@Autowired
	private PartInfoManageService partService;

	@Autowired
	private CenterInfoService centerService;

	@Autowired
	private EgovCcmCmmnDetailCodeManageService detailService;

	@Autowired
	private RestInfoManageService restService;

	fileService uploadFile = new fileService();

	@RequestMapping({ "centerList.do" })
	public ModelAndView selectCenterInfoManageListByPagination(@ModelAttribute("loginVO") AdminLoginVO loginVO,
																			   @ModelAttribute("searchVO") CenterInfoVO searchVO,
																			   HttpServletRequest request, 
																			   BindingResult bindingResult) throws Exception {
		
		    ModelAndView model = new ModelAndView("/backoffice/basicManage/centerList");
			try {
				 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			     if(!isAuthenticated) {
			    		model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
			    		model.setViewName("/backoffice/login");
			    		return model;
			     }else{
			    	 HttpSession httpSession = request.getSession(true);
			    	 loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
				     searchVO.setAdminLevel(loginVO.getAdminLevel());
				     searchVO.setPartId(loginVO.getPartId());
			     }
	
				searchVO.setAdminLevel(loginVO.getAdminLevel());
				searchVO.setPartId(loginVO.getPartId());
				model = centerService.selectCenterInfoPageInfoManageListByPagination(searchVO);
				
			} catch (Exception e) {
				LOGGER.error("ERROR:" + e.toString());
			}
		    return model;
	}

	@RequestMapping({ "centerDetail.do" })
	public String selectCenterInfoManageDetail(@ModelAttribute("loginVO") AdminLoginVO loginVO,
															    @ModelAttribute("CenterInfoVO") CenterInfoVO vo,
															    HttpServletRequest request, 
															    BindingResult bindingResult,
															    ModelMap model) throws Exception {
		
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated.booleanValue()) {
			model.addAttribute("message",this.egovMessageSource.getMessage("fail.common.login"));
			return "/backoffice/login";
		}

		model.addAttribute("regist", vo);
		model.addAttribute("selectFloorCombo", this.detailService.selectCmmnDetailCombo("CenterFloor"));
		model.addAttribute("selectFloorEndCombo", this.detailService.selectCmmnDetailCombo("CenterFloor"));

		model.addAttribute("selectGroupCombo", this.partService.selectPartInfoCombo());

		if (!vo.getMode().equals("Ins")) {
			model.addAttribute("regist", this.centerService.selectCenterInfoDetail(vo.getCenterId()));
		}
		return "/backoffice/basicManage/centerDetail";
	}

	@RequestMapping({ "centerView.do" })
	public ModelAndView selectCenterInfoManageView(@ModelAttribute("loginVO") AdminLoginVO loginVO,
																		    @ModelAttribute("CenterInfoVO") CenterInfoVO vo,
																		    HttpServletRequest request, 
																		    BindingResult bindingResult) throws Exception {
		
		
		ModelAndView model = new ModelAndView("/backoffice/basicManage/centerView");
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated.booleanValue()) {
				model.addObject("message", this.egovMessageSource.getMessage("fail.common.login"));
				model.setViewName("/backoffice/login");
	    		return model;
			}
			model.addObject("regist", vo);
			if (!vo.getMode().equals("Ins")){
				model.addObject("registinfo", centerService.selectCenterInfoDetail(vo.getCenterId()));
			}
			model.addObject("selectFloorCombo", this.detailService.selectCmmnDetailCombo("CenterFloor"));
			model.addObject("selectFloorEndCombo", this.detailService.selectCmmnDetailCombo("CenterFloor"));
			model.addObject("selectGroupCombo", this.partService.selectPartInfoCombo());
		}catch(Exception e){
		    	LOGGER.error("selectCenterInfoManageView ERROR:" + e.toString());
		}
		return model;
	}

	@RequestMapping({ "centerInfo.do" })
	public ModelAndView selectCenterInfo(@ModelAttribute("loginVO") AdminLoginVO loginVO,
														  @RequestBody CenterInfo vo, HttpServletRequest request,
														  BindingResult bindingResult) throws Exception {
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);

		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated.booleanValue()) {
				model.addObject("status", Globals.STATUS_LOGINFAIL);
				model.addObject("message", this.egovMessageSource.getMessage("fail.common.login"));
				return model;
			}
			
			model.addObject("status", Globals.STATUS_SUCCESS);
			model.addObject("regist", this.centerService.selectCenterInfoDetail(vo.getCenterId()));
		} catch (Exception e) {
			model.addObject("message", this.egovMessageSource.getMessage("fail.request.msg"));
			model.addObject("status", Globals.STATUS_FAIL);
		}

		return model;
	}
	
	@RequestMapping(value="centerCombo.do")
	public ModelAndView selectCenterCombo (@ModelAttribute("loginVO") AdminLoginVO loginVO,
											@RequestBody CenterInfoVO vo,
											HttpServletRequest request,
											BindingResult bindingResult ) throws Exception {	
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    	model.setViewName("/backoffice/login");
		    	return model;
		    } else {
		    	 HttpSession httpSession = request.getSession(true);
		    	 loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
		    }
		      
		    model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		    model.addObject("resultList", centerService.selectCenterCombo(vo));
		} catch(Exception e) {
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		return model;
	}
	
	@RequestMapping({ "centerUpdate.do" })
	public String updateCenterInfoManage(HttpServletRequest request,
															MultipartRequest mRequest,
															@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO,
															@ModelAttribute("CenterInfoVO") CenterInfoVO vo,
															BindingResult result, ModelMap model) throws Exception {
		
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated.booleanValue()) {
				model.addAttribute("message",
						this.egovMessageSource.getMessage("fail.common.login"));
				return "/backoffice/login";
			}
		}catch(Exception e){
			
		}
		
		vo.setCenterUpdateId(EgovUserDetailsHelper.getAuthenticatedUser().toString());
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/basicManage/centerList.do";

		String realFolder = this.propertiesService.getString("Globals.fileStorePath");

		vo.setCenterZipcode(String.valueOf(vo.getCenterZipcode1())+ vo.getCenterZipcode2());
		//
		vo.setCenterImg(this.uploadFile.uploadFileNm(mRequest.getFiles("centerImg"), realFolder));
		vo.setCenterSeatImg(this.uploadFile.uploadFileNm(mRequest.getFiles("centerSeatImg"), realFolder));
		vo.setCenterUpdateId(loginVO.getAdminId());

		try {
			int ret = this.centerService.updateCenterInfoManage(vo);
			if (ret > 0) {
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				meesage = vo.getMode().equals("Ins") ? "success.common.insert": "success.common.update";
				model.addAttribute("message",	this.egovMessageSource.getMessage(meesage));
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			LOGGER.debug("error:" + e.toString());
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", this.egovMessageSource.getMessage("fail.common.insert"));
		} finally {
		}
		return url;
	}

	@RequestMapping({ "restPageInfoUpdate.do" })
	public String updateRestInfo(@ModelAttribute("loginVO") AdminLoginVO loginVO,
											@ModelAttribute("vo") RestInfoVO vo, MultipartRequest mRequest,
											HttpServletRequest request, 
											BindingResult bindingResult,
											ModelMap model) throws Exception {
		
		
		String url = "redirect:/backoffice/RestManage/restPageInfoList.do";
		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated.booleanValue()) {
				model.addAttribute("message",
						this.egovMessageSource.getMessage("fail.common.login"));
				return "/backoffice/login";
			}

			LOGGER.debug("menuInfo:" + vo.getMenuInfo());

			String realFolder = this.propertiesService
					.getString("Globals.fileStorePath");

			vo.setMenuImg(this.uploadFile.uploadFileNm(
					mRequest.getFiles("centerImg"), realFolder));
			vo.setUserId(EgovUserDetailsHelper.getAuthenticatedUser()
					.toString());
			LOGGER.debug("menuInfo:" + vo.getMenuImg());

			String meesage = vo.getMode().equals("Ins") ? "sucess.common.insert"
					: "sucess.common.update";
			int ret = this.restService.updateRestInfo(vo);
			if (ret > 0) {
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", this.egovMessageSource.getMessage(meesage));
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("updateRestInfo error:" + e.toString());
			model.addAttribute("message",
					this.egovMessageSource.getMessage("fail.common.select"));
			
			model.addAttribute("status", Globals.STATUS_FAIL);
		}
		return url;
	}
}
