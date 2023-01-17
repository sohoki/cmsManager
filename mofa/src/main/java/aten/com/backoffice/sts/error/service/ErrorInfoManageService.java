package aten.com.backoffice.sts.error.service;

import java.util.List;
import aten.com.backoffice.sts.error.vo.ErrorInfoVO;

public interface ErrorInfoManageService {
	
	List<ErrorInfoVO> selectErrorMessage(ErrorInfoVO searchVO) throws Exception;
	
	int selectErrorMessageCnt(ErrorInfoVO searchVO)throws Exception;
	
	int insertErrorMessage(String errorMessage)throws Exception;
	
	int deleteErrorMessage(String errorSeq)throws Exception;
	

}
