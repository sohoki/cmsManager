package aten.com.backoffice.sts.xml.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import aten.com.backoffice.sts.sch.mapper.AgentGroupInfoManagerMapper;
import aten.com.backoffice.sym.agent.mapper.AgentInfoManageMapper;
import aten.com.backoffice.sym.state.mapper.AgentStateManageMapper;
import aten.com.backoffice.sts.sch.mapper.SchduleInfoManageMapper;
import aten.com.backoffice.sts.message.mapper.SendMessageManageMapper;
import aten.com.backoffice.sts.message.vo.SendMessageInfoVO;
import aten.com.backoffice.sts.sch.vo.AgentGroupInfo;
import aten.com.backoffice.sts.sch.vo.AgentGroupInfoVO;
import aten.com.backoffice.sts.sch.vo.SchduleInfo;
import aten.com.backoffice.sts.xml.vo.SendMsgInfo;
import aten.com.backoffice.sts.xml.vo.SendMsgInfoVO;
import aten.com.backoffice.sts.xml.vo.XmlInfo;
import aten.com.backoffice.sts.xml.vo.XmlInfoVO;
import aten.com.backoffice.sts.xml.service.XmlInfoManageService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import aten.com.backoffice.sts.xml.mapper.XmlInfoManagerMapper;
import aten.com.backoffice.sym.agent.vo.AgentInfo;
import aten.com.backoffice.sym.agent.vo.AgentInfoVO;
import aten.com.backoffice.sym.log.annotation.NoLogging;

