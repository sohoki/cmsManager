package aten.com.backoffice.sts.cnt.service;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import  aten.com.backoffice.sts.cnt.vo.ContentFileInfo;
import  aten.com.backoffice.sts.cnt.vo.ContentFileInfoVO;

public interface ContentFileInfoManageService {

	
	   List<ContentFileInfoVO> selectFilePageListByPagination(ContentFileInfoVO searchVO) throws Exception;
		
	   List<ContentFileInfo> fileDelInfo(List<String> report_Seq) throws Exception;
	   
	   int selectFilePageListByPaginationTotCnt_S (ContentFileInfoVO searchVO)throws Exception;
		
	   int  selectFileListTotCnt_S (String conSeq) throws Exception;
		
	   ContentFileInfoVO selectFileDetail   (String atchFileId) throws Exception;

	   Map<String, String> fileUpload(MultipartHttpServletRequest multipartRequest) throws Exception;
	   
	   int insertFileManage (ContentFileInfo vo) throws Exception;
		
	   int deleteFileListManage(List<String> report_Seq) throws Exception;
	   
	   int updateFileManage (ContentFileInfo vo) throws Exception;
	   
	   int updateFileDetailInfo(ContentFileInfo vo) throws Exception;
		
	   int updateFileManageUseYn(ContentFileInfo vo) throws Exception;
		
	   int deleteFileManage (String atchFileId) throws Exception;
}
