package aten.com.backoffice.sym.cnt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import aten.com.backoffice.sym.cnt.vo.CenterInfo;
import aten.com.backoffice.sym.cnt.vo.CenterInfoVO;
import aten.com.backoffice.sym.cnt.service.CenterInfoService;
import aten.com.backoffice.sym.cnt.mapper.CenterInfoManageMapper;

@Service
public class CenterInfoServiceImpl extends EgovAbstractServiceImpl implements  CenterInfoService {

	private static final Logger LOGGER = LoggerFactory .getLogger(CenterInfoServiceImpl.class);
	
	
	@Autowired
	private CenterInfoManageMapper centerMapper;

	@Autowired
	protected EgovPropertyService propertiesService;
	
	@Override
	public ModelAndView selectCenterInfoPageInfoManageListByPagination(CenterInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try{
			if (searchVO.getPageUnit() > 0) {
				searchVO.setPageUnit(searchVO.getPageUnit());
			} else {
				searchVO.setPageUnit(this.propertiesService.getInt("pageUnit"));
			}
			searchVO.setPageSize(this.propertiesService.getInt("pageSize"));

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());

			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());

			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			List<CenterInfoVO> list = this.centerMapper.selectCenterInfoManageListByPagination(searchVO);
			int totCnt = (list.size() > 0) ? Integer.valueOf(((CenterInfoVO) list.get(0)).getTotalRecordCount()).intValue() : 0;
			
			model.addObject("resultList", list);
			model.addObject("regist", searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addObject("paginationInfo", paginationInfo);
			model.addObject("totalCnt", Integer.valueOf(totCnt));
		}catch(Exception e){
			LOGGER.error("ERROR selectCenterInfoPageInfoManageListByPagination :" + e.toString());
			throw e;
		}
		return model;
	}

	@Override
	public List<CenterInfoVO> selectCenterCombo(CenterInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return centerMapper.selectCenterInfoManageCombo(vo);
	}

	@Override
	public int selectCenterInfoPageInfoManageListToCnt_s(CenterInfoVO serarch)
			throws Exception {
		// TODO Auto-generated method stub
		return centerMapper.selectCenterInfoManageListTotCnt_S(serarch);
	}

	@Override
	public CenterInfoVO selectCenterInfoDetail(String centerId)
			throws Exception {
		// TODO Auto-generated method stub
		return centerMapper.selectCenterInfoManageDetail(centerId);
	}

	@Override
	public int updateCenterInfoManage(CenterInfo vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (vo.getMode().equals("Ins")){
			ret = centerMapper.insertCenterInfoManage(vo);
		}else {
			ret = centerMapper.updateCenterInfoManage(vo);
		}
		return ret;
	}
	
	
	
}
