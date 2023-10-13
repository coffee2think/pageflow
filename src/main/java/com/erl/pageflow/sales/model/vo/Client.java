package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Client implements Serializable {

	private static final long serialVersionUID = -2591897682453932175L;
	
	private int clientId;
	private String category;
	private String clientName;
	private String clientAddress;
	private String clientContact;
	private String eid;
	private String clientUrl;
	private String manager;
	private String managerContact;
	private String managerEmail;
	private Date startDate;
	private Date endDate;
	
	public Client() {
	}

	public Client(int clientId, String category, String clientName, String clientAddress, String clientContact,
			String eid, Date startDate) {
		this.clientId = clientId;
		this.category = category;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.clientContact = clientContact;
		this.eid = eid;
		this.startDate = startDate;
	}

	public Client(int clientId, String category, String clientName, String clientAddress, String clientContact,
			String eid, String clientUrl, String manager, String managerContact, String managerEmail, Date startDate,
			Date endDate) {
		this.clientId = clientId;
		this.category = category;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.clientContact = clientContact;
		this.eid = eid;
		this.clientUrl = clientUrl;
		this.manager = manager;
		this.managerContact = managerContact;
		this.managerEmail = managerEmail;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientContact() {
		return clientContact;
	}

	public void setClientContact(String clientContact) {
		this.clientContact = clientContact;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerContact() {
		return managerContact;
	}

	public void setManagerContact(String managerContact) {
		this.managerContact = managerContact;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", category=" + category + ", clientName=" + clientName
				+ ", clientAddress=" + clientAddress + ", clientContact=" + clientContact + ", eid=" + eid
				+ ", clientUrl=" + clientUrl + ", manager=" + manager + ", managerContact=" + managerContact
				+ ", managerEmail=" + managerEmail + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
