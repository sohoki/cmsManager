package aten.com.backoffice.sym.cnt.vo;

import java.io.Serializable;
import aten.com.backoffice.sym.cnt.vo.CenterInfo;


public class CenterInfoVO extends CenterInfo implements Serializable {

    
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
    private int recordCountPerPage = 10;
    private String partNm;
    private String centerFloorTxt;
    private String centerFloorEndTxt;
    private String centerAgentCnt;
    private String partId;
    private String adminLevel;
    
    
    
    
    
    
    
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}
	public String getCenterAgentCnt() {
		return centerAgentCnt;
	}
	public void setCenterAgentCnt(String centerAgentCnt) {
		this.centerAgentCnt = centerAgentCnt;
	}
	public String getCenterFloorTxt() {
		return centerFloorTxt;
	}
	public void setCenterFloorTxt(String centerFloorTxt) {
		this.centerFloorTxt = centerFloorTxt;
	}
	public String getCenterFloorEndTxt() {
		return centerFloorEndTxt;
	}
	public void setCenterFloorEndTxt(String centerFloorEndTxt) {
		this.centerFloorEndTxt = centerFloorEndTxt;
	}
	public String getPartNm() {
		return partNm;
	}
	public void setPartNm(String partNm) {
		this.partNm = partNm;
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
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
    
	@Override
	public String toString() {
		return "CenterInfoVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", pageIndex=" + pageIndex + ", pageUnit="
				+ pageUnit + ", pageSize=" + pageSize + ", firstIndex="
				+ firstIndex + ", lastIndex=" + lastIndex
				+ ", recordCountPerPage=" + recordCountPerPage + ", partNm="
				+ partNm + ", centerFloorTxt=" + centerFloorTxt
				+ ", centerFloorEndTxt=" + centerFloorEndTxt
				+ ", centerAgentCnt=" + centerAgentCnt + ", partId=" + partId
				+ ", adminLevel=" + adminLevel + ", getTotalRecordCount()="
				+ getTotalRecordCount() + ", getCenterFloorEnd()="
				+ getCenterFloorEnd() + ", getCenterZipcode1()="
				+ getCenterZipcode1() + ", getCenterZipcode2()="
				+ getCenterZipcode2() + ", getCenterFloor()="
				+ getCenterFloor() + ", getMode()=" + getMode()
				+ ", getCenterId()=" + getCenterId() + ", getCenterNm()="
				+ getCenterNm() + ", getCenterZipcode()=" + getCenterZipcode()
				+ ", getCenterAddr1()=" + getCenterAddr1()
				+ ", getCenterAddr2()=" + getCenterAddr2()
				+ ", getCenterTel()=" + getCenterTel() + ", getCenterFax()="
				+ getCenterFax() + ", getCenterRegId()=" + getCenterRegId()
				+ ", getCenterRegdate()=" + getCenterRegdate()
				+ ", getCenterUpdateId()=" + getCenterUpdateId()
				+ ", getCenterImg()=" + getCenterImg() + ", getCenterUrl()="
				+ getCenterUrl() + ", getCenterSeatImg()=" + getCenterSeatImg()
				+ ", getCenterUseYn()=" + getCenterUseYn() + ", getRestInfo()="
				+ getRestInfo() + ", getMeetingroomInfo()="
				+ getMeetingroomInfo() + ", getCenterInfo()=" + getCenterInfo()
				+ ", getAdminApprovalYn()=" + getAdminApprovalYn() + "]";
	}
    
    
    
}
