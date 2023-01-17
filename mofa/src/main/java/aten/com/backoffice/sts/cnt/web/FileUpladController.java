package aten.com.backoffice.sts.cnt.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.AdminLoginVO;
import aten.com.backoffice.sts.cnt.vo.ContentFileInfoVO;
import aten.com.backoffice.sts.cnt.service.ContentFileInfoManageService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class FileUpladController {
	/** EgovPropertyService */
    @Autowired
	private ContentFileInfoManageService conFileService;
    
    
	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String dragAndDrop(Model model) {         
        return "fileUpload";         
    }
	

	@RequestMapping(value = "/backoffice/upload/fileUpload.do") //ajax에서 호출하는 부분
	@ResponseBody
    public Map<String, String> upload(MultipartHttpServletRequest multipartRequest
    		          , HttpServletRequest request) throws Exception { 
		
			   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	           if(!isAuthenticated) {
	        	   Map<String, String> map = new HashMap<String, String>();
	               map.put("message", "fail Login Error");
	               return map;
	           }else {
	               return conFileService.fileUpload(multipartRequest);
	           }	        	        
    }	
	@RequestMapping(value="/backoffice/popup/fileView.do")
	public String selectFileView (@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
			                                   , ContentFileInfoVO vo
								               , HttpServletRequest request
											   , BindingResult bindingResult
											   , ModelMap model) throws  Exception{			
		   String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		   model.addAttribute("regist",  conFileService.selectFileDetail(atchFileId));				
		   return "/backoffice/popup/FileView";
	}
	@RequestMapping(value="/backoffice/sub/conManage/conFileUpdate.do")
	@ResponseBody
	public String conMutiDetail ( @ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
								  , ContentFileInfoVO vo
					              , HttpServletRequest request
								  , BindingResult bindingResult
								  , ModelMap model)throws Exception{
		String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		String playTime = request.getParameter("playTime") != null ? request.getParameter("playTime") : "";
		String fileWidth = request.getParameter("fileWidth") != null ? request.getParameter("fileWidth") : "";
		String fileHeight = request.getParameter("fileHeight") != null ? request.getParameter("fileHeight") : "";
		
		
		vo.setAtchFileId(atchFileId);
		if (!playTime.equals("") && playTime != null){
		    vo.setPlayTime(Integer.toString((int)( Double.parseDouble( playTime))));
		}
		vo.setFileWidth(fileWidth);
		vo.setFileHeight(fileHeight);
		String delResult = "";		
		
		int ret = conFileService.updateFileDetailInfo(vo) ;
		if (ret > 0){
			delResult="O";   	
		}else {
			delResult="F";
		}		
		return delResult; 
		
		
	}
	
}
