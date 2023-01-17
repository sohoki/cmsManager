package aten.com.backoffice.sts.error.mapper;

import java.util.List;
import aten.com.backoffice.sts.error.vo.ErrorInfo;
import aten.com.backoffice.sts.error.vo.ErrorInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ErrorInfoManageMapper {
	
    public List<ErrorInfoVO> selectErrorMessage(ErrorInfoVO searchVO);
    
    public int selectErrorMessageCnt(ErrorInfoVO searchVO);
	
    public int insertErrorMessage(ErrorInfo vo);

    public int deleteErrorMessage(String errorSeq);

}
