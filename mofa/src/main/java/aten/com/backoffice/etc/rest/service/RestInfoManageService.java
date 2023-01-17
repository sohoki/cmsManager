package aten.com.backoffice.etc.rest.service;

import java.util.List;

public interface RestInfoManageService {
	
    List<RestInfoVO> selectRestInfoPageInfoManageListByPagination(RestInfoVO searchVO)throws Exception;
	
    RestInfoVO selectRestInfoDetail(String menuSeq)throws Exception;
    
    int updateRestInfo(RestInfo RestInfo)throws Exception;
    
    int updateRestGrade(RestInfo vo)throws Exception;
    
    int deleteRestInfo(String menuSeq)throws Exception;
}
