package aten.com.backoffice.etc.rest.service.impl;

import aten.com.backoffice.etc.rest.service.RestNoticeInfo;
import aten.com.backoffice.etc.rest.service.RestNoticeInfoVO;
import aten.com.backoffice.etc.rest.service.RestNoticeInfoManageService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import aten.com.backoffice.mapper.RestNoticeInfoManageMapper;

@Service("RestNoticeInfoManageService")
public class RestNoticeInfoManageServiceImpl extends EgovAbstractServiceImpl implements RestNoticeInfoManageService {

	
	@Resource(name="RestNoticeInfoManageMappers")
	private RestNoticeInfoManageMapper noticecMapper;

	@Override
	public List<RestNoticeInfoVO> selectRestNoticeInfoLeftListByPagination(
			RestNoticeInfoVO search) throws Exception {
		// TODO Auto-generated method stub
		return noticecMapper.selectRestNoticeInfoLeftListByPagination(search);
	}

	@Override
	public RestNoticeInfo selectRestNoticeInfo(String noticeSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return noticecMapper.selectRestNoticeInfo(noticeSeq);
	}

	@Override
	public int updateRestNoticeInfo(RestNoticeInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (vo.getMode().equals("Ins")){
			ret = noticecMapper.insertRestNoticeInfo(vo);
		}else {
			ret = noticecMapper.updateRestNoticeInfo(vo);
		}
		return ret;
	}

	@Override
	public int deleteRestNoticeInfo(String noticeSeq) throws Exception {
		// TODO Auto-generated method stub
		return noticecMapper.deleteRestNoticeInfo(noticeSeq);
	}
	
	
}
