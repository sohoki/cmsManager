package aten.com.backoffice.uat.web;

import java.util.List;

import aten.com.backoffice.uat.vo.AdminInfo;
import aten.com.backoffice.uat.service.AdminInfoManageService;
import aten.com.backoffice.uat.vo.AdminInfoVO;
import aten.com.backoffice.uat.service.PartInfoManageService;
import aten.com.backoffice.uat.vo.UniUtilInfo;
import egovframework.com.cmm.AdminLoginVO;
import aten.com.backoffice.sym.rnt.service.AuthorInfoManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.com.cmm.EgovMessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.service.Globals;

//@Controller
@RestController
@RequestMapping("/backoffice/basicManage")
public class AdminInfoManageController {

   private static final Logger LOGGER = LoggerFactory.getLogger(AdminInfoManageController.class);
	
	@Autowired
	private AdminInfoManageService userManagerService;
	
	@Autowired
	private AuthorInfoManageService authorInfoManageService;
	
	/** EgovPropertyService */
	@Autowired
    protected EgovPropertyService propertiesService;
       
	@Autowired
	protected EgovMessageSource egovMessageSource;
	
	@Autowired
	private PartInfoManageService partService;
	
	
	@RequestMapping(value="intro.do")
	public ModelAndView selectUserManagerList() throws Exception {
		ModelAndView model = new ModelAndView("/backoffice/intro");
		return model;		
	}
	
	@RequestMapping(value="empList.do")
	public ModelAndView selectUserManagerList( @ModelAttribute("searchVO") AdminInfoVO searchVO
												, HttpServletRequest request
												, BindingResult bindingResult) throws Exception {  
		
		ModelAndView model = new ModelAndView("/backoffice/basicManage/empList");
	   try{
		   
		  
		   //공용 확인 하기 
		   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	       if(!isAuthenticated) {
	    		model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
	    		model.setViewName("/backoffice/login");
	    		return model;
	       }
	       model.addObject("regist", searchVO);
		   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));          
	       searchVO.setPageSize(propertiesService.getInt("pageSize"));   
	       
	   	   PaginationInfo paginationInfo = new PaginationInfo();
		   paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		   
		   paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		   paginationInfo.setPageSize(searchVO.getPageSize());
		   searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		   searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		   searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		   
		   if (searchVO.getUseYn() == null){ searchVO.setUseYn("");}
		   
			
		   List<AdminInfoVO> adminList =  (List<AdminInfoVO>)userManagerService.selectAdminUserManageListByPagination(searchVO);
	       model.addObject("resultList",  adminList);      	       
	       int totCnt = adminList.size() > 0 ? adminList.get(0).getTotalRecordCount() : 0;  
	       paginationInfo.setTotalRecordCount(totCnt);
	       model.addObject("paginationInfo", paginationInfo);
	       model.addObject("totalCnt", totCnt);
	       model.addObject("selectState", authorInfoManageService.selectAuthorIInfoManageCombo());		
		   model.addObject("selectGroup", partService.selectPartInfoCombo());
	       
		   
	   }catch (Exception e){
			LOGGER.debug("selectUserManagerList error:" + e.toString());
			model.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
			model.addObject("status", Globals.STATUS_FAIL);
	   }
	   return model;
	}
	@RequestMapping(value="passChangeView.do")
	public ModelAndView pagssChangeView(@ModelAttribute("AdminInfo") AdminInfo vo, 
										    HttpServletResponse response,
										    HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView("/backoffice/basicManage/passChange");
		
		return model;
	}
	@RequestMapping(value="passChange.do")
    public ModelAndView updatePasswordChange( @ModelAttribute("AdminInfo") AdminInfo vo, 
										    HttpServletResponse response,
										    HttpServletRequest request) throws Exception {
			
		ModelAndView model = new ModelAndView("jsonView");
		
    	try{
    		int ret =  userManagerService.updatePassChange(vo);
        	if (ret > 0){
        		model.addObject("status", Globals.STATUS_SUCCESS);
        		model.addObject("message", egovMessageSource.getMessage("page.emp.message11"));
        	}else {
        		
        		throw new Exception();
        	}
    	}catch (Exception e ){
			model.addObject("status", Globals.STATUS_FAIL);
			model.addObject("message", egovMessageSource.getMessage("page.emp.message10"));
    	}
    	return model;
    }
	
	@RequestMapping(value="managerCheck.do")
	public ModelAndView userView( @RequestBody AdminInfoVO adminVO
												 , HttpServletRequest request
												, BindingResult bindingResult) throws Exception{
		
		//공용 확인 하기 
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
        	model.addObject("status", Globals.STATUS_LOGINFAIL);
    		model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
    		return model;
        }
        //신규 수정
        adminVO = adminVO.getMode().equals("Edt") ? userManagerService.selectAdminUserManageDetail(adminVO) : adminVO;
        
        //response rawData password 노출 방지
        adminVO.setAdminPassword("");
        adminVO.setAdminPassword2("");
        
        model.addObject("userInfo", adminVO); 
        model.addObject("status", Globals.STATUS_SUCCESS);
        
		return model;
	}
	@RequestMapping(value="managerUpdate.do")
	public ModelAndView mangerUpdate ( @ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
														   ,@RequestBody  AdminInfoVO vo
														   , BindingResult bindingResult) throws Exception{
		
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		
		model.addObject("regist", vo);
		String meesage = "";
		
		try{
			
			int ret = userManagerService.updateAdminUserManage(vo);
			meesage =vo.getMode().equals("Ins") ?"success.common.insert" : "success.common.update";
		    if (ret >0){
				model.addObject("status", Globals.STATUS_SUCCESS);
				model.addObject("message", egovMessageSource.getMessage(meesage));		
			}else {	
				throw new Exception();
			}
		}catch (Exception e){
			
			model.addObject("status", Globals.STATUS_FAIL);
			model.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
		}	
		return model;
	}
	@RequestMapping(value="managerDelete.do")
	public ModelAndView deleteMber(@RequestBody AdminInfo vo) throws Exception {
		
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
        
		UniUtilInfo utilInfo = new UniUtilInfo();
		utilInfo.setInTable("tb_managerinfo");
		utilInfo.setInCondition("admin_id=["+vo.getAdminId()+"[");
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    	model.addObject("status", Globals.STATUS_LOGINFAIL);
		    	model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
		    	return model;
		    }
		    
		    model.addObject("message", egovMessageSource.getMessage("success.request.msg"));	
			model.addObject("status", Globals.STATUS_SUCCESS); 
		    
		}catch (Exception e){
			//result = "F";
			model.addObject("message", egovMessageSource.getMessage("fail.request.msg"));	
			model.addObject("status", Globals.STATUS_FAIL);
		}
		return model;
	}
	@RequestMapping(value="IdCheck.do")
	public String selectUserMangerIDCheck(@RequestBody AdminInfo vo) throws Exception {
		int IDCheck = userManagerService.selectAdminUserMangerIDCheck(vo.getAdminId());		
		return Integer.toString( IDCheck ) ;
	}
	
}