@Service
public class XmlInfoManageServiceImpl extends EgovAbstractServiceImpl implements XmlInfoManageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlInfoManageServiceImpl.class);
	
	@Autowired
	private XmlInfoManagerMapper xmlInfoManagerMapper;
	
	@Autowired
	protected EgovMessageSource egovMessageSource;
	
	@Autowired
	private AgentInfoManageMapper agentMapper;
	
	@Autowired
	private SendMsgInfoManagerMapper sendMsg;

	@Autowired
	private SendMessageManageMapper sendMapper;
	
	@Autowired
	private AgentStateManageMapper agentState;
	
	@Autowired
	private AgentGroupInfoManagerMapper agentGroup;
	
	@Autowired
	private SchduleInfoManageMapper schMapper;
	
	@Override
	public ModelAndView selectXmlInfoManageListByPagination(XmlInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		try{
			  PaginationInfo paginationInfo = new PaginationInfo();
			  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			  paginationInfo.setPageSize(searchVO.getPageSize());

			  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			  searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
			  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			  List<XmlInfoVO> list = xmlInfoManagerMapper.selectXmlInfoManageListByPagination(searchVO);
		      model.addObject("resultList", list);
		      int totCnt = list.size() > 0 ? list.get(0).getTotalRecordCount() : 0;
		      paginationInfo.setTotalRecordCount(totCnt);
		      model.addObject("paginationInfo", paginationInfo);
		      model.addObject("totalCnt", totCnt);
		}catch(Exception e){
			LOGGER.error("ERROR selectXmlInfoManageListByPagination:" + e.toString());
			throw e;
		} 
		return model;
	}
	@NoLogging
	@Override
	public XmlInfoVO selectXmlrInfoManageDetail(String xmlSeq) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlrInfoManageDetail(xmlSeq);
	}
   
	@Override
	public int selectXmlInfoManageListTotCnt_S(XmlInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlInfoManageListTotCnt_S(searchVO);
	}

	
	@Override
	public ModelAndView updateXmlInfoManage(XmlInfo vo) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		String message = "";
		try{

			int ret = 0;
			message = vo.getMode().equals("Ins") ? "sucess.common.insert": "sucess.common.update";
			
			if (vo.getMode().equals("Ins")){
				ret = xmlInfoManagerMapper.insertXmlInfoManage(vo);
				vo.setXmlSeq( vo.getXmlSeq() );
			}else {
				ret = xmlInfoManagerMapper.updateXmlInfoManage(vo);
			}
			if (ret >0){
				model.addObject("status", Globals.STATUS_SUCCESS);
			}else {
				model.addObject("status", Globals.STATUS_FAIL);
			}
			
			model.addObject("message",egovMessageSource.getMessage(message) );
			model.addObject("regist", vo);
		}catch(Exception e){
			LOGGER.error("ERROR updateXmlInfoManage:" + e.toString());
			message = vo.getMode().equals("Ins") ? "fail.common.insert": "fail.common.update";
			model.addObject("status", Globals.STATUS_FAIL);
			model.addObject("message",egovMessageSource.getMessage(message) );
		}
		return model;
	}

	@Override
	public int deleteXmlInfoManage(String xmlSeq) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.deleteXmlInfoManage(xmlSeq);
	}
	@NoLogging
	@Override
	public int selectXmlProcessCount(String xmlProcessName) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlProcessCount(xmlProcessName);
	}

	@Override
	public XmlInfoVO selectXmlrInfoManageNameDetail(String xmlProcessName)
			throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectXmlrInfoManageNameDetail(xmlProcessName);
	}

	@Override
	public String selectDIDProcessNm(String code) throws Exception {
		// TODO Auto-generated method stub
		return xmlInfoManagerMapper.selectDIDProcessNm(code);
	}
	@Override
	public ModelAndView selectXmlrInfoManageDetail_JSONVIEW(String xmlSeq)throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		
		JSONObject obj = new JSONObject();
	    try{	
	    	
	    	XmlInfoVO vo = xmlInfoManagerMapper.selectXmlrInfoManageDetail(xmlSeq);
	    	
	    	String[] inputParamArrays = vo.getXmlInputParam().split(",");
			String[] inputParamSampleArrays = vo.getXmlInputParamSample().split(",");
			//값이 같으면 처리 아니면 애러
			obj.put(Globals.JSON_RESULT_COMMAND, vo.getXmlProcessName());
			if (inputParamArrays.length ==  inputParamSampleArrays.length ){
				JSONArray dataArray = new JSONArray();
				JSONObject sObject = new JSONObject();//배열 내에 들어갈 json			
				for (int i = 0; i < inputParamArrays .length; i++){			
						sObject.put(inputParamArrays[i].toString().trim(), inputParamSampleArrays[i].toString().trim());				
				}
				dataArray.add(sObject);
				obj.put(Globals.JSON_RESULT_DATA, dataArray);
				model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
			}else {
				obj.put(Globals.JSON_RESULT_DATA,  Globals.JSON_RESULT_DATAERROR );
				model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			}
	    }catch(Exception e){
	    	e.printStackTrace();
			LOGGER.debug("jsonDoc:"+ e.toString());		
			obj.put(Globals.JSON_RESULT_COMMAND,   Globals.JSON_RESULT_COMMAND_ERROR );
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
	    }
	    model.addObject(Globals.JSON_RESULT,  obj.toString());
	    return model; 		
	}
	public Object jsonKeyToObject(String inputTxt, String SearchKey, String returnGubun) throws Exception{
		  Object value = null;
		  try{
			  JSONParser  jsonparse = new JSONParser();
			  JSONObject jsonObject = (JSONObject) jsonparse.parse(inputTxt);		
			  
			  if (returnGubun.equals("S") && inputTxt.contains(SearchKey)){
				  value = jsonObject.get(SearchKey).toString();
			  }else if (returnGubun.equals("A") && inputTxt.contains(SearchKey)){
				  JSONArray dataInfoArray = (JSONArray) jsonObject.get(SearchKey);			 
				  value = (JSONObject) dataInfoArray.get(0);	
			  }
		  }catch(Exception e){
				LOGGER.error("jsonKeyToObject Error:" + e.toString());
				value = "ERROR";
		  }
		  return value;
	}
	
	
	@NoLogging
	@Override
	public String selectXmlrInfoManageDetail_JSONVIEW_RESULT(String json) throws Exception {
		// TODO Auto-generated method stub
		
		//ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		
		
		JSONObject  oJson = new JSONObject();
		 JSONObject resultInfo = new JSONObject();
		try {
			
			  
			
			  LOGGER.debug("json:" +json);
			  /*loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
	    	  vo.setUserId(loginVO.getAdminId());*/
			
//			  JSONParser jsonparse = new JSONParser();
			  String commandType =  jsonKeyToObject(json,  Globals.JSON_RESULT_COMMAND, "S").toString(); 
			  //LOGGER.debug("commandType:"+commandType);
			  JSONObject dataObject  =  (JSONObject) jsonKeyToObject(json,  Globals.JSON_RESULT_DATA, "A"); 
			  //LOGGER.debug("dataObject:" + dataObject);
			  AgentInfo agentInfo = new AgentInfo();
			  SendMsgInfo sminfo = new SendMsgInfo();
			  //제이슨 생성
			  
			  oJson.put(Globals.JSON_RESULT_COMMAND, commandType);
			  
			  if (commandType.equals("SP_AUTH")){
				  
				  
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentIp(dataObject.get("AGENT_IP").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  
				  int ret = agentMapper.selectAgentExist(dataObject.get("AGENT_CODE").toString());
				  String msgResult = (ret > 0) ? "O": "E";
				  //resultTxt = "{'command_type':'"+commandType+"','result':'" + msgResult + "'}";
				  
				  oJson.put(Globals.JSON_RETURN_RESULT, msgResult);
				  if (ret > 0){
					  ret = agentMapper.updateAgentIpMac(agentInfo);
	                  msgResult = (ret > 0) ? "O": "F";
					  oJson.put(Globals.JSON_RETURN_RESULT, msgResult);
					  
					  //기록 넣기 
					  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
					  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
					  sminfo.setSendResult(msgResult);
					  sminfo.setXmlProcessName(commandType);
					  //aaaa
					  sendMsg.insertSendMsgInfoManage(sminfo);
				  }
			  }else if (commandType.equals("SP_AGENTUPDATE")){
				  
				  
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentIp(dataObject.get("AGENT_IP").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  
				  
				  int ret = agentMapper.updateAgentIpMac(agentInfo);
				  
                  String msgResult = (ret > 0) ? "O": "F";
                  oJson.put(Globals.JSON_RETURN_RESULT, msgResult);
				  //resultTxt = "{'command_type':'"+commandType+"','result':'" + msgResult + "'}";
				  
				  //기록 넣기 
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(msgResult);
				  sminfo.setXmlProcessName(commandType);
				  sendMsg.insertSendMsgInfoManage(sminfo);
				  
			  }else if (commandType.equals("SP_AGENTSTATE")){
				  JSONArray dataArray = new JSONArray();
				  
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  agentState.updateAgentStateCnt(dataObject.get("AGENT_CODE").toString());
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  String sendInfo = "";
				  String sendGubun = "";
				  //JSONObject resultInfo = new JSONObject();
				  AgentInfoVO info = new AgentInfoVO();
				  try{
					  info = agentMapper.selectAgentUrlCheck(agentInfo);
					  
					  if (info.getAgentContentgubun().equals("AGENT_CONTENT_1")){
						  sendInfo = info.getUpdateChange().equals("N") ? "Y" : "N";
						  sendGubun = "URL_CNT";
					  }else {
						  sendInfo = (agentGroup.selectAgentSchCount(dataObject.get("AGENT_CODE").toString()) > 0)  ? "Y" : "N";
						  sendGubun = "SCH_CNT";
					  }
					  String orderCnt = (sendMsg.selectAgentOrderCount(sminfo) > 0) ? "Y":"N";
					  String msgCnt = (schMapper.selectScheduleSendInfoCnt(dataObject.get("AGENT_CODE").toString()) > 0 ) ? "Y":"N";
					  //단말기 구분을 통해 값 변경 전달 
					  resultInfo.put(sendGubun, sendInfo);
					  resultInfo.put("ORD_CNT", orderCnt);
					  resultInfo.put("MSG_CNT", msgCnt);
					  dataArray.add(resultInfo) ;
				  }catch(Exception e){
					  resultInfo.put("ERROR", "AGENT NOT INFOMATION");
				  }
				  
				  if (info.getAgentContentgubun().equals("AGENT_CONTENT_1")){
					  oJson.put(Globals.JSON_RETURN_RESULT, dataArray);
				  } else {
					  oJson.put(Globals.JSON_RETURN_RESULT, resultInfo);
				  }
				  
				  
				  //resultInfo.clear();
				  
			  }else if (commandType.equals("SP_AGENTURL")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  AgentInfoVO info = agentMapper.selectAgentUrlCheck(agentInfo);
				  
				  String url = "/backoffice/basicManage/AgentInfoPreview.do?agentCode="+ info.getAgentCode();
				  
				  
				  //url변경 관련 멧세지 넣기 
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult("N");
				  sminfo.setXmlProcessName(commandType);
				  sendMsg.insertSendMsgInfoManage(sminfo);
				  //String msgSeq = String.valueOf(sendMsg.selectMaxSeq());
				  JSONArray dataArray = new JSONArray();
				  resultInfo.put("URL", url);
				  resultInfo.put("MSG_SEQ", sminfo.getMsgSeq());
				  //dataArray.add(resultInfo.toJSONString().replace("\"", "''").replaceAll("\\\"", "")) ;
				  dataArray.add(resultInfo) ;
				 
				  oJson.put(Globals.JSON_RETURN_RESULT,    dataArray);
				  //resultInfo.clear();
				  //resultTxt = "{'command_type':'"+commandType+"','result':[{'URL' : '"+url+"','MSG_SEQ' : '"+msgSeq+"'}]}";
				  
			  }else if (commandType.equals("SP_AGENTURLUPDATE")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  agentInfo.setUpdateChange(dataObject.get("URL_CHK").toString());
				  //변경 사항 등록
				  int ret =  agentMapper.updateAgentUrlRec(agentInfo);
				  
				  //변경 사항 등록
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(dataObject.get("URL_CHK").toString());
				  sminfo.setXmlProcessName(commandType);
				  /*sminfo.setErrorMessage(dataObject.get("CHANGE_URL").toString());*/
				  sminfo.setMsgSeq(dataObject.get("MSG_SEQ").toString());
				  sendMsg.updateSendMsgInfoManage(sminfo);
				  
				  
				  String result = (ret > 0) ? "O" : "F"; 
				  //url변경 관련 멧세지 넣기 
				  oJson.put(Globals.JSON_RETURN_RESULT, result);
				  
			  }else if (commandType.equals("SP_AGENTENDTIME") ){
				  //사용 하는지 확인 필요
				  
				  
			  }else if (commandType.equals("SP_AGENTENDTIMERESULT") ){
				  //사용 하는지 확인 필요
				  
				  
			  }else if (commandType.equals("SP_AGENTCONTENTLST")){
				  
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  JSONObject obj = new JSONObject();
				  obj.put(Globals.JSON_RESULT_COMMAND, commandType);
				  //여기 부분 확인 필요
				  List<AgentGroupInfoVO> resultContent =  agentGroup.selectDisplayPageInfoContentList(dataObject.get("AGENT_CODE").toString());
				  JSONArray dataArray = new JSONArray();
				  if (resultContent.size() > 0){
					  
					  
					  //AgentGroupInfo info = new AgentGroupInfo();	
					  List<AgentGroupInfo> infoList = new ArrayList<AgentGroupInfo>();		
					  for (int i = 0; i < resultContent.size(); i++){
						AgentGroupInfo info =new AgentGroupInfo();
						JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						String[] localFile = resultContent.get(i).getDisplayLocalfile().split("\\.");
						sObject.put("GROUP_SEQ", resultContent.get(i).getGroupSeq());
						sObject.put("CONSCH_CODE", resultContent.get(i).getConschCode());
						sObject.put("DISPLAY_TITLE", URLDecoder.decode(resultContent.get(i).getDisplayTitle(), "UTF-8"));
						sObject.put("DISPLAY_WIDTH", resultContent.get(i).getDisplayWidth());
						sObject.put("DISPLAY_HEIGHT", resultContent.get(i).getDisplayHeight());
						sObject.put("DISPALY_TOTALTIME", resultContent.get(i).getDisplayTotalTime());
						sObject.put("DISPLAY_NEXTSEQ", resultContent.get(i).getDisplayNextseq());
						sObject.put("DISPLAY_LOCALFILE", localFile[0] +"_local.html");
						dataArray.add(sObject);
						
						info.setGroupSeq(resultContent.get(i).getGroupSeq());
						info.setSendResult("O");
						infoList.add(info);
					  }			 
					  agentGroup.updateAgentSendUpdate(infoList);
					  oJson.put(Globals.JSON_RETURN_CONFIGE, dataArray);
					  infoList = null;
				  }else {
					  oJson.put(Globals.JSON_RETURN_CONFIGE, "NO_DATA");
				  }
				 
				  LOGGER.debug("------------------------------------------------------------------");
				  
				  //obj.put("CONINFO", dataArray);				 
				  //resultTxt = obj.toJSONString();
				  //obj.clear();   
				  
			  }else if (commandType.equals("SP_AGENTCONTENTPAGELSTUPDATECHECK")){
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(dataObject.get("RECEIVED_RESULT").toString());
				  sminfo.setXmlProcessName(commandType);
				  sendMsg.insertSendMsgInfoManage(sminfo);
				  
				  
				  
				  AgentGroupInfo info = new AgentGroupInfo();
				  info.setReceivedResult(dataObject.get("RECEIVED_RESULT").toString());
				  info.setGroupSeq(Integer.valueOf(dataObject.get("GROUP_SEQ").toString()));
				  int ret = agentGroup.updateAgentReceivedUpdate(info);
				  info = null;
				  String result = (ret > 0) ? "O" : "F"; 
				  //url변경 관련 멧세지 넣기 
				  oJson.put(Globals.JSON_RETURN_RESULT, result);
				  //resultTxt = "{'command_type':'"+commandType+"','result':'"+result+"'}";
				  
			  }else if (commandType.equals("SP_AGENTCONTENTFILELST")){
				  AgentGroupInfo info = new AgentGroupInfo();
				  info.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  info.setGroupSeq( Integer.valueOf(dataObject.get("GROUP_SEQ").toString()));
				  
				  List<AgentGroupInfoVO> resultContent =  agentGroup.selectDisplayFileInfoContentList(info);
					
//				  JSONObject obj = new JSONObject();
				  //obj.put(Globals.JSON_RESULT_COMMAND, commandType);
				  JSONArray dataArray = new JSONArray();
						
				  for (int i = 0; i < resultContent.size(); i++){
					JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					sObject.put("GROUP_SEQ", resultContent.get(i).getGroupSeq());
					sObject.put("CONSCH_CODE", resultContent.get(i).getConschCode());
					sObject.put("DISPLAY_SEQ", resultContent.get(i).getDisplaySeq());
					sObject.put("REPORT_URL", resultContent.get(i).getReportUrl());
					sObject.put("ATCH_FILE_ID", resultContent.get(i).getAtchFileId());
					sObject.put("STRE_FILE_NM", resultContent.get(i).getStreFileNm());
					sObject.put("FILE_EXTSN", resultContent.get(i).getFileExtsn());
					sObject.put("FILE_SIZE", resultContent.get(i).getFileSize());
					dataArray.add(sObject);
				  }			 	
				  info = null;
				  oJson.put(Globals.JSON_RETURN_CONFIGE, dataArray);
				  /*obj.put("CONINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();   */
				  
			  }else if (commandType.equals("SP_AGENTCONTENTFILELSTUPDATECHECK")){
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(dataObject.get("FILE_RESULT").toString());
				  sminfo.setXmlProcessName(commandType);
				  sendMsg.insertSendMsgInfoManage(sminfo);
				  
				  
				  
				  AgentGroupInfo info = new AgentGroupInfo();
				  info.setReceivedFileResult(dataObject.get("FILE_RESULT").toString());
				  info.setGroupSeq(Integer.valueOf(dataObject.get("GROUP_SEQ").toString()));
				  int ret = agentGroup.updateAgentFileUpdate(info);
				  info = null;
				  String result = (ret > 0) ? "O" : "F"; 
				  //url변경 관련 멧세지 넣기 
				  oJson.put(Globals.JSON_RETURN_RESULT, result);
				  //resultTxt = "{'command_type':'"+commandType+"','result':'"+result+"'}";
				  
			  }else if (commandType.equals("SP_AGENTORDERLST")){
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  
				  List<SendMsgInfoVO> resultLst = sendMsg.selectAgentOrderLst(sminfo);
					
//					JSONObject obj = new JSONObject();
				    //obj.put(Globals.JSON_RESULT_COMMAND, commandType);
				    JSONArray dataArray = new JSONArray();
				  			
				   for (int i = 0; i < resultLst.size(); i++){
					    JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put(  "MSG_SEQ", resultLst.get(i).getMsgSeq());
						sObject.put(  "XML_PROCESS_NAME", resultLst.get(i).getXmlProcessName());
						sObject.put(  "SEND_REGDATE", resultLst.get(i).getSendRegdate());						
						dataArray.add(sObject);
				   }	
				   oJson.put(Globals.JSON_RETURN_CONFIGE, dataArray);
				   /*obj.put("CONINFO", dataArray);				 
				   resultTxt = obj.toJSONString();
				   obj.clear();*/				   
				  
			  }else if (commandType.equals("SP_AGENTORDERRESULT")){
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  
				  String result = dataObject.get("ERROR_MESSAGE").toString().equals("OK") ? "O" : "F";
				  sminfo.setSendResult(result);
				  sminfo.setXmlProcessName(commandType);
				  sminfo.setErrorMessage(dataObject.get("ERROR_MESSAGE").toString());
				  sminfo.setMsgSeq(dataObject.get("MSG_SEQ").toString());
				  int ret = sendMsg.updateSendMsgInfoManage(sminfo);
				  result = (ret > 0)? "O" :"F";
				  oJson.put(Globals.JSON_RETURN_RESULT, result);
				  
			  }else if (commandType.equals("SP_AGENTORD")){
				  //명령어 전송
				  /*agentInfo.setAgentCode(dataObject.get("DASAN_ID").toString());
				  agentInfo.setAgentMac(dataObject.get("DASAN_MAC").toString());
				  AgentInfoVO info = agentMapper.selectAgentUrlCheck(agentInfo);
				  JSONObject jsonRes = new JSONObject();
				  OrderInfo orderInfo = orderMapper.selectAgentOrderList(info.getAgentCode());
				  
				  aaa
				  //resultTxt = "{'command_type':'"+commandType+"','result':{'ORD_SEQ': '"+orderInfo.getOrderSeq() +"','ORD_INFO',:'"+orderInfo.getAgentOrder()+"'}}";
				  */
			  }else if (commandType.equals("SP_AGENTREBOOT")){
  					
				sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				sminfo.setXmlProcessName(commandType);
				sminfo.setSendResult("N");
				
				sendMsg.insertSendMsgInfoManage(sminfo) ;
				String maxSeq = !sminfo.getMsgSeq().equals("") ? sminfo.getMsgSeq() : "F";
				LOGGER.debug("maxSeq:" +  sminfo.getMsgSeq());
				oJson.put(Globals.JSON_RETURN_RESULT, maxSeq);
				
				
			  }else if (commandType.equals("SP_AGENTMESSAGEINFO")){
				  
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  // 메세지 전문 확인 후 메세지 TB_MESSAGEHISTORY 에 등록후 리스트 보여 주기 		
				  List<SchduleInfo> messageInfo = schMapper.selectScheduleSendInfo(dataObject.get("AGENT_CODE").toString());
				 
				  
				  //JSONObject obj = new JSONObject();
				  //obj.put(Globals.JSON_RESULT_COMMAND, commandType);
				  JSONArray dataArray = new JSONArray();
				  for  (int i = 0; i < messageInfo.size(); i++){
					  JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					  //SEND_MESSAGE, SEND_MESSAGESTARTDAY, SEND_MESSAGEENDDAY, SEND_MESSAGESTARTTIME, SEND_MESSAGEENDTIME, SEND_FONTTYPE
					  sObject.put(  "SCH_CODE", messageInfo.get(i).getSchCode());
					  sObject.put(  "SEND_MESSAGE", URLDecoder.decode(messageInfo.get(i).getSchMessage(),"UTF-8"));
					  sObject.put(  "SEND_MESSAGESTARTDAY", messageInfo.get(i).getSchStartDay());
					  sObject.put(  "SEND_MESSAGEENDDAY", messageInfo.get(i).getSchEndDay());
					  sObject.put(  "SEND_MESSAGESTARTTIME", messageInfo.get(i).getSchStartTime());
					  sObject.put(  "SEND_MESSAGEENDTIME", messageInfo.get(i).getSchEndTime());
					  sObject.put(  "SEND_FONTTYPE", messageInfo.get(i).getSchFonttype());
					  dataArray.add(sObject);
				  }
				  oJson.put(Globals.JSON_RETURN_MESSAGEKEY, dataArray);
				  /*obj.put("MESSAGEINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();*/
				  
			  }else if (commandType.equals("SP_AGENTMESSAGEUPDATE")){
				  
				  SendMessageInfoVO sendInfo = new SendMessageInfoVO();
				  sendInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sendInfo.setSchCode(dataObject.get("SCH_CODE").toString());
				  
                  if (dataObject.get("ERROR_MESSAGE").toString().equals("OK")){
                	  sendInfo.setMsgRecCheck("Y");
				  }else {
					  sendInfo.setMsgRecCheck("N");
				  }
//                int ret = sendMapper.updateSendMessageAgent(sendInfo);
                  //업데이트 확인 
                  String resultT = sendMapper.updateSendMessageAgent(sendInfo) > 0 ? "O" : "F";
                  
                  oJson.put(Globals.JSON_RETURN_RESULT, resultT);
                 
			  }
			  
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		String returnTxt = oJson.toJSONString().replace("\"", "'").replace("\\", "");
		resultInfo.clear();
		oJson.clear();
		return returnTxt; // resultTxt;
		
	}
	
}
