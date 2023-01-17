package aten.com.backoffice.sym.agent.web;


import aten.com.backoffice.sym.agent.service.AgentInfoManageService;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backoffice/contentManage/")
public class AgentRestController {

     @Autowired
	protected EgovMessageSource egovMessageSource;
	
     @Autowired
    protected EgovPropertyService propertiesService;
	
     @Autowired
    protected AgentInfoManageService agentService;
		
	@Resource(name="egovEqupIdGnrService")
	private EgovIdGnrService egovEqupIdGnrService;
	
	
}
