package aten.com.backoffice.sym.state.service;

import java.util.List;
import aten.com.backoffice.sym.state.vo.AgentStateInfo;
import aten.com.backoffice.sym.state.vo.AgentStateInfoVO;

public interface AgentstateInfoManageServie {

	List<AgentStateInfo> selectAgentStateDay(AgentStateInfoVO searchVO) throws Exception;
	
	List<AgentStateInfoVO> selectAgentStateOnyDayChart(AgentStateInfoVO searchVO) throws Exception;
	
	int updateAgentStateCnt (String agentCode) throws Exception;
}