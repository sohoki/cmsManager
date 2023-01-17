package egovframework.mofa.moniter.page.oracle.mapper;

import java.util.List;
import java.util.Map;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.mofa.moniter.page.vo.StateInfo;


@Mapper
public interface StaticMoniterManagerMapper {
	
    public List<Map<String, Object>> selectPageUni01();
	
	public List<Map<String, Object>> selectPageUni02();
	
	public List<Map<String, Object>> selectPageUni03(String agentid);
	
	public Map<String, Object> selectPageUni04();
	
	public List<Map<String, Object>> selectPageUni05();
	
	public List<Map<String, Object>> selectPageUni06();
	
	public List<Map<String, Object>> selectPageUni07();
	
	public List<Map<String, Object>> selectPageUni08();
		
	
	public int insertPage01(StateInfo stateinfo);
	
	public int updatePage02(StateInfo stateinfo);
	
	public int insertPage03(StateInfo stateinfo);
	
	public int updatePage04(StateInfo stateinfo);
	
	public int updatePage05(StateInfo stateinfo);
	
	public int updatePage05_1(StateInfo stateinfo);
	
	public int updatePage06(StateInfo stateinfo);
	
	public int updatePage06_1(StateInfo stateinfo);
	
}
