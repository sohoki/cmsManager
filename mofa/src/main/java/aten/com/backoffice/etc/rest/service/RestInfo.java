package aten.com.backoffice.etc.rest.service;

import java.io.Serializable;

public class RestInfo implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String menuSeq = "";
	private String menuDate = "";
	private String menuInfo = "";
	private String menuGubun = "";
	private String menuImg = "";
	private String frstRegistPnttm;
	private String frstRegistId;
	private String lastUpdtPnttm;
	private String lastUpusrId;
	private String centerId = "";
	private String userId = "";
	private String mode = "";
	private int menuStep01Value = 0;
	private int menuStep02Value = 0;
	private int menuStep03Value = 0;
	private int menuStep04Value = 0;
	private int menuStep05Value = 0;
	private String menuUpdate  = "";
	private String menuUseyn = "";
	
	
	
	
	public String getMenuUseyn() {
		return menuUseyn;
	}
	public void setMenuUseyn(String menuUseyn) {
		this.menuUseyn = menuUseyn;
	}
	public int getMenuStep01Value() {
		return menuStep01Value;
	}
	public void setMenuStep01Value(int menuStep01Value) {
		this.menuStep01Value = menuStep01Value;
	}
	public int getMenuStep02Value() {
		return menuStep02Value;
	}
	public void setMenuStep02Value(int menuStep02Value) {
		this.menuStep02Value = menuStep02Value;
	}
	public int getMenuStep03Value() {
		return menuStep03Value;
	}
	public void setMenuStep03Value(int menuStep03Value) {
		this.menuStep03Value = menuStep03Value;
	}
	public int getMenuStep04Value() {
		return menuStep04Value;
	}
	public void setMenuStep04Value(int menuStep04Value) {
		this.menuStep04Value = menuStep04Value;
	}
	public int getMenuStep05Value() {
		return menuStep05Value;
	}
	public void setMenuStep05Value(int menuStep05Value) {
		this.menuStep05Value = menuStep05Value;
	}
	public String getMenuUpdate() {
		return menuUpdate;
	}
	public void setMenuUpdate(String menuUpdate) {
		this.menuUpdate = menuUpdate;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getMenuSeq() {
		return menuSeq;
	}
	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}
	public String getMenuDate() {
		return menuDate;
	}
	public void setMenuDate(String menuDate) {
		this.menuDate = menuDate;
	}
	public String getMenuInfo() {
		return menuInfo;
	}
	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}
	public String getMenuGubun() {
		return menuGubun;
	}
	public void setMenuGubun(String menuGubun) {
		this.menuGubun = menuGubun;
	}
	public String getMenuImg() {
		return menuImg;
	}
	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
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
