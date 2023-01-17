package aten.com.backoffice.sts.xml.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sts.xml.vo.SendMsgInfo;
import aten.com.backoffice.sts.xml.vo.SendMsgInfoVO;

public interface SendMsgInfoManageService {

    ModelAndView selectSendMsgInfoManageListByPagination(SendMsgInfoVO searchVO) throws Exception;  
	
	int selectAgentOrderCount(SendMsgInfo searchVO)throws Exception;    
	
	int selectAgentMessageCount (SendMsgInfoVO searchVO)throws Exception;    
	
	List<SendMsgInfoVO>  selectAgentOrderLst(SendMsgInfo searchVO)throws Exception;    
	
	ModelAndView insertSendMsgInfoManageList(List<SendMsgInfo> vo)throws Exception;  
	
	int insertSendMsgInfoManage(SendMsgInfo vo)throws Exception;  
	
	int updateSendMsgInfoManage(SendMsgInfo vo)throws Exception;


}
