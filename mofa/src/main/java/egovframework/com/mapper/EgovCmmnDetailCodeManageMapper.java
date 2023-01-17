package egovframework.com.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sym.ccm.cde.service.CmmnDetailCode;
import aten.com.backoffice.sym.ccm.cde.service.CmmnDetailCodeVO;


@Mapper
public interface EgovCmmnDetailCodeManageMapper {

    public List<CmmnDetailCodeVO> selectCmmnDetailCodeListByPagination(String codeId);
	
	public List<CmmnDetailCode> selectCmmnDetailCombo (String code);
	
	public CmmnDetailCode selectCmmnDetailCodeDetail(String code);
	
	public CmmnDetailCode selectCmmnDetail(String code);
	
	public int insertCmmnDetailCode(CmmnDetailCode vo);
	               
	public int updateCmmnDetailCode(CmmnDetailCode vo);
	
	public int deleteCmmnDetailCode(String code);
	
	public int selectCmmnDetailCodeIdCheck(String code);
	
	public int deleteCmmnDetailCodeId(String codeId);
}
