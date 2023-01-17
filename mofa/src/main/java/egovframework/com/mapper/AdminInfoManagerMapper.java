package egovframework.com.mapper;

import java.util.List;

import aten.com.backoffice.uat.vo.AdminInfo;
import aten.com.backoffice.uat.vo.AdminInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper
public interface AdminInfoManagerMapper {

	public int deleteAdminUserManage(String mberId) throws Exception;
	
	public int insertAdminUserManage(AdminInfoVO vo) throws Exception;
	
	public int updateAdminUserManage(AdminInfoVO vo) throws Exception;
	
	public int updateAdminUserLockManage(String adminId) throws Exception;
	
	public int updatePassChange(AdminInfo vo) throws Exception;
	
	public AdminInfoVO selectAdminUserManageDetail(AdminInfoVO vo) throws Exception;
	
	public List<AdminInfoVO> selectAdminUserManageListByPagination(AdminInfoVO searchVO) throws Exception;
	
	public int selectAdminUserManageListTotCnt_S(AdminInfoVO searchVO) throws Exception;

	public int selectAdminUserMangerIDCheck(String code) throws Exception;
	
}