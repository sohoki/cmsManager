package aten.com.backoffice.sym.rnt.service.impl;

import java.util.List;

import aten.com.backoffice.sym.rnt.vo.AuthorInfo;
import aten.com.backoffice.sym.rnt.service.AuthorInfoManageService;
import aten.com.backoffice.sym.rnt.mapper.AuthorInfoManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class AuthorInfoManageServiceImpl extends EgovAbstractServiceImpl implements AuthorInfoManageService {

	
	@Autowired
	private AuthorInfoManagerMapper authMapper;
	
	@Override
	public List<AuthorInfo> selectAuthorIInfoManageCombo() {
		// TODO Auto-generated method stub
		return authMapper.selectAuthorIInfoManageCombo();
	}
	

}
