package aten.com.backoffice.sym.device.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sym.device.vo.DeviceInfo;
import aten.com.backoffice.sym.device.vo.DeviceInfoVO;

public interface DeviceInfoManageService {

	
	ModelAndView selectAgentPageInfoManageListByPagination(DeviceInfoVO searchVO) throws Exception;
	//신규 
/*	ModelAndView selectDisplayStageChangePageList(@Param("params") Map<String, Object> params)throws Exception;
	
	ModelAndView updateAgentDisplayChange(AgentInfo vo)throws Exception;

	List<AgentInfoVO> selectAgentCenterPageList(AgentInfoVO searchVO) throws Exception;
	
    AgentInfoVO selectAgentPageInfoManageDetail(String  agentCode )throws Exception;
	
    AgentInfoVO selectAgentPageInfoManageView(AgentInfoVO searchVO) throws Exception;
    
    AgentInfoVO selectAgentUrlCheck(AgentInfo searchVO)throws Exception;
    
    int selectAgentExist (String agentCode) throws Exception;
	
    String selectDisplayCheck(AgentInfo vo) throws Exception;
    
    int selectAgentPageInfoManageListTotCnt_S(AgentInfoVO searchVO) throws Exception;
    
    int updateAgentState()throws Exception;*/
}
