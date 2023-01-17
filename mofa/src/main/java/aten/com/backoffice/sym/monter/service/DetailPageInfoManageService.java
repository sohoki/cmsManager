package aten.com.backoffice.sym.monter.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import aten.com.backoffice.sym.monter.vo.DetailPageInfoVO;
import aten.com.backoffice.sym.monter.vo.DetailPageInfo;


public interface DetailPageInfoManageService {
	
    ModelAndView selectDetailPageInfoManageListByPagination(DetailPageInfoVO searchVO) throws Exception;
    
    List<Map<String, Object>> selectDetailPageInfoManageListByContent(DetailPageInfoVO searchVO) throws Exception;
	
    Map<String, Object> selectDetailInfo(String  detailSeq )throws Exception;
    //수정 
    List<Map<String, Object>> selectDisPlayPreviewList(DetailPageInfoVO searchVO)throws Exception;
    
    List<Map<String, Object>> selectAgentPreviewList(DetailPageInfoVO vo)throws Exception;
    
    String returnHtmlPage(String displaySeq, String fileGubun )throws Exception;
    
    boolean ContentFileCreate (String htmlPgae, String displaySeq, String fileGubun )throws Exception;
	
    int selectDetailPageInfoManageListTotCnt_S(DetailPageInfoVO searchVO) throws Exception;
    
    int selectDetailMaxSort(String displaySeq)throws Exception;
    
    int selectDetailSumTime(String displaySeq)throws Exception;
	
    int insertDetailPageInfoManage(DetailPageInfo vo) throws Exception;
	
    int updateDetailPageInfoManage(DetailPageInfo vo) throws Exception;
    
    int updateDetailPageInfoUpManage(DetailPageInfo vo) throws Exception;
    
    int updateDetailPageInfoDownManage(DetailPageInfo vo) throws Exception;
	
    int updateDetailTimeChangeManage(DetailPageInfo vo) throws Exception;
    
    int updateDetailSortOrderInfoManage(DetailPageInfoVO vo)throws Exception;
    
    int updateDisplayPageChangeInfo(String report_Seq)throws Exception; 
    
    ModelAndView updateDitailOrderUpdatePage(List<DetailPageInfo> detailPages) throws Exception;
    
    ModelAndView deleteDetailPageInfoManage(DetailPageInfo vo) throws Exception;
}
