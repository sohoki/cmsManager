package aten.com.backoffice.sts.xml.mapper;

import java.util.List;

import aten.com.backoffice.sts.xml.vo.XmlInfo;
import aten.com.backoffice.sts.xml.vo.XmlInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper
public interface XmlInfoManagerMapper {

    public List<XmlInfoVO> selectXmlInfoManageListByPagination(XmlInfoVO searchVO);
	
	public XmlInfoVO selectXmlrInfoManageDetail(String xmlSeq);
	
	public XmlInfoVO selectXmlrInfoManageNameDetail(String xmlProcessName);
	
	/*public List<XmlInfo> selectXmlProcessCombo();*/
	
	public int selectXmlInfoManageListTotCnt_S(XmlInfoVO searchVO);
	
	public int insertXmlInfoManage(XmlInfo vo);
	
	public int updateXmlInfoManage(XmlInfo vo);
	
	public int deleteXmlInfoManage(String xmlSeq);
	
	public int selectXmlProcessCount(String xmlProcessName);
	
	public String selectDIDProcessNm(String code);
}
