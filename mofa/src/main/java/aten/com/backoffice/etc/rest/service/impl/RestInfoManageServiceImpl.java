package aten.com.backoffice.etc.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import aten.com.backoffice.etc.rest.service.RestInfoVO;
import aten.com.backoffice.etc.rest.service.RestInfo;
import aten.com.backoffice.etc.rest.service.RestInfoManageService;
import aten.com.backoffice.mapper.RestInfoManageMapper;


@Service("RestInfoManageService")
public class RestInfoManageServiceImpl extends EgovAbstractServiceImpl implements RestInfoManageService {
	
	@Resource(name="RestInfoManageMapper")
	private RestInfoManageMapper restMapper;
	
	
	

	@Override
	public List<RestInfoVO> selectRestInfoPageInfoManageListByPagination(
			RestInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return restMapper.selectRestInfoPageInfoManageListByPagination(searchVO);
	}

	@Override
	public RestInfoVO selectRestInfoDetail(String menuSeq) throws Exception {
		// TODO Auto-generated method stub
		return restMapper.selectRestInfoDetail(menuSeq);
	}

	@Override
	public int updateRestInfo(RestInfo RestInfo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (RestInfo.getMode().equals("Ins"))
			ret = restMapper.insertRestInfo(RestInfo);
		else 
			ret = restMapper.updateRestInfo(RestInfo);
		
		return ret;
	}

	@Override
	public int deleteRestInfo(String menuSeq) throws Exception {
		// TODO Auto-generated method stub
		return restMapper.deleteRestInfo(menuSeq);
	}

	@Override
	public int updateRestGrade(RestInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return restMapper.updateRestGrade(vo);
	}
	

}
