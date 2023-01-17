package egovframework.mofa.moniter.page.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.service.Globals;
import egovframework.mofa.moniter.page.app.mapper.AppInfoManagerMapper;
import egovframework.mofa.moniter.page.mysql.mapper.StaticManagerMapper;
import egovframework.mofa.moniter.page.oracle.mapper.StaticMoniterManagerMapper;
import egovframework.mofa.moniter.page.service.StaticMoniterManagerService;
import egovframework.mofa.moniter.page.vo.StateInfo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StaticMoniterManagerServiceImpl extends EgovAbstractServiceImpl implements StaticMoniterManagerService  {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaticMoniterManagerServiceImpl.class);
	
	@Autowired
	private StaticManagerMapper mariaMapper;
	
	@Autowired
	private AppInfoManagerMapper appMapper;
	
	@Autowired
	private StaticMoniterManagerMapper staticMapper;
	
	@Override
	public List<Map<String, Object>> selectPageUni01() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni01();
	}

	@Override
	public List<Map<String, Object>> selectPageUni02() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni02();
	}

	@Override
	public List<Map<String, Object>> selectPageUni03(String agentid) throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni03(agentid);
	}
	@Override
	public Map<String, Object> selectPageUni04() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni04();
	}
	@Override
	public List<Map<String, Object>> selectPageUni05() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni05();
	}

	@Override
	public List<Map<String, Object>> selectPageUni06() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni06();
	}
	@Override
	public ModelAndView batchTest() throws Exception {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView(Globals.JSONVIEW);
		try{
			
			
			
			LOGGER.debug("-----------------1");
			
			List<StateInfo> page01 = mariaMapper.selectMariaPageUni01();
			for (StateInfo info : page01){
				
				staticMapper.insertPage01(info);
			}
			LOGGER.debug("-----------------2");
			StateInfo page02 = mariaMapper.selectPageMariaUni02();
			staticMapper.updatePage02(page02);
			
			List<StateInfo> page03 = mariaMapper.selectMariaPageUni03();
			for (StateInfo info : page03){
				staticMapper.insertPage03(info);
			}
			StateInfo page04 = mariaMapper.selectMariaPageUni04();
			staticMapper.updatePage04(page04);
			
			
			List<StateInfo> page05=  appMapper.selectApp01();
			for (StateInfo info : page05){
				if (info.getTypeGubun().equals("IN")){
					
					staticMapper.updatePage05(info);	
				}else {
					staticMapper.updatePage05_1(info);
				}
				
			}
			List<StateInfo> page06=  appMapper.selectApp02();
			for (StateInfo info : page06){
				if (info.getTypeGubun().equals("TOD")){
					staticMapper.updatePage06(info);	
				}else {
					staticMapper.updatePage06_1(info);
				}
			}
			
			
			
			
		}catch(Exception e){
			LOGGER.error("StaticChanageSchedule ERRROR:" + e.toString());
		}
		return model;
	}

	@Override
	public List<Map<String, Object>> selectPageUni07() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni07();
	}

	@Override
	public List<Map<String, Object>> selectPageUni08() throws Exception {
		// TODO Auto-generated method stub
		return staticMapper.selectPageUni08();
	}



	

}
