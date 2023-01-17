package egovframework.mofa.moniter.page.app.mapper;

import java.util.List;
import egovframework.mofa.moniter.page.vo.StateInfo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper
public interface AppInfoManagerMapper {

	public List<StateInfo> selectApp01();
	
	public List<StateInfo> selectApp02();
}
