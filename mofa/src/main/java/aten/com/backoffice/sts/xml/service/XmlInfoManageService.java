package aten.com.backoffice.sts.xml.service;

import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sts.xml.vo.XmlInfo;
import aten.com.backoffice.sts.xml.vo.XmlInfoVO;

public interface XmlInfoManageService {

	 ModelAndView selectXmlInfoManageListByPagination(  XmlInfoVO searchVO) throws Exception ;
		
	 XmlInfoVO selectXmlrInfoManageDetail(String xmlSeq) throws Exception;
	 
	 ModelAndView selectXmlrInfoManageDetail_JSONVIEW(String xmlSeq) throws Exception;
	 
	 String selectXmlrInfoManageDetail_JSONVIEW_RESULT(String json) throws Exception;
	 
	 XmlInfoVO selectXmlrInfoManageNameDetail(String xmlProcessName)throws Exception;
	
	 int selectXmlInfoManageListTotCnt_S(  XmlInfoVO searchVO) throws Exception;
	
	 ModelAndView updateXmlInfoManage(  XmlInfo vo) throws Exception;
	
	 int deleteXmlInfoManage(String xmlSeq) throws Exception;
	 
	 int selectXmlProcessCount(String xmlProcessName) throws Exception;
	 
	 String selectDIDProcessNm (String code) throws Exception;
}
