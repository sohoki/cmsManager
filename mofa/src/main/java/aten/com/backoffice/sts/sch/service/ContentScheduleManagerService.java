package aten.com.backoffice.sts.sch.service;

import org.springframework.web.servlet.ModelAndView;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfo;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfoVO;

public interface ContentScheduleManagerService {

	
	ModelAndView selectContentSchduleInfoManageListByPagination(ContentScheduleInfoVO searchVO) throws Exception;
	
	ContentScheduleInfoVO  selectConetntSchduleInfoManageView(String conschCode) throws Exception;
	
	String conPageDetail(String displaySeq, String fileGubun)throws Exception;
	
	int updateContentSchduleInfoManage(ContentScheduleInfo vo) throws Exception;
	
	int deleteContentSchduleInfoManage(String conschCode) throws Exception;
	
}
