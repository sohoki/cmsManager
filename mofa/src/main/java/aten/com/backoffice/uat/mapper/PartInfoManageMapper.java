package aten.com.backoffice.uat.mapper;

import java.util.List;


import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.uat.vo.PartInfo;
import aten.com.backoffice.uat.vo.PartInfoVO;

@Mapper("PartInfoManageMapper")
public interface PartInfoManageMapper {
	
	
    public List<PartInfoVO> selectPartInfoPageInfoManageListByPagination(PartInfoVO serarch)throws Exception;
	
	public List<PartInfoVO> selectPartInfoCombo() throws Exception;
	
	public int selectPartInfoPageInfoManageListToCnt_s(PartInfoVO serarch);
	
	public PartInfoVO selectPartInfoDetail(String PartId);
	
	public int insertPartInfoManage(PartInfo vo);
	
	public int updatePartInfoManage(PartInfo vo);
}
