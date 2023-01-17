package aten.com.backoffice.etc.rest.service;

import java.io.Serializable;

public class RestNoticeInfo  implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String noteSeq = "";
	private String noteTitle = "";
	private String noteStartdate = "";
	private String noteEnddate = "";
	private String noteContent = "";
	private String  frstRegistPnttm= "";
	private String  frstRegistId= "";
	private String  lastUpdtPnttm= "";
	private String  lastUpusrId= "";
	private String mode= "";
	private String userId = "";
	
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getNoteStartdate() {
		return noteStartdate;
	}
	public void setNoteStartdate(String noteStartdate) {
		this.noteStartdate = noteStartdate;
	}
	public String getNoteEnddate() {
		return noteEnddate;
	}
	public void setNoteEnddate(String noteEnddate) {
		this.noteEnddate = noteEnddate;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getNoteSeq() {
		return noteSeq;
	}
	public void setNoteSeq(String noteSeq) {
		this.noteSeq = noteSeq;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
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
	
	
	
	

}
