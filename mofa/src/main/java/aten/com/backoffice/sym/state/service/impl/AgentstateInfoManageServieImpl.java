package aten.com.backoffice.sym.state.service.impl;

import aten.com.backoffice.sym.state.vo.AgentStateInfo;
import aten.com.backoffice.sym.state.vo.AgentStateInfoVO;
import aten.com.backoffice.sym.state.service.AgentstateInfoManageServie;
import aten.com.backoffice.sym.state.mapper.AgentStateManageMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class AgentstateInfoManageServieImpl extends EgovAbstractServiceImpl implements AgentstateInfoManageServie {
	
	
	@Autowired
	private AgentStateManageMapper agentStateMapper;

	@Override
	public List<AgentStateInfo> selectAgentStateDay(AgentStateInfoVO searchVO) throws Exception {
		
		// TODO Auto-generated method stub
		return agentStateMapper.selectAgentStateDayList(searchVO);
	}
	@Override
	public List<AgentStateInfoVO> selectAgentStateOnyDayChart(
			AgentStateInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return agentStateMapper.selectAgentStateOnyDayChart(searchVO);
	}

	@Override
	public int updateAgentStateCnt(String agentCode) throws Exception {
		// TODO Auto-generated method stub
		return agentStateMapper.updateAgentStateCnt(agentCode);
	}

	
	
	

}
