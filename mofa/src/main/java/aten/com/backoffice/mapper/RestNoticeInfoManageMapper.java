package aten.com.backoffice.mapper;

import aten.com.backoffice.etc.rest.service.RestNoticeInfo;
import aten.com.backoffice.etc.rest.service.RestNoticeInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import java.util.List;

@Mapper("RestNoticeInfoManageMappers")
public interface RestNoticeInfoManageMapper {
  List<RestNoticeInfoVO> selectRestNoticeInfoLeftListByPagination(RestNoticeInfoVO paramRestNoticeInfoVO);
  
  RestNoticeInfo selectRestNoticeInfo(String paramString);
  
  int insertRestNoticeInfo(RestNoticeInfoVO paramRestNoticeInfoVO);
  
  int updateRestNoticeInfo(RestNoticeInfoVO paramRestNoticeInfoVO);
  
  int deleteRestNoticeInfo(String paramString);
}
