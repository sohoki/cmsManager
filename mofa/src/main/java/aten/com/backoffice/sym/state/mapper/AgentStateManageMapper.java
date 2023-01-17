package aten.com.backoffice.sym.state.mapper;

import java.util.List;

import aten.com.backoffice.sym.state.vo.AgentStateInfo;
import aten.com.backoffice.sym.state.vo.AgentStateInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface AgentStateManageMapper {
	
    public List<AgentStateInfo> selectAgentStateDayList(AgentStateInfoVO searchVO);
    
    public List<AgentStateInfoVO> selectAgentStateOnyDayChart(AgentStateInfoVO searchVO);
	
    public int updateAgentStateCnt (String agentCode);
    
    public int insertAgentStateCreate();
}
