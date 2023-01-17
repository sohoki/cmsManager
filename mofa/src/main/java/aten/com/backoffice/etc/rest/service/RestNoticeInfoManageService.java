package aten.com.backoffice.etc.rest.service;

import java.util.List;

public interface RestNoticeInfoManageService {
	
    List<RestNoticeInfoVO> selectRestNoticeInfoLeftListByPagination(RestNoticeInfoVO search) throws Exception;
	
	RestNoticeInfo selectRestNoticeInfo(String noticeSeq) throws Exception;
		
	int updateRestNoticeInfo(RestNoticeInfoVO vo) throws Exception;
	
	int deleteRestNoticeInfo(String noticeSeq) throws Exception;
}
