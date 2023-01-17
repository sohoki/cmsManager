package aten.com.backoffice.sts.cnt.service.impl;



import aten.com.backoffice.sts.cnt.vo.ContentFileInfo;
import aten.com.backoffice.sts.cnt.vo.ContentFileInfoVO;
import aten.com.backoffice.sts.cnt.service.ContentFileInfoManageService;
import aten.com.backoffice.util.web.service.fileService;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import aten.com.backoffice.sts.cnt.mapper.ContentFileManagerMapper;



@Service
public class ContentFileInfoManageServiceImpl extends EgovAbstractServiceImpl implements ContentFileInfoManageService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileInfoManageServiceImpl.class);
	
	@Autowired
	private ContentFileManagerMapper conFileManager;

	@Autowired
    protected EgovPropertyService propertiesService;
	
	@Autowired
	private EgovIdGnrService egovFileIdGnrService;   
	
	@Override
	public List<ContentFileInfoVO> selectFilePageListByPagination(
			ContentFileInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFilePageListByPagination(searchVO);
	}

	@Override
	public int selectFilePageListByPaginationTotCnt_S(ContentFileInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFilePageListByPaginationTotCnt_S(searchVO);
	}

	@Override
	public int selectFileListTotCnt_S(String conSeq) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFileListTotCnt_S(conSeq);
	}

	@Override
	public ContentFileInfoVO selectFileDetail(String atchFileId)
			throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.selectFileDetail(atchFileId);
	}

	@Override
	public int insertFileManage(ContentFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.insertFileManage(vo);
	}

	@Override
	public int updateFileManage(ContentFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.updateFileManage(vo);
	}

	@Override
	public int updateFileManageUseYn(ContentFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.updateFileManageUseYn(vo);
	}

	@Override
	public int deleteFileManage(String atchFileId) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.deleteFileManage(atchFileId);
	}

	@Override
	public List<ContentFileInfo> fileDelInfo(List<String> reportSeq) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.fileDelInfo(reportSeq);
	}

	@Override
	public int updateFileDetailInfo(ContentFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.updateFileDetailInfo(vo);
	}
    //신규 생성
	@Override
	public int deleteFileListManage(List<String> report_Seq) throws Exception {
		// TODO Auto-generated method stub
		return conFileManager.deleteFileListManage (report_Seq);
	}

	@Override
	public Map<String, String> fileUpload( MultipartHttpServletRequest multipartRequest) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		try{
			
			Iterator<String> itr = multipartRequest.getFileNames();
			
			fileService fileserice = new fileService();
			String inDate   = new java.text.SimpleDateFormat("yyyyMM").format(new java.util.Date());
			String file_path =  propertiesService.getString("Globals.fileStorePath")+"/"+inDate;
			while (itr.hasNext()) { //받은 파일들을 모두 돌린다.
				MultipartFile mpf = multipartRequest.getFile(itr.next());      
                String originalFilename = mpf.getOriginalFilename(); //파일명
                
                String Ext = "";        	      
                
                try {
                	//디렉톨 생성 여부 확인 
                	
                	            	
                	File filedir = new File(file_path);
                	
                	if (!filedir.isDirectory()){
                		filedir.mkdir();
                	}
                	filedir =null;
                    //파일 저장
                	
                	
                	LOGGER.debug("originalFilename:" + originalFilename);
                	
                	File file_s =  new File(file_path+"/" + originalFilename );            	
                	Ext = fileserice.fileExt(file_s,".");  
                	LOGGER.debug("Ext:" + Ext + " file_s:" + file_s.toString() );
                	
                	String atchFileId = egovFileIdGnrService.getNextStringId();
                	file_s = fileserice.rename(file_s, atchFileId+"."+Ext, file_path);            	
                    mpf.transferTo(file_s); //파일저장 실제로는 service에서 처리                
                    
                    
                    
                    // DB 에 저장
                    //동영상 파일시 썸네일 파일 생성 
                    
                    
                    ContentFileInfo vo = new ContentFileInfo();
                    
                    ContentFileInfoVO contentFileInfoVO = new ContentFileInfoVO();
                    contentFileInfoVO.setStreFileNm(file_s.getName());
                    contentFileInfoVO.setOrignlFileNm(originalFilename);
                    contentFileInfoVO.setSearchKeyword(file_s.getName());
                    contentFileInfoVO.setSearchCondition("atchFileId");
                    contentFileInfoVO.setMediaType(fileserice.modifyExtension(originalFilename));
                    
                    if (contentFileInfoVO.getMediaType().equals("MEDIA") ){
                    	
                    	Map<String, String> videoInfo =fileService.getImageFromFrame(file_s.toString(),  file_path);
                        
                        if (videoInfo.size() > 0){
                       	    vo.setFileThumnail(videoInfo.get("thumnail")  );
                       	    vo.setPlayTime(videoInfo.get("duration"));
                       	    vo.setFileWidth(videoInfo.get("fileWidth"));
                       	    vo.setFileHeight(videoInfo.get("fileHeight"));
                       	    vo.setFileSize(videoInfo.get("fileSize") );
                        }
                        videoInfo.clear();
                        
                	}else if (contentFileInfoVO.getMediaType().equals("IMAGE")){
                		//이미지 width , height 정리 하기 
                        Map<String, String> imageInfo =fileService.getImageSize(file_s);
                        
                        if (imageInfo.size() > 0){
                       	    vo.setFileWidth(imageInfo.get("fileWidth"));
                       	    vo.setFileHeight(imageInfo.get("fileHeight"));
                       	    vo.setFileSize(imageInfo.get("fileSize") );
                        }
                        imageInfo.clear();
                	}
                    vo.setAtchFileId(atchFileId);                    
                    vo.setUserId(EgovUserDetailsHelper.getAuthenticatedUser().toString()); 
    				vo.setFileStreCours(file_path+"/");
            		vo.setStreFileNm(file_s.getName());
            		vo.setOrignlFileNm(originalFilename);
            		vo.setFileExtsn(fileserice.fileExt(file_s,"."));      
                    vo.setFileOrder( Integer.toString( Integer.parseInt(atchFileId.replace("FILE_",""))));
                   
                    
                   if(contentFileInfoVO.getMediaType().equals("MEDIA")){
                    	contentFileInfoVO.setNotConType("MUSIC");
                    	vo.setReportGubun("reportGubun_4");
                   }else if (contentFileInfoVO.getMediaType().equals("IMAGE")){
                    	contentFileInfoVO.setNotConType("MUSIC");
                    	vo.setReportGubun("reportGubun_3");
                   }
                 
                   file_s = null;
                   
                   if(conFileManager.selectFilePageListByPaginationTotCnt_S(contentFileInfoVO) < 1) {
                	    conFileManager.insertFileManage(vo);
                	    
                    	map.put("message", "success");
                    	map.put("originalFilename", originalFilename);
                   } else {
                    	map.put("message", "fail");
                    	map.put("originalFilename", originalFilename);
                   }
                   contentFileInfoVO = null;
                } catch (Exception e) {                
                	LOGGER.error("fileUpload error:" + e.toString());
                    e.printStackTrace();
                    map.put("message", "fail");
                }				
			}
			
			fileserice = null;
		}catch (Exception e1){
			  e1.printStackTrace();
			  LOGGER.error("fileUpload error:" + e1.toString());
              map.put("message", "fail");
		}
		return map;
	}
	
}
