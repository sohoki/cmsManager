package aten.com.backoffice.sym.monter.vo;

import java.io.Serializable;

public class DetailPageInfo implements  Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String detailSeq;
	private String displaySeq ;
	private String reportSeq ;
	private String detailSort;
	private String detailTime;
	private String  frstRegistPnttm;
	private String  frstRegistId;
	private String  lastUpdtPnttm;
	private String  lastUpusrId;
	private String mode;
	private String userId;
	
	
	
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDetailSeq() {
		return detailSeq;
	}
	public void setDetailSeq(String detailSeq) {
		this.detailSeq = detailSeq;
	}
	public String getDisplaySeq() {
		return displaySeq;
	}
	public void setDisplaySeq(String displaySeq) {
		this.displaySeq = displaySeq;
	}
	public String getReportSeq() {
		return reportSeq;
	}
	public void setReportSeq(String reportSeq) {
		this.reportSeq = reportSeq;
	}
	public String getDetailSort() {
		return detailSort;
	}
	public void setDetailSort(String detailSort) {
		this.detailSort = detailSort;
	}
	public String getDetailTime() {
		return detailTime;
	}
	public void setDetailTime(String detailTime) {
		this.detailTime = detailTime;
	}
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	public String getFrstRegistId() {
		return frstRegistId;
	}
	public void setFrstRegistId(String frstRegistId) {
		this.frstRegistId = frstRegistId;
	}
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	public String getLastUpusrId() {
		return lastUpusrId;
	}
	public void setLastUpusrId(String lastUpusrId) {
		this.lastUpusrId = lastUpusrId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
	
}
