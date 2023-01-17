package aten.com.backoffice.sts.sch.mapper;

import java.util.List;

import aten.com.backoffice.sts.sch.vo.SchduleInfo;
import aten.com.backoffice.sts.sch.vo.SchduleInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("SchduleInfoManageMapper")
public interface SchduleInfoManageMapper {

	public List<SchduleInfoVO> selectSchduleInfoManageListByPagination(SchduleInfoVO searchVO);
	
	public SchduleInfoVO selectSchduleInfoManageDetail(String schCode);
	
	public SchduleInfoVO selectSchduleInfoManageView(String schCode);
	
	public List<SchduleInfo> selectScheduleSendInfo(String agentCode);

	public int selectScheduleSendInfoCnt(String agentCode);
	
	public int selectSchduleInfoManageListTotCnt_S(SchduleInfoVO searchVO);
	
	public int insertSchduleInfoManage(SchduleInfo vo);
	
	public int updateSchduleInfoManage(SchduleInfo vo);
	
	public int deleteSchduleInfoManage(String schCode);
}
