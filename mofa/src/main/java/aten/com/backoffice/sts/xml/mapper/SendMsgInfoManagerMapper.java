package aten.com.backoffice.sts.xml.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sts.xml.vo.SendMsgInfo;
import aten.com.backoffice.sts.xml.vo.SendMsgInfoVO;

@Mapper
public interface SendMsgInfoManagerMapper {
	
	public List<SendMsgInfoVO> selectSendMsgInfoManageListByPagination(SendMsgInfoVO searchVO);  
	
	/*public int selectSendMsgInfoManageListTotCnt_S(SendMsgInfoVO searchVO);  */
	
	public int selectAgentOrderCount(SendMsgInfo searchVO);  
	
	public int selectAgentMessageCount (SendMsgInfoVO searchVO);  
	
	public List<SendMsgInfoVO>  selectAgentOrderLst(SendMsgInfo searchVO);  
	//다중 처리 msgSeq 값 필요 없을떄
	public int insertSendMsgInfoManageList(List<SendMsgInfo> vo);
	//단일 처리 msgSeq 값 필요할떄
	public int insertSendMsgInfoManage(SendMsgInfo vo);
	
	public int updateSendMsgInfoManage(SendMsgInfo vo);
	
}
