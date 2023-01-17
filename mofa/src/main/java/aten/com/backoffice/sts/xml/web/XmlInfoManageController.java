package aten.com.backoffice.sts.xml.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.AdminLoginVO;
import egovframework.com.cmm.service.Globals;
import aten.com.backoffice.sts.xml.vo.XmlInfoVO;
import aten.com.backoffice.sts.xml.service.XmlInfoManageService;
import aten.com.backoffice.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;

import aten.com.backoffice.sym.log.annotation.NoLogging;


@RestController
@RequestMapping("/backoffice/operManage")
public class XmlInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlInfoManageController.class);

    
	@Autowired
    protected XmlInfoManageService xmlInfoManageService;
    
    @Autowired
	protected EgovMessageSource egovMessageSource;
    
    @Autowired
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
	@Autowired
    protected EgovPropertyService propertiesService;

	@RequestMapping ("xmlList.do")
	public ModelAndView selectXmlLst(@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
									, @ModelAttribute("searchVO") XmlInfoVO searchVO
									, HttpServletRequest request
									, BindingResult bindingResult) throws Exception {
		
          ModelAndView model = new ModelAndView("/backoffice/operManage/xmlList");
		  //공용 확인 하기 
          
          try{
        	  Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	      if(!isAuthenticated) {
    	    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
    	    		model.setViewName("/backoffice/login");
    	    		return model;
    	      }
    		      
    		  if(  searchVO.getPageUnit() > 0  ){    	   
    	    	      searchVO.setPageUnit(searchVO.getPageUnit());
    		  }else {
    				   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
    		  }
    		  searchVO.setPageSize(propertiesService.getInt("pageSize"));
    		  model = xmlInfoManageService.selectXmlInfoManageListByPagination(searchVO);
    	      model.addObject("selectWorkgubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("WORKGUBUN"));
    	      model.addObject("regist", searchVO);
    	      
    	      
          }catch(Exception e){
        	  LOGGER.debug("selectXmlLst error: " + e.toString());
          }
	      
	      return model;
	}
	@RequestMapping("xmlView.do")
	public ModelAndView selectViewXml(@ModelAttribute("AdminLoginVO") AdminLoginVO loginVO
														, @ModelAttribute("XmlInfoVO") XmlInfoVO vo
														, HttpServletRequest request
														, BindingResult bindingResult) throws Exception {
		     
		    ModelAndView model = new ModelAndView("/backoffice/operManage/xmlView");
		    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    try{
		    	if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.setViewName("/backoffice/login");
		    		return model;
		        }
			    model.addObject("selectWorkgubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("WORKGUBUN"));
			    model.addObject("selectSendgubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("SEND_GUBUN"));
			    if (vo.getMode().equals("Viw")){
			    	model.addObject("regist", xmlInfoManageService.selectXmlrInfoManageDetail(vo.getXmlSeq()) );
			    }else {
			    	model.addObject("regist", vo);
			    }
			    model.addObject("registView", vo);
		    }catch(Exception e){
		    	model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
				model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.request.msg"));	
		    }
			return model;
	}
	
	@RequestMapping(value="xmlUpdate.do" , method=RequestMethod.POST)
	public ModelAndView updateXml(@ModelAttribute("loginVO") AdminLoginVO loginVO
			                                       , @RequestBody  XmlInfoVO vo
													, HttpServletRequest request
													, BindingResult bindingResult)throws Exception{
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		model.addObject("regist", vo);
		try{
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    if(!isAuthenticated) {
		    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
		    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
		    		return model;
		    }
		    else {
		    	HttpSession httpSession = request.getSession(true);
		    	loginVO = (AdminLoginVO)httpSession.getAttribute("AdminLoginVO");
		    	vo.setUserId(loginVO.getAdminId());
		    }
		    model = xmlInfoManageService.updateXmlInfoManage(vo);
			
		}catch (Exception e){
			model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.request.msg"));	
			
		}finally {
			
		}					
		return model;
		
	}
	// groupCodeJson 체크 
	@NoLogging
	@RequestMapping("jsonAuthView.do")
	public ModelAndView selectJsonPage(HttpServletRequest request,
			                                            @RequestParam("xmlSeq") String xmlSeq) throws Exception{	
		   
		   ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		   try{
			   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			   if(!isAuthenticated) {
			    		model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
			    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			    		return model;
			   }
			   model = xmlInfoManageService.selectXmlrInfoManageDetail_JSONVIEW(xmlSeq);
		   }catch(Exception e){
			   LOGGER.error("jsonAuthView :" + e.toString());
			   model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			   model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
		   }
		   
		   return model;
	}
	@NoLogging
	@RequestMapping({"jsonAuthReq.do"})
	public ModelAndView selectJsonSendPage(HttpServletRequest request,
			                                                   @RequestParam("xmlSeq") String xmlSeq)   throws Exception {
		
		ModelAndView model = new 	ModelAndView(Globals.JSONVIEW);
		try{
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if(!isAuthenticated) {
				        model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.login"));
			    		model.addObject(Globals.STATUS, Globals.STATUS_LOGINFAIL);
			    		return model;
			}
			model = xmlInfoManageService.selectXmlrInfoManageDetail_JSONVIEW(xmlSeq); 
			/*LOGGER.debug("----1:" +model.toString() ); */  
		    Gson gson = new Gson();
			
		     JSONParser  jsonparse = new JSONParser(); 		
		     JSONObject jsonObject = (JSONObject) jsonparse.parse( gson.toJson( model).toString().replace("'", "\""));	
		     
		     
		     
			 String json =jsonObject.get(Globals.JSON_RESULT_TOP).toString() ; 
			 jsonObject = (JSONObject) jsonparse.parse( json);	
			 
			 String commandType = jsonObject.get(Globals.JSON_RESULT).toString();
			 jsonObject = (JSONObject) jsonparse.parse( commandType);	
			 commandType = jsonObject.get(Globals.JSON_RESULT_COMMAND).toString();
			 
			 
			 int ProcessCk = xmlInfoManageService.selectXmlProcessCount(commandType);
			 if (ProcessCk > 0){	
				 model.addObject(Globals.STATUS, Globals.STATUS_SUCCESS);
				 
				 jsonObject = (JSONObject) jsonparse.parse( json);
				 json =jsonObject.get(Globals.JSON_RESULT).toString() ; 
				 
				 //LOGGER.debug("json result:" + xmlInfoManageService.selectXmlrInfoManageDetail_JSONVIEW_RESULT(json));
				 model.addObject(Globals.JSON_RESULT, xmlInfoManageService.selectXmlrInfoManageDetail_JSONVIEW_RESULT(json));
			 }else {
				 model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
				 model.addObject(Globals.JSON_RESULT,"NO_JSON");
			 }	
			 
			 
		}catch(Exception e){
			 LOGGER.error("jsonAuthReq.do :" + e.toString());
			 model.addObject(Globals.STATUS, Globals.STATUS_FAIL);
			 model.addObject(Globals.STATUS_MESSAGE, egovMessageSource.getMessage("fail.common.select"));	
		}
	    return model;
	  }
	// jsonDoc 만들기
	
	@RequestMapping("jsonAuth.do")
	public String selectJsonResultPage(HttpServletRequest request,
			                                        @RequestParam("json") String json) throws Exception{
		
		
		
		
	    String jsonResult = "";
	    json = json.replace("'", "\"").replace("&quot;","\"");;
	    JSONParser  jsonparse = new JSONParser(); 		
	    JSONObject jsonObject = (JSONObject) jsonparse.parse(json);						 
		String commandType = jsonObject.get("command_type").toString();
		
		int ProcessCk = xmlInfoManageService.selectXmlProcessCount(commandType);
		if (ProcessCk > 0){	
			jsonResult =xmlInfoManageService.selectXmlrInfoManageDetail_JSONVIEW_RESULT(json);
		}else {
			jsonResult = "NO_JSON";
		}	
		return jsonResult;
		
	}
	/*public String jsonDoc(XmlInfo vo)
	{	
		String[] inputParamArrays ;
		inputParamArrays = vo.getXmlInputParam().split(",");
		String[] inputParamSampleArrays ;
		inputParamSampleArrays = vo.getXmlInputParamSample().split(",");
		
		JSONObject obj = new JSONObject();
		obj.put("command_type", vo.getXmlProcessName());
		try {			
			 JSONArray dataArray = new JSONArray();
			 JSONObject sObject = new JSONObject();//배열 내에 들어갈 json			
			for (int i = 0; i < inputParamArrays .length; i++){			
				sObject.put(inputParamArrays[i].toString().trim(), inputParamSampleArrays[i].toString().trim());				
			}
			dataArray.add(sObject);
			obj.put("command_data", dataArray);
		}catch (JSONException e) {
			e.printStackTrace();
			LOGGER.debug("jsonDoc:"+ e.toString());			
		}		
	   return obj.toJSONString(); 	
	}*/
    /*public String jsonDocResult(String json) throws Exception{
		
		JSONParser  jsonparse = new JSONParser();
		String resultTxt = null; 
		try {
              JSONObject jsonObject = (JSONObject) jsonparse.parse(json);						 
			  String commandType = jsonObject.get("command_type").toString();
			  
			 
			  JSONArray dataInfoArray = (JSONArray) jsonObject.get("command_data");			 
			  JSONObject dataObject = (JSONObject) dataInfoArray.get(0);	
			  
			  
			  AgentInfo agentInfo = new AgentInfo();
			  SendMsgInfo sminfo = new SendMsgInfo();
			  
			  if (commandType.equals("SP_AUTH")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentIp(dataObject.get("AGENT_IP").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  
				  int ret = agentService.selectAgentExist(dataObject.get("AGENT_CODE").toString());
				  String msgResult = (ret > 0) ? "O": "E";
				  resultTxt = "{'command_type':'"+commandType+"','result':'" + msgResult + "'}";
				
				  if (ret > 0){
					  ret = agentService.updateAgentIpMac(agentInfo);
	                  msgResult = (ret > 0) ? "O": "F";
					  
					  resultTxt = "{'command_type':'"+commandType+"','result':'" + msgResult + "'}";
					  
					  //기록 넣기 
					  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
					  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
					  sminfo.setSendResult(msgResult);
					  sminfo.setXmlProcessName(commandType);
					  sendMsgService.insertSendMsgInfoManage(sminfo);
				  }
			  }else if (commandType.equals("SP_AGENTUPDATE")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentIp(dataObject.get("AGENT_IP").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  
				  
				  int ret = agentService.updateAgentIpMac(agentInfo);
				  
                  String msgResult = (ret > 0) ? "O": "F";
				  
				  resultTxt = "{'command_type':'"+commandType+"','result':'" + msgResult + "'}";
				  
				  //기록 넣기 
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(msgResult);
				  sminfo.setXmlProcessName(commandType);
				  sendMsgService.insertSendMsgInfoManage(sminfo);
				  
			  }else if (commandType.equals("SP_AGENTSTATE")){
				  
				  
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  AgentStateService.updateAgentStateCnt(dataObject.get("AGENT_CODE").toString());
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  String sendInfo = "";
				  String sendGubun = "";
				  AgentInfoVO info = agentService.selectAgentUrlCheck(agentInfo);
				  if (info.getAgentContentgubun().equals("AGENT_CONTENT_1")){
					  sendInfo = info.getUpdateChange().equals("N") ? "Y" : "N";
					  sendGubun = "URL_CNT";
				  }else {
					  sendInfo = (agentGroup.selectAgentSchCount(dataObject.get("AGENT_CODE").toString()) > 0)  ? "Y" : "N";
					  sendGubun = "SCH_CNT";
				  }
				  String orderCnt = (sendMsgService.selectAgentOrderCount(sminfo) > 0) ? "Y":"N";
				  String msgCnt = (schService.selectScheduleSendInfoCnt(dataObject.get("AGENT_CODE").toString()) > 0 ) ? "Y":"N";
				  //단말기 구분을 통해 값 변경 전달 
				  if(sendGubun.equals("URL_CNT")){
					  resultTxt = "{'command_type':'"+commandType+"','result':[{'"+sendGubun+"' : '"+sendInfo+"','ORD_CNT' : '"+orderCnt+"','MSG_CNT' : '"+msgCnt+"'}]}";
				  }else{
					  resultTxt = "{'command_type':'"+commandType+"','result':{'"+sendGubun+"' : '"+sendInfo+"','ORD_CNT' : '"+orderCnt+"','MSG_CNT' : '"+msgCnt+"'}}";
				  }
				  
			  }else if (commandType.equals("SP_AGENTURL")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  AgentInfoVO info = agentService.selectAgentUrlCheck(agentInfo);
				  
				  String url = "/backoffice/basicManage/AgentInfoPreview.do?agentCode="+ info.getAgentCode();
				  
				  
				  //url변경 관련 멧세지 넣기 
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult("N");
				  sminfo.setXmlProcessName(commandType);
				  sendMsgService.insertSendMsgInfoManage(sminfo);
				  String msgSeq = String.valueOf(sendMsgService.selectMaxSeq());
				  
				  resultTxt = "{'command_type':'"+commandType+"','result':[{'URL' : '"+url+"','MSG_SEQ' : '"+msgSeq+"'}]}";
				  
			  }else if (commandType.equals("SP_AGENTURLUPDATE")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  agentInfo.setUpdateChange(dataObject.get("URL_CHK").toString());
				  //변경 사항 등록
				  int ret =  agentService.updateAgentUrlRec(agentInfo);
				  
				  //변경 사항 등록
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(dataObject.get("URL_CHK").toString());
				  sminfo.setXmlProcessName(commandType);
				  sminfo.setErrorMessage(dataObject.get("CHANGE_URL").toString());
				  sminfo.setMsgSeq(dataObject.get("MSG_SEQ").toString());
				  sendMsgService.updateSendMsgInfoManage(sminfo);
				  
				  
				  String result = (ret > 0) ? "O" : "F"; 
				  //url변경 관련 멧세지 넣기 
				  
				  resultTxt = "{'command_type':'"+commandType+"','result':'"+result+"'}";
				  
			  }else if (commandType.equals("SP_AGENTCONTENTLST")){
				  agentInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  agentInfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  
				  List<AgentGroupInfoVO> resultContent =  agentGroup.selectDisplayPageInfoContentList(dataObject.get("AGENT_CODE").toString());
					
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  JSONArray dataArray = new JSONArray();
				  AgentGroupInfo info = new AgentGroupInfo();			
				  for (int i = 0; i < resultContent.size(); i++){
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
					agentGroup.updateAgentSendUpdate(info);
				  }			 	
				  info = null;
				  obj.put("CONINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();   
				  
			  }else if (commandType.equals("SP_AGENTCONTENTPAGELSTUPDATECHECK")){
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(dataObject.get("RECEIVED_RESULT").toString());
				  sminfo.setXmlProcessName(commandType);
				  sendMsgService.insertSendMsgInfoManage(sminfo);
				  
				  
				  
				  AgentGroupInfo info = new AgentGroupInfo();
				  info.setReceivedResult(dataObject.get("RECEIVED_RESULT").toString());
				  info.setGroupSeq(Integer.valueOf(dataObject.get("GROUP_SEQ").toString()));
				  int ret = agentGroup.updateAgentReceivedUpdate(info);
				  info = null;
				  String result = (ret > 0) ? "O" : "F"; 
				  //url변경 관련 멧세지 넣기 
				  
				  resultTxt = "{'command_type':'"+commandType+"','result':'"+result+"'}";
				  
			  }else if (commandType.equals("SP_AGENTCONTENTFILELST")){
				  AgentGroupInfo info = new AgentGroupInfo();
				  info.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  info.setGroupSeq( Integer.valueOf(dataObject.get("GROUP_SEQ").toString()));
				  
				  List<AgentGroupInfoVO> resultContent =  agentGroup.selectDisplayFileInfoContentList(info);
					
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
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
				  obj.put("CONINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();   
				  
			  }else if (commandType.equals("SP_AGENTCONTENTFILELSTUPDATECHECK")){
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setSendResult(dataObject.get("FILE_RESULT").toString());
				  sminfo.setXmlProcessName(commandType);
				  sendMsgService.insertSendMsgInfoManage(sminfo);
				  
				  
				  
				  AgentGroupInfo info = new AgentGroupInfo();
				  info.setReceivedFileResult(dataObject.get("FILE_RESULT").toString());
				  info.setGroupSeq(Integer.valueOf(dataObject.get("GROUP_SEQ").toString()));
				  int ret = agentGroup.updateAgentFileUpdate(info);
				  info = null;
				  String result = (ret > 0) ? "O" : "F"; 
				  //url변경 관련 멧세지 넣기 
				  
				  resultTxt = "{'command_type':'"+commandType+"','result':'"+result+"'}";
				  
			  }else if (commandType.equals("SP_AGENTORDERLST")){
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  
				  List<SendMsgInfoVO> resultLst = sendMsgService.selectAgentOrderLst(sminfo);
					
					JSONObject obj = new JSONObject();
				    obj.put("command_type", commandType);
				    JSONArray dataArray = new JSONArray();
				  			
				   for (int i = 0; i < resultLst.size(); i++){
					    JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put(  "MSG_SEQ", resultLst.get(i).getMsgSeq());
						sObject.put(  "XML_PROCESS_NAME", resultLst.get(i).getXmlProcessName());
						sObject.put(  "SEND_REGDATE", resultLst.get(i).getSendRegdate());						
						dataArray.add(sObject);
				   }			 	  
				   obj.put("CONINFO", dataArray);				 
				   resultTxt = obj.toJSONString();
				   obj.clear();				   
				  
			  }else if (commandType.equals("SP_AGENTORDERRESULT")){
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  
				  String result = dataObject.get("ERROR_MESSAGE").toString().equals("OK") ? "O" : "F";
				  sminfo.setSendResult(result);
				  sminfo.setXmlProcessName(commandType);
				  sminfo.setErrorMessage(dataObject.get("ERROR_MESSAGE").toString());
				  sminfo.setMsgSeq(dataObject.get("MSG_SEQ").toString());
				  int ret = sendMsgService.updateSendMsgInfoManage(sminfo);
				  result = (ret > 0)? "O" :"F";
				  resultTxt = "{'command_type':'"+commandType+"','result':'"+result+"'}";
				  
			  }else if (commandType.equals("SP_AGENTORD")){
				  //명령어 전송
				  agentInfo.setAgentCode(dataObject.get("DASAN_ID").toString());
				  agentInfo.setAgentMac(dataObject.get("DASAN_MAC").toString());
				  
				  AgentInfoVO info = agentService.selectAgentUrlCheck(agentInfo);
				  
				  
				  JSONObject jsonRes = new JSONObject();
				  OrderInfo orderInfo = orderService.selectAgentOrderList(info.getAgentCode());
				  
				  
				  resultTxt = "{'command_type':'"+commandType+"','result':{'ORD_SEQ': '"+orderInfo.getOrderSeq() +"','ORD_INFO',:'"+orderInfo.getAgentOrder()+"'}}";
				  
			  }else if (commandType.equals("SP_AGENTREBOOT")){
  					
				sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				sminfo.setXmlProcessName(commandType);
				sminfo.setSendResult("N");
				
				int ret =  sendMsgService.insertSendMsgInfoManage(sminfo);
				
				if ( ret > 0){
					int max_seq = sendMsgService.selectMaxSeq();
					resultTxt = "{'command_type':'"+commandType+"','result':'"+Integer.toString(max_seq)+"'}";
				}else {
					resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
				}	
			  }else if (commandType.equals("SP_AGENTMESSAGEINFO")){
				  
				  
				  sminfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sminfo.setAgentMac(dataObject.get("AGENT_MAC").toString());
				  // 메세지 전문 확인 후 메세지 TB_MESSAGEHISTORY 에 등록후 리스트 보여 주기 		
				  List<SchduleInfo> messageInfo = schService.selectScheduleSendInfo(dataObject.get("AGENT_CODE").toString());
				  
				  
				 
				  
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
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
				  
				  obj.put("MESSAGEINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  
			  }else if (commandType.equals("SP_AGENTMESSAGEUPDATE")){
				  
				  SendMessageInfoVO sendInfo = new SendMessageInfoVO();
				  sendInfo.setAgentCode(dataObject.get("AGENT_CODE").toString());
				  sendInfo.setSchCode(dataObject.get("SCH_CODE").toString());
				  
                  if (dataObject.get("ERROR_MESSAGE").toString().equals("OK")){
                	  sendInfo.setMsgRecCheck("Y");
				  }else {
					  sendInfo.setMsgRecCheck("N");
				  }
                  int ret = sendService.updateSendMessageAgent(sendInfo);
                  //업데이트 확인 
                  if (ret > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
				  }else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
				  }
			  }
			  
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultTxt;
    }*/
    
}
