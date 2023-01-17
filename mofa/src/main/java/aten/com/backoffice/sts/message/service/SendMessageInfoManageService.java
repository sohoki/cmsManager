package aten.com.backoffice.sts.message.service;

import java.util.List;
import java.util.Map;

import aten.com.backoffice.sts.message.vo.SendMessageInfo;
import aten.com.backoffice.sts.message.vo.SendMessageInfoVO;

public interface SendMessageInfoManageService {
	
	
	List<Map<String, Object>> selectSendMessageAgentList (SendMessageInfoVO vo) throws Exception;
	
	List<SendMessageInfoVO> selectSendMessageAgentHistoryList(SendMessageInfoVO vo) throws Exception;
	
    int insertSendMessage (SendMessageInfoVO vo) throws Exception;
	
	int updateSendMessage (String schCode) throws Exception;
	
	int updateSendMessageAgent(SendMessageInfoVO vo) throws Exception;
	
	int deleteSendMessage (SendMessageInfo vo) throws Exception;
}
