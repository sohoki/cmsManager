package aten.com.backoffice.sym.cnt.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sym.cnt.vo.CenterInfoVO;
import aten.com.backoffice.sym.cnt.vo.CenterInfo;


@Mapper
public interface CenterInfoManageMapper {
	
    public List<CenterInfoVO> selectCenterInfoManageListByPagination(CenterInfoVO serarch);
	
    public List<CenterInfoVO> selectCenterInfoManageCombo(CenterInfoVO vo);
	
    public int selectCenterInfoManageListTotCnt_S(CenterInfoVO serarch);
	
    public CenterInfoVO selectCenterInfoManageDetail(String centerId);
	
    public int updateCenterInfoManage(CenterInfo vo);
    
    public int updateFileDetailInfo(CenterInfo vo);
    
    public int insertCenterInfoManage(CenterInfo vo);
}
