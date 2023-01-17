package aten.com.backoffice.sym.monter.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import aten.com.backoffice.sym.monter.vo.DetailPageInfo;
import aten.com.backoffice.sym.monter.vo.DetailPageInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface DetailPageInfoManageMapper {

    public List<Map<String, Object>> selectDetailPageInfoManageListByPagination(DetailPageInfoVO searchVO);
	
    public List<Map<String, Object>> selectDetailPageInfoManageListByContent(DetailPageInfoVO searchVO);
    
    public Map<String, Object> selectDetailInfo(String  detailSeq );
	//신규
    public List<Map<String, Object>> selectDisPlayPreviewList(DetailPageInfoVO searchVO);
    
    public List<Map<String, Object>> selectAgentPreviewList(DetailPageInfoVO vo);
    
    public List<String> selectDisplaySeqReturn(List<String> report_Seq);
	//신규 end    
    public int selectDetailPageInfoManageListTotCnt_S(DetailPageInfoVO searchVO);
    
    public int selectDetailMaxSort(String displaySeq);
    
    public int selectDetailSumTime(String displaySeq);
	
    public int insertDetailPageInfoManage(DetailPageInfo vo);
	
    public int updateDetailPageInfoManage(DetailPageInfo vo);
    
    public int updateDetailPageInfoUpManage(DetailPageInfo vo);
    
    public int updateDetailPageInfoDownManage(DetailPageInfo vo);
	
    public int updateDetailTimeChangeManage(DetailPageInfo vo);
    
    public int updateDetailSortOrderUpSubInfoManage(String displaySeq);
    //화면 순위 변경
    public int updateDetailSortOrderUpDownSubInfoManage(@Param("displaySeq") String displaySeq, @Param("detailSeq") String detailSeq, @Param("mode") String mode);
    
    //public int updateDetailSortOrderDownInfoManage();   
    
    public int updateDisplayPageChangeInfo(List<String> display_Seq); 
    
    public int updateDitailOrderUpdatePage(List<DetailPageInfo> vo );
    
    public int deleteDetailPageInfoManage(@Param("displaySeq") String displaySeq, @Param("detailSeq") String detailSeq);
    
    public int deleteDetailReportSeq(List<String> report_Seq);
        
}


