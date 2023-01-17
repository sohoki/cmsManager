package egovframework.mofa.moniter.page.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.mofa.moniter.page.app.mapper.AppInfoManagerMapper;
import egovframework.mofa.moniter.page.mysql.mapper.StaticManagerMapper;
import egovframework.mofa.moniter.page.oracle.mapper.StaticMoniterManagerMapper;
import egovframework.mofa.moniter.page.vo.StateInfo;



@Service("StateSchedulerService")
public class StateSchedulerService {

	
	private static final Logger LOGGER = Logger.getLogger(StateSchedulerService.class);
	
	@Autowired
	private StaticManagerMapper mariaMapper;
	
	@Autowired
	private StaticMoniterManagerMapper staticMapper;
	
	@Autowired
	private AppInfoManagerMapper appMapper;
	
	
	public void StaticChanageSchedule()throws Exception {
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
		
	}
}
