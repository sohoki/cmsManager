package aten.com.backoffice.sts.message.vo;

import java.io.Serializable;

public class SendMessageInfo implements  Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mode;
	private String sendMsgSeq = "";
	private String schCode = "";
	private String agentCode = "";
	private String msgSend = "";
	private String msgSendRegdate = "";
	private String msgRecCheck = "";
	private String msgRecUpdate = "";
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSendMsgSeq() {
		return sendMsgSeq;
	}
	public void setSendMsgSeq(String sendMsgSeq) {
		this.sendMsgSeq = sendMsgSeq;
	}
	public String getSchCode() {
		return schCode;
	}
	public void setSchCode(String schCode) {
		this.schCode = schCode;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getMsgSend() {
		return msgSend;
	}
	public void setMsgSend(String msgSend) {
		this.msgSend = msgSend;
	}
	public String getMsgSendRegdate() {
		return msgSendRegdate;
	}
	public void setMsgSendRegdate(String msgSendRegdate) {
		this.msgSendRegdate = msgSendRegdate;
	}
	public String getMsgRecCheck() {
		return msgRecCheck;
	}
	public void setMsgRecCheck(String msgRecCheck) {
		this.msgRecCheck = msgRecCheck;
	}
	public String getMsgRecUpdate() {
		return msgRecUpdate;
	}
	public void setMsgRecUpdate(String msgRecUpdate) {
		this.msgRecUpdate = msgRecUpdate;
	}
	@Override
	public String toString() {
		return "SendMessageInfo [mode=" + mode + ", sendMsgSeq=" + sendMsgSeq
				+ ", schCode=" + schCode + ", agentCode=" + agentCode
				+ ", msgSend=" + msgSend + ", msgSendRegdate=" + msgSendRegdate
				+ ", msgRecCheck=" + msgRecCheck + ", msgRecUpdate="
				+ msgRecUpdate + ", getMode()=" + getMode()
				+ ", getSendMsgSeq()=" + getSendMsgSeq() + ", getSchCode()="
				+ getSchCode() + ", getAgentCode()=" + getAgentCode()
				+ ", getMsgSend()=" + getMsgSend() + ", getMsgSendRegdate()="
				+ getMsgSendRegdate() + ", getMsgRecCheck()="
				+ getMsgRecCheck() + ", getMsgRecUpdate()=" + getMsgRecUpdate()
				+ "]";
	}
	
	
	
	
	
	
}
