package aten.com.backoffice.uat.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.uat.vo.UniUtilInfo;

@Mapper("UniUtilManageMapper")
public interface UniUtilManageMapper {

	public int selectIdDoubleCheck(UniUtilInfo vo); 
	
	public int deleteUniStatement(UniUtilInfo vo);
	
	public String selectMaxValue(UniUtilInfo vo);
}
