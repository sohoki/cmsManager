package egovframework.mofa.moniter.page.mysql.mapper;

import java.util.List;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.mofa.moniter.page.vo.StateInfo;


@Mapper
public interface StaticManagerMapper {

	public List<StateInfo> selectMariaPageUni01();
	
	public StateInfo selectPageMariaUni02();
	
	public List<StateInfo> selectMariaPageUni03();
	
	public StateInfo selectMariaPageUni04();
}
