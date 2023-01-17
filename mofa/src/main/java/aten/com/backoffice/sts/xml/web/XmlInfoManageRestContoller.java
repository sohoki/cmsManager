package aten.com.backoffice.sts.xml.web;


import egovframework.com.cmm.EgovMessageSource;
import aten.com.backoffice.sym.agent.service.AgentInfoManageService;
import aten.com.backoffice.sts.xml.vo.XmlInfo;
import aten.com.backoffice.sts.xml.service.XmlInfoManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.rte.fdl.property.EgovPropertyService;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@RestController
@RequestMapping("/backoffice/operManage/")
public class XmlInfoManageRestContoller {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlInfoManageRestContoller.class);

    @Autowired
    protected XmlInfoManageService xmlInfoManageService;
    
    @Autowired
	protected EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Autowired
    protected EgovPropertyService propertiesService;
    
    @Autowired
    protected AgentInfoManageService agentService;

	@RequestMapping("/jsonAuthView")
	public String jsonViewRes(@RequestParam String xmlSeq)throws Exception{
		return jsonDoc(xmlInfoManageService.selectXmlrInfoManageDetail(xmlSeq));
	}
	// jsonDoc 만들기
	public String jsonDoc(XmlInfo vo)
	{	
		String[] inputParamArrays ;
		inputParamArrays = vo.getXmlInputParam().split(",");
		String[] inputParamSampleArrays ;
		inputParamSampleArrays = vo.getXmlInputParamSample().split(",");
		
		
		JSONObject obj = new JSONObject();
		obj.put("command_type", vo.getXmlProcessName());
		
		try {			
			
			JSONArray dataArray = new JSONArray( ); 
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
	}
}
