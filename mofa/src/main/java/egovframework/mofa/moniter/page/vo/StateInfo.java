package egovframework.mofa.moniter.page.vo;

import java.io.Serializable;


public class StateInfo  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String skillNo = "";
	private String staffAgent = "";
	private String availAgent = "";
	private String auxAgent = "";
	private String busyAgent = "";
	private String acwAgent = "";
	private String inacdCalls = "";
	private String acdCalls = "";
	private String abnCalls = "";
	private String acdRate = "";
	private String waitCalls = "";
	
	
	private String agentId = "";
	private String loginTime = "";
	private String availTime = "";
	private String acdTime = "";
	private String ansTime = "";
	private String aux1Time = "";
	private String aux2Time  = "";
	private String aux3Time  = "";
	private String acwTime = "";
	private String wallCalls  = "";
	private String enWait  = "";
	private String chWait  = "";
	private String jaWait  = "";
	private String frWait  = "";
	private String ruWait  = "";
	private String spWait  = "";
	private String arWait = "";
   
	private String inCalls = "";
	private String arsCalls = "";
	private String mode = "";
	private int cnt = 0;
	
	private String inType = "";
	private String inTodaycntt = "";
	private String inYestercnt = "";
	private String typeGubun = "";
	private String typeCnt = "";
	private String typeTotcalcnt = "";
	
	
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getInTodaycntt() {
		return inTodaycntt;
	}
	public void setInTodaycntt(String inTodaycntt) {
		this.inTodaycntt = inTodaycntt;
	}
	public String getInYestercnt() {
		return inYestercnt;
	}
	public void setInYestercnt(String inYestercnt) {
		this.inYestercnt = inYestercnt;
	}
	public String getTypeGubun() {
		return typeGubun;
	}
	public void setTypeGubun(String typeGubun) {
		this.typeGubun = typeGubun;
	}
	public String getTypeCnt() {
		return typeCnt;
	}
	public void setTypeCnt(String typeCnt) {
		this.typeCnt = typeCnt;
	}
	public String getTypeTotcalcnt() {
		return typeTotcalcnt;
	}
	public void setTypeTotcalcnt(String typeTotcalcnt) {
		this.typeTotcalcnt = typeTotcalcnt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSkillNo() {
		return skillNo;
	}
	public String getInCalls() {
		return inCalls;
	}
	public void setInCalls(String inCalls) {
		this.inCalls = inCalls;
	}
	public String getArsCalls() {
		return arsCalls;
	}
	public void setArsCalls(String arsCalls) {
		this.arsCalls = arsCalls;
	}
	public void setSkillNo(String skillNo) {
		this.skillNo = skillNo;
	}
	public String getStaffAgent() {
		return staffAgent;
	}
	public void setStaffAgent(String staffAgent) {
		this.staffAgent = staffAgent;
	}
	public String getAvailAgent() {
		return availAgent;
	}
	public void setAvailAgent(String availAgent) {
		this.availAgent = availAgent;
	}
	public String getAuxAgent() {
		return auxAgent;
	}
	public void setAuxAgent(String auxAgent) {
		this.auxAgent = auxAgent;
	}
	public String getBusyAgent() {
		return busyAgent;
	}
	public void setBusyAgent(String busyAgent) {
		this.busyAgent = busyAgent;
	}
	public String getAcwAgent() {
		return acwAgent;
	}
	public void setAcwAgent(String acwAgent) {
		this.acwAgent = acwAgent;
	}
	public String getInacdCalls() {
		return inacdCalls;
	}
	public void setInacdCalls(String inacdCalls) {
		this.inacdCalls = inacdCalls;
	}
	public String getAcdCalls() {
		return acdCalls;
	}
	public void setAcdCalls(String acdCalls) {
		this.acdCalls = acdCalls;
	}
	public String getAbnCalls() {
		return abnCalls;
	}
	public void setAbnCalls(String abnCalls) {
		this.abnCalls = abnCalls;
	}
	public String getAcdRate() {
		return acdRate;
	}
	public void setAcdRate(String acdRate) {
		this.acdRate = acdRate;
	}
	public String getWaitCalls() {
		return waitCalls;
	}
	public void setWaitCalls(String waitCalls) {
		this.waitCalls = waitCalls;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentid) {
		this.agentId = agentid;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getAvailTime() {
		return availTime;
	}
	public void setAvailTime(String availTime) {
		this.availTime = availTime;
	}
	public String getAcdTime() {
		return acdTime;
	}
	public void setAcdTime(String acdTime) {
		this.acdTime = acdTime;
	}
	public String getAnsTime() {
		return ansTime;
	}
	public void setAnsTime(String ansTime) {
		this.ansTime = ansTime;
	}
	public String getAux1Time() {
		return aux1Time;
	}
	public void setAux1Time(String aux1Time) {
		this.aux1Time = aux1Time;
	}
	public String getAux2Time() {
		return aux2Time;
	}
	public void setAux2Time(String aux2Time) {
		this.aux2Time = aux2Time;
	}
	public String getAux3Time() {
		return aux3Time;
	}
	public void setAux3Time(String aux3Time) {
		this.aux3Time = aux3Time;
	}
	public String getAcwTime() {
		return acwTime;
	}
	public void setAcwTime(String acwTime) {
		this.acwTime = acwTime;
	}
	public String getWallCalls() {
		return wallCalls;
	}
	public void setWallCalls(String wallCalls) {
		this.wallCalls = wallCalls;
	}
	public String getEnWait() {
		return enWait;
	}
	public void setEnWait(String enWait) {
		this.enWait = enWait;
	}
	public String getChWait() {
		return chWait;
	}
	public void setChWait(String chWait) {
		this.chWait = chWait;
	}
	public String getJaWait() {
		return jaWait;
	}
	public void setJaWait(String jaWait) {
		this.jaWait = jaWait;
	}
	public String getFrWait() {
		return frWait;
	}
	public void setFrWait(String frWait) {
		this.frWait = frWait;
	}
	public String getRuWait() {
		return ruWait;
	}
	public void setRuWait(String ruWait) {
		this.ruWait = ruWait;
	}
	public String getSpWait() {
		return spWait;
	}
	public void setSpWait(String spWait) {
		this.spWait = spWait;
	}
	public String getArWait() {
		return arWait;
	}
	public void setArWait(String arWait) {
		this.arWait = arWait;
	}
	
	@Override
	public String toString() {
		return "StateInfo [skillNo=" + skillNo + ", staffAgent=" + staffAgent
				+ ", availAgent=" + availAgent + ", auxAgent=" + auxAgent
				+ ", busyAgent=" + busyAgent + ", acwAgent=" + acwAgent
				+ ", inacdCalls=" + inacdCalls + ", acdCalls=" + acdCalls
				+ ", abnCalls=" + abnCalls + ", acdRate=" + acdRate
				+ ", waitCalls=" + waitCalls + ", agentId=" + agentId
				+ ", loginTime=" + loginTime + ", availTime=" + availTime
				+ ", acdTime=" + acdTime + ", ansTime=" + ansTime
				+ ", aux1Time=" + aux1Time + ", aux2Time=" + aux2Time
				+ ", aux3Time=" + aux3Time + ", acwTime=" + acwTime
				+ ", wallCalls=" + wallCalls + ", enWait=" + enWait
				+ ", chWait=" + chWait + ", jaWait=" + jaWait + ", frWait="
				+ frWait + ", ruWait=" + ruWait + ", spWait=" + spWait
				+ ", arWait=" + arWait + ", inCalls=" + inCalls + ", arsCalls="
				+ arsCalls + ", getSkillNo()=" + getSkillNo()
				+ ", getInCalls()=" + getInCalls() + ", getArsCalls()="
				+ getArsCalls() + ", getStaffAgent()=" + getStaffAgent()
				+ ", getAvailAgent()=" + getAvailAgent() + ", getAuxAgent()="
				+ getAuxAgent() + ", getBusyAgent()=" + getBusyAgent()
				+ ", getAcwAgent()=" + getAcwAgent() + ", getInacdCalls()="
				+ getInacdCalls() + ", getAcdCalls()=" + getAcdCalls()
				+ ", getAbnCalls()=" + getAbnCalls() + ", getAcdRate()="
				+ getAcdRate() + ", getWaitCalls()=" + getWaitCalls()
				+ ", getAgentId()=" + getAgentId() + ", getLoginTime()="
				+ getLoginTime() + ", getAvailTime()=" + getAvailTime()
				+ ", getAcdTime()=" + getAcdTime() + ", getAnsTime()="
				+ getAnsTime() + ", getAux1Time()=" + getAux1Time()
				+ ", getAux2Time()=" + getAux2Time() + ", getAux3Time()="
				+ getAux3Time() + ", getAcwTime()=" + getAcwTime()
				+ ", getWallCalls()=" + getWallCalls() + ", getEnWait()="
				+ getEnWait() + ", getChWait()=" + getChWait()
				+ ", getJaWait()=" + getJaWait() + ", getFrWait()="
				+ getFrWait() + ", getRuWait()=" + getRuWait()
				+ ", getSpWait()=" + getSpWait() + ", getArWait()="
				+ getArWait() + "]";
	}
	
	
	
	
	
	
	
	
}
