package aten.com.backoffice.sym.monter.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sym.monter.vo.DispalyPageInfo;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface DisplayPageInfoManageMapper {

	
    public List<Map<String, Object>> selectDisplayPageInfoManageListByPagination(DispalyPageInfoVO searchVO);
    
    public List<DispalyPageInfo> selectDisplayPageInfoContentCombo(String displayGubun);
	
    public DispalyPageInfoVO selectDisplayPageInfoManageDetail(String reportSeq );
    
    public Map<String, Object> selectPageCntNullCheck(String displaySeq);
	
    public DispalyPageInfoVO selectDisplayPageInfoManageView(String displaySeq);
	
    public DispalyPageInfo selectTimeIntevalNullCheck(String displaySeq);
    
    public List<DispalyPageInfoVO> selectDisplayPageInfoCombo(DispalyPageInfoVO searchVO);
    
    public String selectDisplayMaxSeq();
    
    public int selectDisplayPageInfoManageListTotCnt_S(DispalyPageInfoVO searchVO);
	
    public int insertDisplayPageInfoManage(DispalyPageInfo vo);
	//파일 수정 
    public int updateDisplayPageFileUpdate(DispalyPageInfo vo);
    
    public int updateDisplayPageInfoManage(DispalyPageInfo vo);
    
    public int updateDisplayPageUpManage(String displaySeq);
    
    public int updateDisplayPageDownManage(String displaySeq);
    
    public int updateDisplayTimeInfoManage(String displaySeq);
    
    public ModelAndView updateDisplayReSendManage(String displaySeq);
    
    public int deleteDisplayPageInfoManage(DispalyPageInfoVO  vo);
    
}
