package aten.com.backoffice.uat.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import aten.com.backoffice.uat.mapper.PartInfoManageMapper;
import aten.com.backoffice.uat.vo.PartInfo;
import aten.com.backoffice.uat.vo.PartInfoVO;
import aten.com.backoffice.uat.service.PartInfoManageService;


@Service
public class PartInfoManageServiceImpl extends EgovAbstractServiceImpl implements PartInfoManageService {
	
     @Resource(name = "prop")
	 private Properties fileProperties;
    
	@Resource(name="egovGroupIdGnrService")
	private EgovIdGnrService egovGroupIdGnrService;
	
	@Autowired
	private PartInfoManageMapper partMapper;

	@Override
	public List<PartInfoVO> selectPartInfoPageInfoManageListByPagination(PartInfoVO serarch) throws Exception {
		return partMapper.selectPartInfoPageInfoManageListByPagination(serarch);
	}

	@Override
	public List<PartInfoVO> selectPartInfoCombo() throws Exception {
		return partMapper.selectPartInfoCombo();
	}

	@Override
	public int selectPartInfoPageInfoManageListToCnt_s(PartInfoVO serarch) throws Exception {
		return partMapper.selectPartInfoPageInfoManageListToCnt_s(serarch);
	}

	@Override
	public PartInfoVO selectPartInfoDetail(String partId) throws Exception {
		// TODO Auto-generated method stub
		return partMapper.selectPartInfoDetail(partId);
	}

	@Override
	public int updatePartInfoManage(PartInfo vo) throws Exception {
		// TODO Auto-generated method stub
		//환경 변수가 오라클이냐 아니냐 구분으로 확인 하기 
		
		if (vo.getMode().equals("Ins")){
			
			if ( fileProperties.getProperty("Globals.DbType").equals("oracle")){
				vo.setPartId(egovGroupIdGnrService.getNextStringId());
			}
			return partMapper.insertPartInfoManage(vo);
		}else {
			return partMapper.updatePartInfoManage(vo);
		}
	}
	
	

}
