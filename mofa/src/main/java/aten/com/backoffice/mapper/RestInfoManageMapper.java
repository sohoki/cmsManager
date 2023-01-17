package aten.com.backoffice.mapper;

import aten.com.backoffice.etc.rest.service.RestInfo;
import aten.com.backoffice.etc.rest.service.RestInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import java.util.List;

@Mapper("RestInfoManageMapper")
public interface RestInfoManageMapper {
  List<RestInfoVO> selectRestInfoPageInfoManageListByPagination(RestInfoVO paramRestInfoVO);
  
  RestInfoVO selectRestInfoDetail(String paramString);
  
  int insertRestInfo(RestInfo paramRestInfo);
  
  int updateRestInfo(RestInfo paramRestInfo);
  
  int updateRestGrade(RestInfo paramRestInfo);
  
  int deleteRestInfo(String paramString);
}
