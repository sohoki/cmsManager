package aten.com.backoffice.sym.ccm.cde.web;

import java.util.List;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.service.Globals;
import aten.com.backoffice.sym.ccm.cde.service.CmmnDetailCode;
import aten.com.backoffice.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import aten.com.backoffice.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/backoffice/basicManage")
public class EgovCcmCmmnDetailCodeManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCcmCmmnDetailCodeManageController.class);
	
	
	@Resource(name = "CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
    private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	@Resource(name = "CmmnCodeManageService")
    private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/*private List<?> rightList(String codeId)throws Exception{
		List<CmmnDetailCode> codeDetail = (List<CmmnDetailCode>) cmmnDetailCodeManageService.selectCmmnDetailCodeList(codeId);
		return codeDetail;
	}*/
	@SuppressWarnings("unchecked")
    @RequestMapping(value="codeDetailCodeDelete.do")
	public ModelAndView deleteCmmnDetailCode (@ModelAttribute("loginVO") AdminLoginVO loginVO,
			                                  @RequestBody CmmnDetailCode vo,
			                                  ModelMap modelMe,
			                                  HttpServletRequest request) throws Exception {
    	
    	
    	ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
    	try{
    		
    		
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if(!isAuthenticated) {
            	modelMe.addAttribute(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
            	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
        		return model;
            }
            
    		
    		int ret = cmmnDetailCodeManageService.deleteCmmnDetailCode(vo.getCode());
    		model.addObject("resutl", ret );   	
    		
    		
			List<CmmnDetailCode> codeDetailList = (List<CmmnDetailCode>) cmmnDetailCodeManageService.selectCmmnDetailCodeList(vo.getCodeId());
            int totCnt = codeDetailList.size() > 0 ? codeDetailList.size()  : 0;
            model.addObject("cmDetailLst", codeDetailList);
    	    model.addObject("totCnt", totCnt);
    		
    	}catch(Exception e){
    		
    	}
    	return model;
	}

	/**
	* 공통상세코드 상세항목을 조회한다.
	* @param loginVO
	 * @param cmmnDetailCode
	 * @param model
	 * @return "cmm/sym/ccm/EgovCcmCmmnDetailCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="CcmCmmnDetailCodeDetail.do")	
 	public ModelMap selectCmmnDetailCodeDetail (@ModelAttribute("loginVO") AdminLoginVO loginVO
									 			, CmmnDetailCode cmmnDetailCode
									 			, ModelMap model)	throws Exception {
    	CmmnDetailCode vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCode);
		model.addAttribute("result", vo);
		return model;
	}
	
    /**
	 * 공통상세코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model 
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
    @RequestMapping(value="CmmnDetailCodeList.do")
    public ModelAndView selectCmmnDetailCodeList ( @ModelAttribute("AdminLoginVO") AdminLoginVO loginVO,
    		                                       @RequestBody CmmnDetailCode vo, 
    		                                       HttpServletRequest request)  {
    	//나중에 권한 설정 값 넣기 
    	
    	
    	ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
    	try{
    		
            List<CmmnDetailCode> codeDetailList = (List<CmmnDetailCode>) cmmnDetailCodeManageService.selectCmmnDetailCodeList(vo.getCodeId());
            int totCnt = codeDetailList.size() > 0 ? codeDetailList.size()  : 0;
            model.addObject("cmDetailLst", codeDetailList);
            model.addObject("totalCnt", totCnt);
            return model;
    	}catch(Exception e){
    		return model;
    	}    	
	}
    @RequestMapping(value="CmmnDetailView.do")
    public ModelAndView CmmnDetailView (@ModelAttribute("loginVO") AdminLoginVO loginVO
												             , @RequestBody CmmnDetailCode vo
    		                                               //  , @RequestParam(value="code") String code
															  , BindingResult bindingResult ) throws Exception {
    	     ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
    	     try{
    	    	 model.addObject("detaulCode", cmmnDetailCodeManageService.selectCmmnDetail(vo.getCode())  );
        	     model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
    	     }catch(Exception e){
    	    	 LOGGER.error("CmmnDetailView ERROR:" + e.toString());
    	    	 model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
    	    	 model.addObject("message", egovMessageSource.getMessage("fail.common.select"));
    	     }
    	    return model;
    }
    @SuppressWarnings("unchecked")
    @RequestMapping(value="CodeDetailUpdate.do")
	public ModelAndView updateCmmnDetailCode (@ModelAttribute("loginVO") AdminLoginVO loginVO
			                                  , @RequestBody CmmnDetailCode vo
			                                  , ModelMap modelMe
											  , BindingResult bindingResult ) throws Exception {
    	
    	ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
        	model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
	    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
    		return model;
        }
    	vo.setLastUpdusrId(EgovUserDetailsHelper.getAuthenticatedUser().toString());
    	int ret  = cmmnDetailCodeManageService.updateCmmnDetailCode(vo);
    	
   	    List<CmmnDetailCode> codeDetailList = (List<CmmnDetailCode>) cmmnDetailCodeManageService.selectCmmnDetailCodeList(vo.getCodeId());
   	    int totCnt = codeDetailList.size() > 0 ? codeDetailList.size()  : 0;
        model.addObject("cmDetailLst", codeDetailList);
        model.addObject("totalCnt", totCnt);
        model.addObject("result", ret);
    	return model;
    }
}
