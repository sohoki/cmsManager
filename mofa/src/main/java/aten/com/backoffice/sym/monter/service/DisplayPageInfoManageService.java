package aten.com.backoffice.sym.monter.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sym.monter.vo.DispalyPageInfoVO;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfo;

public interface DisplayPageInfoManageService {

    ModelAndView selectDisplayPageInfoManageListByPagination(DispalyPageInfoVO searchVO) throws Exception;
    
    List<DispalyPageInfo> selectDisplayPageInfoContentCombo (String displayGubun) throws Exception;
	
    DispalyPageInfoVO selectDisplayPageInfoManageDetail(String  reportSeq )throws Exception;
	
    DispalyPageInfoVO selectDisplayPageInfoManageView(String displaySeq) throws Exception;
    
    List<DispalyPageInfoVO> selectDisplayPageInfoCombo(DispalyPageInfoVO searchVO) throws Exception;
    
    String selectDisplayMaxSeq() throws Exception;
    
    int selectDisplayPageInfoManageListTotCnt_S(DispalyPageInfoVO searchVO) throws Exception;
	
    int updateDisplayPageInfoManage(DispalyPageInfo vo) throws Exception;
	
    int updateDisplayPageUpManage(String displaySeq) throws Exception;
    
    int updateDisplayPageDownManage(String displaySeq) throws Exception; 
    
    int updateDisplayTimeInfoManage(String displaySeq) throws Exception;
    
    ModelAndView updateDisplayReSendManage(String displaySeq) throws Exception;
    
    //int deleteDisplayPageInfoManage(String  displaySeq) throws Exception;

	ModelAndView deleteDisplayPageInfoManage(String displaySeq) throws Exception;
    
}
