package aten.com.backoffice.sts.cnt.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aten.com.backoffice.sts.cnt.vo.ContentFileInfo;
import aten.com.backoffice.sts.cnt.vo.ContentFileInfoVO;

@Mapper
public interface ContentFileManagerMapper {
	
	public List<ContentFileInfoVO> selectFilePageListByPagination (ContentFileInfoVO  searchVO);
	
	public int selectFilePageListByPaginationTotCnt_S (ContentFileInfoVO  searchVO);
	
	public List<ContentFileInfo> fileDelInfo(List<String> reportSeq);
	
	public int selectFileListTotCnt_S  (String conSeq);
	
	public ContentFileInfoVO selectFileDetail (String atchFileId);
	
	public int insertFileManage(ContentFileInfo vo);
	
	public int updateFileManage(ContentFileInfo vo);
	
	public int updateFileManageUseYn(ContentFileInfo vo);
	
	public int updateFileDetailInfo(ContentFileInfo vo);
		
	public int deleteFileManage(String atchFileId);
	//추가 수정 
	public int deleteFileListManage(List<String> reportSeq);

}
