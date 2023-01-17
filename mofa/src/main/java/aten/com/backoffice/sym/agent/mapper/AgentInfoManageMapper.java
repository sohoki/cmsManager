package aten.com.backoffice.sym.agent.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sym.agent.vo.AgentInfo;
import aten.com.backoffice.sym.agent.vo.AgentInfoVO;

@Mapper
public interface AgentInfoManageMapper {

	    public List<AgentInfoVO> selectAgentPageInfoManageListByPagination(AgentInfoVO searchVO);
		//지점에서  단말기 상태 보기 
	    public List<Map<String, Object>> selectDisplayStageChangePageList(@Param("params") Map<String, Object> params);
	   	    
	    public List<AgentInfoVO> selectAgentNowStateInnfo(String centerId);
	    
	    public List<AgentInfoVO> selectAgentCenterPageList(AgentInfoVO searchVO);
	    
	    public AgentInfoVO selectAgentPageInfoManageDetail(String agentCode );
		
	    public AgentInfoVO selectAgentPageInfoManageView(AgentInfoVO searchVO);
	    
	    public AgentInfoVO selectAgentUrlCheck(AgentInfo searchVO);
		
	    public int selectAgentPageInfoManageListTotCnt_S(AgentInfoVO searchVO);
	    
	    public String selectDisplayCheck(AgentInfo vo);
	    
	    public int selectAgentExist (String agentCode);
		
	    public int insertAgentPageInfoManage(AgentInfo vo);
		
	    public int updateAgentPageInfoManage(AgentInfo vo);
	    
	    public int updateAgentState();
	    
	    public int updateAgentUrlRec(AgentInfo vo);
	    
	    public int updateAgentDisplayChange(AgentInfo vo);
	    
	    public int updateAgentIpMac (AgentInfo vo);
	    
	    public int updateDisplayUpdate(AgentInfo vo);
	    
	    public int updateAgentStateUpdate(String displaySeq);
		
	    public int deleteAgentPageInfoManage(String agentCode);
}
