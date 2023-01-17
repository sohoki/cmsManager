package aten.com.backoffice.sym.agent.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aten.com.backoffice.sym.state.mapper.AgentStateManageMapper;
import aten.com.backoffice.sts.error.mapper.ErrorInfoManageMapper;
import aten.com.backoffice.sts.error.vo.ErrorInfo;

@Service("AgentStateCreateSchedulerService")
public class AgentStateCreateSchedulerService {

	private static final Logger logger = Logger.getLogger(AgentStateCreateSchedulerService.class);
	
	
	@Autowired
	private AgentStateManageMapper agentState;
	
	@Autowired
	private ErrorInfoManageMapper errorInfo;
	
	public void agentStateScheduling() throws Exception{
		try{
			agentState.insertAgentStateCreate();
		}catch (RuntimeException re) {
		       logger.error("resveBreakScheduling run failed", re);
		       ErrorInfo vo = new ErrorInfo();
		       vo.setErrorType("ERROR_TYPE_1");
			   vo.setErrorMessage("resveBreakScheduling runing error:" + re.toString());
		       errorInfo.insertErrorMessage(vo);
		}catch (Exception e){
			   logger.error("resveBreakScheduling failed", e);
			   ErrorInfo vo = new ErrorInfo();
			   vo.setErrorType("ERROR_TYPE_1");
			   vo.setErrorMessage("resveBreakScheduling error:" + e.toString());
			   errorInfo.insertErrorMessage(vo);
		}
	}
}
