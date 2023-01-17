package aten.com.backoffice.uat.service;

import aten.com.backoffice.uat.vo.UniUtilInfo; ;

public interface UniUtilManageService {

    int selectIdDoubleCheck(UniUtilInfo vo)throws Exception;
	
	int deleteUniStatement(UniUtilInfo vo) throws Exception;
	
	String selectMaxValue(UniUtilInfo vo) throws Exception;
}
