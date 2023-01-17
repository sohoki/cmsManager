package aten.com.backoffice.sts.xml.service.impl;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import aten.com.backoffice.sts.xml.mapper.SendMsgInfoManagerMapper;
import aten.com.backoffice.sts.xml.vo.SendMsgInfo;
import aten.com.backoffice.sts.xml.vo.SendMsgInfoVO;
import aten.com.backoffice.sts.xml.service.SendMsgInfoManageService;

@Service
public class SendMsgInfoManageServiceImpl extends EgovAbstractServiceImpl implements SendMsgInfoManageService{
	
	 
	 private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgInfoManageServiceImpl.class);
	
	 @Autowired
	 private SendMsgInfoManagerMapper sendMsg;
	 
	 @Autowired
	 protected EgovMessageSource egovMessageSource;

	@Override
	public ModelAndView selectSendMsgInfoManageListByPagination(SendMsgInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		//ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		ModelAndView model = new ModelAndView();
		try{
			  PaginationInfo paginationInfo = new PaginationInfo();
			  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			  paginationInfo.setPageSize(searchVO.getPageSize());

			  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			  searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
			  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			  List<SendMsgInfoVO> list = sendMsg.selectSendMsgInfoManageListByPagination(searchVO);
			  model.addObject("megLst", list);
		      int totCnt = list.size() > 0 ? list.get(0).getTotalRecordCount() : 0;
		      
			  paginationInfo.setTotalRecordCount(totCnt);
			  model.addObject("totalCnt", totCnt);
			  model.addObject("paginationInfo", paginationInfo);
		}catch(Exception e){
			LOGGER.error("SendMsgInfoManageServiceImpl ERROR:" + e.toString());
			throw e;
		} 
		return model;
	}

	/*@Override
	public int selectSendMsgInfoManageListTotCnt_S(SendMsgInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return sendMsg.selectSendMsgInfoManageListTotCnt_S(searchVO);
	}*/

	@Override
	public int selectAgentOrderCount(SendMsgInfo searchVO) throws Exception {
		// TODO Auto-generated method stub
		return sendMsg.selectAgentOrderCount(searchVO);
	}

	@Override
	public int selectAgentMessageCount(SendMsgInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return sendMsg.selectAgentMessageCount(searchVO);
	}

	@Override
	public List<SendMsgInfoVO> selectAgentOrderLst(SendMsgInfo searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return sendMsg.selectAgentOrderLst(searchVO);
	}


	@Override
	public ModelAndView insertSendMsgInfoManageList(List<SendMsgInfo> vo) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try{
			 sendMsg.insertSendMsgInfoManageList(vo);
			 model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			 model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("success.common.update"));	
		}catch(Exception e){
			LOGGER.error("insertSendMsgInfoManage ERROR:" + e.toString());
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.request.msg"));	
			throw e;
		}
		return model;
	}
	@Override
	public int insertSendMsgInfoManage(SendMsgInfo vo)throws Exception {
		// TODO Auto-generated method stub
//		int ret =0;
		try{
//			ret = sendMsg.insertSendMsgInfoManage(vo);
		}catch(Exception e){
			LOGGER.error("insertSendMsgInfoManage ERROR:" + e.toString());
			throw e;
		}
		return sendMsg.insertSendMsgInfoManage(vo);
	}

	@Override
	public int updateSendMsgInfoManage(SendMsgInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return sendMsg.updateSendMsgInfoManage(vo);
	}

		   

}
