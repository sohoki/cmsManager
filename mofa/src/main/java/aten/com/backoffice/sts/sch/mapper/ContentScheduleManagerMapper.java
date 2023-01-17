package aten.com.backoffice.sts.sch.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfo;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfoVO;


@Mapper
public interface ContentScheduleManagerMapper {

	public List<ContentScheduleInfoVO> selectContentSchduleInfoManageListByPagination(ContentScheduleInfoVO searchVO);
	
	public ContentScheduleInfoVO  selectConetntSchduleInfoManageView(String conschCode);
	
	public int insertContentSchduleInfoManage(ContentScheduleInfo vo);
	
	public int updateContentSchduleInfoManage(ContentScheduleInfo vo);
	
	public int deleteContentSchduleInfoManage(String conschCode);
}
