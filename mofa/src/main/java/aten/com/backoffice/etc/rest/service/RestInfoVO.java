package aten.com.backoffice.etc.rest.service;

import java.io.Serializable;

public class RestInfoVO extends RestInfo   implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 검색조건 */
    private String searchCondition = "";    
    /** 검색Keyword */
    private String searchKeyword = "";    
    /** 검색사용여부 */
    private String searchUseYn = "";    
    
    private String mber_Sttus = "";
    
    
    /** 현재페이지 */
    private int pageIndex = 1;    
    /** 페이지갯수 */
    private int pageUnit = 10;    
    /** 페이지사이즈 */
    private int pageSize = 10;    
    private int firstIndex = 1;
    private int lastIndex = 1;    
    private int recordCountPerPage = 10;
    
    private String centerNm = "";
    private String menuGubunTxt = "";
    private int totalRecordCount = 0;
    private String searchStartDay = "";
    private String searchEndDay = "";
    private String searchMenugubun = "";
    private String searchOrder = "";
    
    private String searchTime = "";
    
    
    
    

	
	public String getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
	public String getSearchOrder() {
		return searchOrder;
	}
	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}
	public String getSearchMenugubun() {
		return searchMenugubun;
	}
	public void setSearchMenugubun(String searchMenugubun) {
		this.searchMenugubun = searchMenugubun;
	}
	public String getSearchStartDay() {
		return searchStartDay;
	}
	public void setSearchStartDay(String searchStartDay) {
		this.searchStartDay = searchStartDay;
	}
	public String getSearchEndDay() {
		return searchEndDay;
	}
	public void setSearchEndDay(String searchEndDay) {
		this.searchEndDay = searchEndDay;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public String getMenuGubunTxt() {
		return menuGubunTxt;
	}
	public void setMenuGubunTxt(String menuGubunTxt) {
		this.menuGubunTxt = menuGubunTxt;
	}
	public String getCenterNm() {
		return centerNm;
	}
	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
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
	public String getMber_Sttus() {
		return mber_Sttus;
	}
	public void setMber_Sttus(String mber_Sttus) {
		this.mber_Sttus = mber_Sttus;
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
    
    
    

}
