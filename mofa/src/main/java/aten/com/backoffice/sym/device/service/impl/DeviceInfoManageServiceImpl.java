package aten.com.backoffice.sym.device.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import aten.com.backoffice.sym.device.mapper.DeviceInfoManageMapper;
import aten.com.backoffice.sym.device.service.DeviceInfoManageService;
import aten.com.backoffice.sym.device.vo.DeviceInfo;
import aten.com.backoffice.sym.device.vo.DeviceInfoVO;

@Service
public class DeviceInfoManageServiceImpl extends EgovAbstractServiceImpl implements DeviceInfoManageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInfoManageServiceImpl.class);

	@Autowired
	private DeviceInfoManageMapper agentMapper;

	@Resource(name = "egovEqupIdGnrService")
	private EgovIdGnrService egovEqupIdGnrService;

	@Autowired
	protected EgovMessageSource egovMessageSource;

	@Override
	public ModelAndView selectAgentPageInfoManageListByPagination(DeviceInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try {
			/** pageing */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());

			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());

			LOGGER.debug("getPageIndex:" + searchVO.getPageIndex());
			LOGGER.debug("getPageUnit:" + searchVO.getPageUnit());
			LOGGER.debug("getPageSize:" + searchVO.getPageSize());
			LOGGER.debug("getFirstRecordIndex:" + searchVO.getFirstIndex());

			searchVO.setRecordCountPerPage(searchVO.getPageUnit());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());

			List<DeviceInfoVO> agentList = agentMapper.selectAgentPageInfoManageListByPagination(searchVO);
			model.addObject("resultList", agentList);
			List<DeviceInfoVO> floorLost = agentList.stream().sorted(Comparator.comparing((DeviceInfoVO vo) -> vo.getAgentFloor())).collect(Collectors.toList());
			model.addObject("agentList", floorLost);
			int totCnt = agentList.size() > 0 ? agentList.get(0).getTotalRecordCount() : 0;
			paginationInfo.setTotalRecordCount(totCnt);
			model.addObject("paginationInfo", paginationInfo);
			model.addObject("totalCnt", Integer.valueOf(totCnt));

		} catch (Exception e) {
			LOGGER.error("selectAgentPageInfoManageListByPagination : " + e.toString());
		}
		return model;
	}

/*	@Override
	public ModelAndView selectDisplayStageChangePageList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try {
			LOGGER.debug("params:" + params.toString());

			PaginationInfo leftPaginationInfo = new PaginationInfo();
			leftPaginationInfo.setCurrentPageNo(Integer.valueOf(params.get("leftPageIndex").toString()));
			leftPaginationInfo.setRecordCountPerPage(Integer.valueOf(params.get("recordCountPerPage").toString()));
			leftPaginationInfo.setPageSize(Integer.valueOf(params.get("recordCountPerPage").toString()));
			params.put("leftFirstIndex",leftPaginationInfo.getFirstRecordIndex());

			PaginationInfo rightPaginationInfo = new PaginationInfo();
			rightPaginationInfo.setCurrentPageNo(Integer.valueOf(params.get("rightPageIndex").toString()));
			rightPaginationInfo.setRecordCountPerPage(Integer.valueOf(params.get("recordCountPerPage").toString()));
			rightPaginationInfo.setPageSize(Integer.valueOf(params.get("recordCountPerPage").toString()));
			params.put("rightFirstIndex",rightPaginationInfo.getFirstRecordIndex());

			LOGGER.debug("params:" + params.toString());

			List<Map<String, Object>> disStateLists = agentMapper.selectDisplayStageChangePageList(params);
			int totCntLeft = 0;
			int totCntRight = 0;
			// 여기 부분 람다식으로 바꾸기
			for (Map<String, Object> disStateList : disStateLists) {
				if (disStateList.get("play_gubun").equals("OT")) {
					totCntLeft = Integer.valueOf(disStateList.get("total_record_count").toString());
				}
				if (disStateList.get("play_gubun").equals("IN")) {
					totCntRight = Integer.valueOf(disStateList.get("total_record_count").toString());
					break;
				}
			}

			
			 * Stream<Trade> filteredTrades =
			 * 
			 * trades.stream()
			 * 
			 * .filter(t -> t.getIssuer().equals("GOOG"))
			 * 
			 * .filter(t-> t.getQuantity()>100000)
			 * 
			 * .filter(t -> t.getStatus().equals("PEND"));
			 

			model.addObject("disStateList", disStateLists);
			model.addObject("totCntLeft", totCntLeft);
			leftPaginationInfo.setTotalRecordCount(totCntLeft);
			model.addObject("leftPaginationInfo", leftPaginationInfo);

			model.addObject("totCntRight", totCntRight);
			rightPaginationInfo.setTotalRecordCount(totCntRight);
			model.addObject("rightPaginationInfo", rightPaginationInfo);

			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("selectDisplayStageChangePageList ERROR : " + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.msg"));
		}
		return model;
	}

	@Override
	public ModelAndView updateAgentDisplayChange(AgentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try {
			agentMapper.updateAgentDisplayChange(vo);
			model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("updateAgentDisplayChange ERROR :" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE,egovMessageSource.getMessage("fail.common.msg"));
		}
		return model;
	}

	@Override
	public AgentInfoVO selectAgentPageInfoManageDetail(String agentCode) throws Exception {
		// TODO Auto-generated method stub
		return agentMapper.selectAgentPageInfoManageDetail(agentCode);
	}

	@Override
	public AgentInfoVO selectAgentPageInfoManageView(AgentInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return agentMapper.selectAgentPageInfoManageView(searchVO);
	}

	@Override
	public AgentInfoVO selectAgentUrlCheck(AgentInfo searchVO) throws Exception {
		// TODO Auto-generated method stub
		return agentMapper.selectAgentUrlCheck(searchVO);
	}

	@Override
	public int selectAgentExist(String agentCode) throws Exception {
		// TODO Auto-generated method stub
		return agentMapper.selectAgentExist(agentCode);
	}

	@Override
	public String selectDisplayCheck(AgentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return agentMapper.selectDisplayCheck(vo);
	}

	@Override
	public int selectAgentPageInfoManageListTotCnt_S(AgentInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return agentMapper.selectAgentPageInfoManageListTotCnt_S(searchVO);
	}*/
}
