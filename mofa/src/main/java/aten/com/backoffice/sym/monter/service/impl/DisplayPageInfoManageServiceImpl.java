package aten.com.backoffice.sym.monter.service.impl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import aten.com.backoffice.sym.monter.mapper.DisplayPageInfoManageMapper;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfo;
import aten.com.backoffice.sym.monter.vo.DispalyPageInfoVO;
import aten.com.backoffice.sym.monter.service.DisplayPageInfoManageService;

@Service
public class DisplayPageInfoManageServiceImpl extends EgovAbstractServiceImpl implements  DisplayPageInfoManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DisplayPageInfoManageServiceImpl.class);
	
	@Autowired
	private DisplayPageInfoManageMapper dispalyMapper;
	
	@Autowired
    protected EgovPropertyService propertiesService;
	
	@Autowired
	protected EgovMessageSource egovMessageSource;
	
	@Override
	public ModelAndView selectDisplayPageInfoManageListByPagination(DispalyPageInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		
		ModelAndView  model = new ModelAndView();
		try{
			if(  searchVO.getPageUnit() > 0  ){    	   
		    	   searchVO.setPageUnit(searchVO.getPageUnit());
			 }else {
				   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
			 }
			 searchVO.setPageSize(propertiesService.getInt("pageSize"));
		       
			 
		       /** pageing */       
		   	 PaginationInfo paginationInfo = new PaginationInfo();
			 paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			 paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			 paginationInfo.setPageSize(searchVO.getPageSize());
			 searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			 searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			 searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			 searchVO.setDisplayGubun("DispalyGubun_2");
			 
			 List<Map<String, Object>> displayList = dispalyMapper.selectDisplayPageInfoManageListByPagination(searchVO);
		     
		     int totCnt = displayList.size() > 0 ? Integer.valueOf( displayList.get(0).get("total_record_count").toString()) : 0; 
	         
			 paginationInfo.setTotalRecordCount(totCnt);
			 model.addObject("resultList", displayList );
		     model.addObject("paginationInfo", paginationInfo);
		     model.addObject("totalCnt", totCnt);
		}catch(Exception e){
			LOGGER.error("ERROR selectDisplayPageInfoManageListByPagination :" + e.toString());
			throw e;
		}
		 
		return  model;
		
	}

	@Override
	public DispalyPageInfoVO selectDisplayPageInfoManageDetail(String reportSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.selectDisplayPageInfoManageDetail(reportSeq);
	}

	@Override
	public DispalyPageInfoVO selectDisplayPageInfoManageView(String displaySeq)
			throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.selectDisplayPageInfoManageView(displaySeq);
	}

	@Override
	public int selectDisplayPageInfoManageListTotCnt_S(
			DispalyPageInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.selectDisplayPageInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int updateDisplayPageInfoManage(DispalyPageInfo vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (vo.getMode().equals("Ins")){
			ret = dispalyMapper.insertDisplayPageInfoManage(vo);
		}else {
			ret = dispalyMapper.updateDisplayPageInfoManage(vo);
		}
		return ret;
	}

	@Override
	public ModelAndView deleteDisplayPageInfoManage(  String  displaySeq ) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try{
			//LOGGER.debug("searchVO:" + searchVO.toString());
			//여기 부분에서 전광판/현황판 구분 해서 정리 하기
			
			DispalyPageInfoVO searchVO = new DispalyPageInfoVO();
			searchVO.setDisplaySeq(displaySeq);
			
			int totalCnt = dispalyMapper.deleteDisplayPageInfoManage(searchVO ); 
			LOGGER.debug("totalCnt:" + totalCnt);
			
		    if (totalCnt == -1 ){
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("page.display.alert03"));	
				model.addObject(Globals.STATUS, Globals.STATUS_NTUNIQUE);
				
				
		    }else{
		    	model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.common.delete"));
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		    }
		}catch(Exception e){
			LOGGER.error("deleteDisplayPageInfoManage ERROR:" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("page.display.alert03"));	
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
		}
		
		return model;
		
		//return dispalyMapper.deleteDisplayPageInfoManage(displaySeq);
	}

	@Override
	public List<DispalyPageInfoVO> selectDisplayPageInfoCombo(DispalyPageInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.selectDisplayPageInfoCombo(searchVO);
	}

	@Override
	public int updateDisplayTimeInfoManage(String displaySeq) throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.updateDisplayTimeInfoManage(displaySeq);
	}

	@Override
	public String selectDisplayMaxSeq() throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.selectDisplayMaxSeq();
	}

	@Override
	public int updateDisplayPageUpManage(String displaySeq) throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.updateDisplayPageUpManage(displaySeq);
	}

	@Override
	public int updateDisplayPageDownManage(String displaySeq) throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.updateDisplayPageDownManage(displaySeq);
	}

	@Override
	public ModelAndView updateDisplayReSendManage(String displaySeq) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try{
			dispalyMapper.updateDisplayReSendManage(displaySeq);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.request.msg"));	
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		}catch(Exception e){
			LOGGER.debug("updateSisplayUpdateChange Error:" + e.toString());
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.request.msg"));	
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			
		}
		return model;
	}

	@Override
	public List<DispalyPageInfo> selectDisplayPageInfoContentCombo(String displayGubun)
			throws Exception {
		// TODO Auto-generated method stub
		return dispalyMapper.selectDisplayPageInfoContentCombo(displayGubun);
	}
	
	
}
