package aten.com.backoffice.sts.message.service.impl;

import java.util.List;
import java.util.Map;

import aten.com.backoffice.sts.message.mapper.SendMessageManageMapper;
import aten.com.backoffice.sts.message.vo.SendMessageInfo;
import aten.com.backoffice.sts.message.vo.SendMessageInfoVO;
import aten.com.backoffice.sts.message.service.SendMessageInfoManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class SendMessageManageServieImpl extends EgovAbstractServiceImpl implements  SendMessageInfoManageService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageManageServieImpl.class);
	
	@Autowired
	private SendMessageManageMapper sendMapper;

	@Override
	public int insertSendMessage(SendMessageInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		//삭제 한다음 없는거 인서트 
		int ret = 1;
		try{
			LOGGER.debug("size:" + vo.getAgentCodeLst().size() );
			//배열 사이즈 빈값으로 해서 나오는거 확인 하기 
			if (vo.getAgentCodeLst().size() > 0 && !vo.getAgentCodeLst().get(0).toString().equals("")){
				sendMapper.insertSendMessage(vo);
			}else {
				sendMapper.deleteSendMessage(vo);
			}
		}catch (Exception e){
			LOGGER.error("insertSendMessage error:" + e.toString());
			ret = 0;
			throw e;
		}
		return ret;
	}
	@Override
	public int updateSendMessage(String schCode) throws Exception {
		// TODO Auto-generated method stub
		return sendMapper.updateSendMessage(schCode);
	}

	@Override
	public int deleteSendMessage(SendMessageInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMapper.deleteSendMessage(vo);
	}

	@Override
	public List<Map<String, Object>> selectSendMessageAgentList(SendMessageInfoVO vo) {
		// TODO Auto-generated method stub
		return sendMapper.selectSendMessageAgentList(vo);
	}

	@Override
	public int updateSendMessageAgent(SendMessageInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMapper.updateSendMessageAgent(vo);
	}

	@Override
	public List<SendMessageInfoVO> selectSendMessageAgentHistoryList(
			SendMessageInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMapper.selectSendMessageAgentHistoryList(vo);
	}
	
	
	
}
