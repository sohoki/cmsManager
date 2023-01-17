package aten.com.backoffice.sym.monter.vo;

import java.io.Serializable;

import aten.com.backoffice.sym.monter.vo.DetailPageInfo;

public class DetailPageInfoVO extends DetailPageInfo implements Serializable {

	
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
    private String reportTitle = "";
    private String detailOrder = "";
    
    private String agentCode = "";
    private String displayPageCnt = "";
    private String reportUrl = "";
    private String detailSeq_N = "";
    private String reportGubun = "";
    private String replacePath = "";
	private String reportPreview = "";
	
	private int  totalRecordCount = 0; //TOTAL_RECORD_COUNT
	
	private String mediaType = "";
	private String fileWidth = "";
	private String fileHeight = "";
	private String reportDc = "";
	
	
	

	public String getReportDc() {
		return reportDc;
	}
	public void setReportDc(String reportDc) {
		this.reportDc = reportDc;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getFileWidth() {
		return fileWidth;
	}
	public void setFileWidth(String fileWidth) {
		this.fileWidth = fileWidth;
	}
	public String getFileHeight() {
		return fileHeight;
	}
	public void setFileHeight(String fileHeight) {
		this.fileHeight = fileHeight;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public String getReportPreview() {
		return reportPreview;
	}
	public void setReportPreview(String reportPreview) {
		this.reportPreview = reportPreview;
	}
	public String getReplacePath() {
		return replacePath;
	}
	public void setReplacePath(String replacePath) {
		this.replacePath = replacePath;
	}
	//신규 추가 구문
    private String reportSeqStatement;
    private String displaySeqStatement;
    
	public String getReportSeqStatement() {
		return reportSeqStatement;
	}
	public void setReportSeqStatement(String reportSeqStatement) {
		this.reportSeqStatement = reportSeqStatement;
	}
    
    
    
    
	public String getDisplaySeqStatement() {
		return displaySeqStatement;
	}
	public void setDisplaySeqStatement(String displaySeqStatement) {
		this.displaySeqStatement = displaySeqStatement;
	}
	public String getReportGubun() {
		return reportGubun;
	}

	public void setReportGubun(String reportGubun) {
		this.reportGubun = reportGubun;
	}

	public String getDetailSeq_N() {
		return detailSeq_N;
	}

	public void setDetailSeq_N(String detailSeq_N) {
		this.detailSeq_N = detailSeq_N;
	}

	public String getAgentCode() {
		return agentCode;
	}
	
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getDisplayPageCnt() {
		return displayPageCnt;
	}
	public void setDisplayPageCnt(String displayPageCnt) {
		this.displayPageCnt = displayPageCnt;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	
	public String getDetailOrder() {
		return detailOrder;
	}
	public void setDetailOrder(String detailOrder) {
		this.detailOrder = detailOrder;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
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
	
	@Override
	public String toString() {
		return "DetailPageInfoVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", mber_Sttus=" + mber_Sttus + ", pageIndex="
				+ pageIndex + ", pageUnit=" + pageUnit + ", pageSize="
				+ pageSize + ", firstIndex=" + firstIndex + ", lastIndex="
				+ lastIndex + ", recordCountPerPage=" + recordCountPerPage
				+ ", reportTitle=" + reportTitle + ", detailOrder="
				+ detailOrder + ", agentCode=" + agentCode
				+ ", displayPageCnt=" + displayPageCnt + ", reportUrl="
				+ reportUrl + ", detailSeq_N=" + detailSeq_N + ", reportGubun="
				+ reportGubun + ", replacePath=" + replacePath
				+ ", reportPreview=" + reportPreview + ", totalRecordCount="
				+ totalRecordCount + ", mediaType=" + mediaType
				+ ", fileWidth=" + fileWidth + ", fileHeight=" + fileHeight
				+ ", reportDc=" + reportDc + ", reportSeqStatement="
				+ reportSeqStatement + ", displaySeqStatement="
				+ displaySeqStatement + ", getUserId()=" + getUserId()
				+ ", getDetailSeq()=" + getDetailSeq() + ", getDisplaySeq()="
				+ getDisplaySeq() + ", getReportSeq()=" + getReportSeq()
				+ ", getDetailSort()=" + getDetailSort() + ", getDetailTime()="
				+ getDetailTime() + ", getFrstRegistPnttm()="
				+ getFrstRegistPnttm() + ", getFrstRegistId()="
				+ getFrstRegistId() + ", getLastUpdtPnttm()="
				+ getLastUpdtPnttm() + ", getLastUpusrId()=" + getLastUpusrId()
				+ ", getMode()=" + getMode() + "]";
	}
    
    
	
}
