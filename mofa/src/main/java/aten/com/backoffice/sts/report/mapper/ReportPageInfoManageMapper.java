package aten.com.backoffice.sts.report.mapper;

import java.util.List;

import aten.com.backoffice.sts.report.vo.ReportPageInfo;
import aten.com.backoffice.sts.report.vo.ReportPageInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ReportPageInfoManageMapper {

    public List<ReportPageInfoVO> selectReportPageInfoManageListByPagination(ReportPageInfoVO searchVO);
	
    public ReportPageInfoVO selectReportPageInfoManageDetail(String  reportSeq );
	
    public ReportPageInfoVO selectReportPageInfoManageView(String reportSeq);
	
    public int selectReportPageInfoManageListTotCnt_S(ReportPageInfoVO searchVO);
    
    public String selectReportMax();
	
    public String selectReportPalyTime(String reportSeq);
    
    public int insertReportPageInfoManage(ReportPageInfo vo);
	
    public int updateReportPageInfoManage(ReportPageInfo vo);
    
    public int updateReportPreviewInfoManage(ReportPageInfo vo);
	
}
