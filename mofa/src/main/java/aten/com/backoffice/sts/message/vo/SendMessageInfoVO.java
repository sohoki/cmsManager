package aten.com.backoffice.sts.message.vo;

import java.io.Serializable;
import java.util.List;

import aten.com.backoffice.sts.message.vo.SendMessageInfo;

public class SendMessageInfoVO extends SendMessageInfo implements Serializable  {

	
	private static final long serialVersionUID = 1L;
	/** 검색조건 */
    private String searchCondition = "";    
    /** 검색Keyword */
    private String searchKeyword = "";    
    /** 검색사용여부 */
    private String searchUseYn = "";    
    
    /** 현재페이지 */
    private int pageIndex = 1;    
    /** 페이지갯수 */
    private int pageUnit = 10;    
    /** 페이지사이즈 */
    private int pageSize = 10;    
    private int firstIndex = 1;
    private int lastIndex = 1;
    private int recordCountPerPage = 1;
    //배열 형태로 메세지 받기
    private List<String> agentCodeLst;;
    private String agentNm = "";;
    private String agentRemark = "";;
    private String adminLevel;
    private String partId;
    
    
    public String getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	private String schMessage = "";
    private String schFonttype = "";
    private int totalRecordCount = 0;
    private String errorMessage = "";
    
    
    
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public String getSchMessage() {
		return schMessage;
	}
	public void setSchMessage(String schMessage) {
		this.schMessage = schMessage;
	}
	public String getSchFonttype() {
		return schFonttype;
	}
	public void setSchFonttype(String schFonttype) {
		this.schFonttype = schFonttype;
	}
	public String getAgentRemark() {
		return agentRemark;
	}
	public void setAgentRemark(String agentRemark) {
		this.agentRemark = agentRemark;
	}
	public String getAgentNm() {
		return agentNm;
	}
	public void setAgentNm(String agentNm) {
		this.agentNm = agentNm;
	}
	public List<String> getAgentCodeLst() {
		return agentCodeLst;
	}
	public void setAgentCodeLst(List<String> agentCodeLst) {
		this.agentCodeLst = agentCodeLst;
	}
	
	
	
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	@Override
	public String toString() {
		return "SendMessageInfoVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", pageIndex=" + pageIndex + ", pageUnit="
				+ pageUnit + ", pageSize=" + pageSize + ", firstIndex="
				+ firstIndex + ", lastIndex=" + lastIndex
				+ ", recordCountPerPage=" + recordCountPerPage
				+ ", agentCodeLst=" + agentCodeLst + ", agentNm=" + agentNm
				+ ", agentRemark=" + agentRemark + ", adminLevel=" + adminLevel
				+ ", partId=" + partId + ", schMessage=" + schMessage
				+ ", schFonttype=" + schFonttype + ", totalRecordCount="
				+ totalRecordCount + ", errorMessage=" + errorMessage
				+ ", getAdminLevel()=" + getAdminLevel() + ", getPartId()="
				+ getPartId() + ", getRecordCountPerPage()="
				+ getRecordCountPerPage() + ", getErrorMessage()="
				+ getErrorMessage() + ", getTotalRecordCount()="
				+ getTotalRecordCount() + ", getSchMessage()="
				+ getSchMessage() + ", getSchFonttype()=" + getSchFonttype()
				+ ", getAgentRemark()=" + getAgentRemark() + ", getAgentNm()="
				+ getAgentNm() + ", getAgentCodeLst()=" + getAgentCodeLst()
				+ ", getSearchCondition()=" + getSearchCondition()
				+ ", getSearchKeyword()=" + getSearchKeyword()
				+ ", getSearchUseYn()=" + getSearchUseYn()
				+ ", getPageIndex()=" + getPageIndex() + ", getPageUnit()="
				+ getPageUnit() + ", getPageSize()=" + getPageSize()
				+ ", getFirstIndex()=" + getFirstIndex() + ", getLastIndex()="
				+ getLastIndex() + "]";
	}   
    
    
    
    
    
}
