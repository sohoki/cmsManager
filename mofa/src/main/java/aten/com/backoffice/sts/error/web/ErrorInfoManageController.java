package aten.com.backoffice.sts.error.web;

import aten.com.backoffice.sts.error.vo.ErrorInfoVO;
import aten.com.backoffice.sts.error.service.ErrorInfoManageService;
import egovframework.com.cmm.AdminLoginVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@RestController
@RequestMapping("/backoffice/*")
public class ErrorInfoManageController {

	@Autowired
	private ErrorInfoManageService errService;
	
	
	@RequestMapping("errList")
	public String selectT(@RequestBody ErrorInfoVO searchVO)throws Exception{
		
        JSONObject datas = new JSONObject();
//		JSONObject result = new JSONObject();
		JSONObject data_list = new JSONObject();
		try{
			
			datas.put("result", true);
//			JSONObject paginationInfo = new JSONObject();
			JSONObject pagination = new JSONObject();
			pagination.put("page", searchVO.getPageIndex());
			pagination.put("totalCount",  errService.selectErrorMessageCnt(searchVO));
			data_list.put("contents", errService.selectErrorMessage(searchVO));
			data_list.put("pagination", pagination);
			
			datas.put("data", data_list);
			
		}catch (Exception e){
			datas.put("result", true);
			datas.put("data", null);
			datas.put("message", e.toString());
		}
		return datas.toString();
	}
	@RequestMapping(value="errList.do")
	public ModelAndView selectErrorData( @ModelAttribute("loginVO") AdminLoginVO loginVO 
                                        )throws Exception{
		ModelAndView mav = new ModelAndView("/backoffice/operManage/logList");
		return mav;
	}
	
	@RequestMapping("errListAjax.do")
	public String selectErrorData( AdminLoginVO loginVO ,
			                       ErrorInfoVO searchVO )throws Exception{
		JSONObject datas = new JSONObject();
		
//		JSONObject result = new JSONObject();
		JSONObject data_list = new JSONObject();
		try{
			datas.put("result", true);
			
			List<ErrorInfoVO> jsonList = errService.selectErrorMessage(searchVO);
			JSONArray contentList =  JSONArray.fromObject(jsonList);
			
//			JSONObject paginationInfo = new JSONObject();
			JSONObject pagination = new JSONObject();
			pagination.put("page", searchVO.getPageIndex());
			pagination.put("totalCount",  errService.selectErrorMessageCnt(searchVO));
			data_list.put("contents", contentList );
			data_list.put("pagination", pagination);
			
			datas.put("data", data_list);
			
		}catch (Exception e){
			datas.put("result", true);
			datas.put("data", null);
			datas.put("message", e.toString());
		}
		return datas.toString();
	}
}
