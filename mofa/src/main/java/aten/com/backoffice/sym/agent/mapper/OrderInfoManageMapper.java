package aten.com.backoffice.sym.agent.mapper;

import aten.com.backoffice.sym.agent.vo.OrderInfo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface OrderInfoManageMapper {

    public int selectAgentOrderCnt(String agentCode);
	
	public OrderInfo selectAgentOrderList (String agentCode);
	
	public int updateOrderCheck (String orderSeq);
}
