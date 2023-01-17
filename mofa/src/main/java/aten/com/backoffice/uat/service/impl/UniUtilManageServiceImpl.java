package aten.com.backoffice.uat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import aten.com.backoffice.uat.mapper.UniUtilManageMapper;
import aten.com.backoffice.uat.vo.UniUtilInfo;
import aten.com.backoffice.uat.service.UniUtilManageService;

@Service
public class UniUtilManageServiceImpl extends EgovAbstractServiceImpl implements  UniUtilManageService {
	
	@Autowired
	private UniUtilManageMapper uniMapper;

	@Override
	public int selectIdDoubleCheck(UniUtilInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return uniMapper.selectIdDoubleCheck(vo);
	}

	@Override
	public int deleteUniStatement(UniUtilInfo vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = uniMapper.deleteUniStatement(vo);
		System.out.println("ret:" + ret);
		return 1;
	}

	@Override
	public String selectMaxValue(UniUtilInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return uniMapper.selectMaxValue(vo);
	}

}
