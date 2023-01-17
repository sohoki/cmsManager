package aten.com.backoffice.sym.agent.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aten.com.backoffice.sym.agent.vo.OrderInfo;
import aten.com.backoffice.sym.agent.service.OrderInfoManageServie;
import aten.com.backoffice.sym.agent.mapper.OrderInfoManageMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class OrderInfoManageServieImpl extends EgovAbstractServiceImpl implements  OrderInfoManageServie{
	
	
	@Autowired
	private OrderInfoManageMapper orderMapper;

	@Override
	public int selectAgentOrderCnt(String agentCode) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.selectAgentOrderCnt(agentCode);
	}

	@Override
	public OrderInfo selectAgentOrderList(String agentCode) {
		// TODO Auto-generated method stub
		return orderMapper.selectAgentOrderList(agentCode);
	}

	@Override
	public int updateOrderCheck(String orderSeq) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderCheck(orderSeq);
	}
	
	

}
