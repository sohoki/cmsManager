package aten.com.backoffice.sts.sch.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sts.sch.vo.SchduleInfo;
import aten.com.backoffice.sts.sch.vo.SchduleInfoVO;
public interface SchduleInfoManageService {

	
    ModelAndView selectSchduleInfoManageListByPagination(SchduleInfoVO searchVO) throws Exception;
	
	SchduleInfoVO selectSchduleInfoManageDetail(String schCode) throws Exception;
	
	SchduleInfoVO selectSchduleInfoManageView(String schCode) throws Exception;
	
	List<SchduleInfo> selectScheduleSendInfo(String agentNm) throws Exception;

	int selectScheduleSendInfoCnt(String agentNm) throws Exception;
	
	int selectSchduleInfoManageListTotCnt_S(SchduleInfoVO searchVO) throws Exception;
	
	SchduleInfo updateSchduleInfoManage(SchduleInfo vo) throws Exception;
	
	int deleteSchduleInfoManage(String schCode) throws Exception;
}
