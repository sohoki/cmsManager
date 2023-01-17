package aten.com.backoffice.sts.sch.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import aten.com.backoffice.sts.sch.mapper.ContentScheduleManagerMapper;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfo;
import aten.com.backoffice.sts.sch.vo.ContentScheduleInfoVO;
import aten.com.backoffice.sts.sch.service.ContentScheduleManagerService;

@Service
public class ContentScheduleManagerServiceImpl extends EgovAbstractServiceImpl implements ContentScheduleManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentScheduleManagerServiceImpl.class);
	
	
	@Autowired
	private ContentScheduleManagerMapper contentSchedule;
	
	@Resource(name="egovSchIdGnrService")
	private EgovIdGnrService egovConSchIdGnrService;

	@Override
	public ModelAndView selectContentSchduleInfoManageListByPagination( ContentScheduleInfoVO searchVO) throws Exception {
		
			ModelAndView model = new ModelAndView();
			try{
				
				PaginationInfo paginationInfo = new PaginationInfo();
				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
				paginationInfo.setPageSize(searchVO.getPageSize());
				searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
				searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
				searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				
				List<ContentScheduleInfoVO> conschList = contentSchedule.selectContentSchduleInfoManageListByPagination(searchVO); // contSchedule.selectContentSchduleInfoManageListByPagination(searchVO);
				model.addObject("resultList",   conschList);
			    
			    int totCnt =  conschList.size() > 0 ? conschList.get(0).getTotalRecordCount() : 0;       
				paginationInfo.setTotalRecordCount(totCnt);
			    model.addObject("paginationInfo", paginationInfo);
			    model.addObject("totalCnt", totCnt);
			    
			}catch(Exception e){
				LOGGER.error("selectContentSchduleInfoManageListByPagination ERROR:" + e.toString());
				throw e;
				
			}
			// TODO Auto-generated method stub
			
		return model;
	}

	@Override
	public ContentScheduleInfoVO selectConetntSchduleInfoManageView(
			String conschCode) throws Exception {
		// TODO Auto-generated method stub
		return contentSchedule.selectConetntSchduleInfoManageView(conschCode);
	}
	
	@Override
	public int updateContentSchduleInfoManage(ContentScheduleInfo vo)throws Exception {
		
		int ret = 0;
		if (vo.getMode().equals("Ins")){
			vo.setConschCode(egovConSchIdGnrService.getNextStringId());
			ret = contentSchedule.insertContentSchduleInfoManage(vo);
		}else {
			ret = contentSchedule.updateContentSchduleInfoManage(vo);
		}
		return ret ;
	}

	@Override
	public int deleteContentSchduleInfoManage(String conschCode)
			throws Exception {
		// TODO Auto-generated method stub
		return contentSchedule.deleteContentSchduleInfoManage(conschCode);
	}

	@Override
	public String conPageDetail(String displaySeq, String fileGubun) throws Exception {
		
        
		return null;
		
	}
	  
}
