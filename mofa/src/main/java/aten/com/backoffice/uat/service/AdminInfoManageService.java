package aten.com.backoffice.uat.service;

import java.util.List;

import aten.com.backoffice.uat.vo.AdminInfo;
import aten.com.backoffice.uat.vo.AdminInfoVO;

public interface AdminInfoManageService {

	int deleteAdminUserManage(String mberId) throws Exception;
	
	int updateAdminUserManage(AdminInfoVO vo) throws Exception;
	
	int updateAdminUserLockManage(String adminId) throws Exception;
	
	int updatePassChange(AdminInfo vo) throws Exception;
	
	AdminInfoVO selectAdminUserManageDetail(AdminInfoVO vo) throws Exception;	
	
	List<AdminInfoVO> selectAdminUserManageListByPagination(AdminInfoVO searchVO) throws Exception;  
	
    int selectAdminUserManageListTotCnt_S(AdminInfoVO searchVO) throws Exception;
    
    int selectAdminUserMangerIDCheck(String code) throws Exception;
    
    
}
