package aten.com.backoffice.sym.cnt.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sym.cnt.vo.CenterInfo;
import aten.com.backoffice.sym.cnt.vo.CenterInfoVO;

public interface CenterInfoService {

    ModelAndView selectCenterInfoPageInfoManageListByPagination(CenterInfoVO serarch)throws Exception;
	
	List<CenterInfoVO> selectCenterCombo(CenterInfoVO vo) throws Exception;
	
	int selectCenterInfoPageInfoManageListToCnt_s(CenterInfoVO serarch)throws Exception;
	
	CenterInfoVO selectCenterInfoDetail(String centerId)throws Exception;
	
	int updateCenterInfoManage(CenterInfo vo) throws Exception;
}
