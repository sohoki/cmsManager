package aten.com.backoffice.sts.message.mapper;

import java.util.List;
import java.util.Map;

import aten.com.backoffice.sts.message.vo.SendMessageInfo;
import aten.com.backoffice.sts.message.vo.SendMessageInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SendMessageManageMapper {

	public List<Map<String, Object>> selectSendMessageAgentList (SendMessageInfoVO vo);
	
	public List<SendMessageInfoVO> selectSendMessageAgentHistoryList(SendMessageInfoVO vo);
	
	public int insertSendMessage (SendMessageInfoVO vo);
	
	public int updateSendMessage (String schCode);
	
	public int updateSendMessageAgent(SendMessageInfoVO vo);
	
	public int deleteSendMessage (SendMessageInfo vo);
	
	public int deleteSendMessageAgent(SendMessageInfoVO vo);
}
