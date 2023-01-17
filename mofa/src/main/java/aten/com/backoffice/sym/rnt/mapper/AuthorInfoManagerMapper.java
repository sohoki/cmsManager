package aten.com.backoffice.sym.rnt.mapper;

import java.util.List;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sym.rnt.vo.AuthorInfo;

@Mapper
public interface AuthorInfoManagerMapper {
	public List<AuthorInfo> selectAuthorIInfoManageCombo();
}
