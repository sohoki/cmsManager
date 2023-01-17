package aten.com.backoffice.sym.agent.service;

import aten.com.backoffice.sym.agent.vo.OrderInfo;

public interface OrderInfoManageServie {

    int selectAgentOrderCnt(String agentCode)throws Exception;
	
    OrderInfo selectAgentOrderList (String agentCode);
	
	int updateOrderCheck (String orderSeq);
}
