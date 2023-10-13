package com.erl.pageflow.sales.model.vo;

import java.sql.Date;

public class BookStore extends Client {

	private static final long serialVersionUID = 8460004124769364347L;
	
	public BookStore() {}
	
	public BookStore(int clientId, String category, String clientName, String clientAddress, String clientContact,
			String eid, Date startDate) {
		super(clientId, category, clientName, clientAddress, clientContact, eid, startDate);
	}
	
	public BookStore(int clientId, String category, String clientName, String clientAddress, String clientContact,
			String eid, String clientUrl, String manager, String managerContact, String managerEmail, Date startDate,
			Date endDate) {
		super(clientId, category, clientName, clientAddress, clientContact, eid, clientUrl, manager, managerContact, 
			managerEmail, startDate, endDate);
	}
	
	@Override
	public String toString() {
		return "BookStore [clientId=" + super.getClientId() + ", category=" + super.getCategory() + ", clientName=" + super.getClientName()
				+ ", clientAddress=" + super.getClientAddress() + ", clientContact=" + super.getClientContact() + ", eid=" + super.getEid()
				+ ", clientUrl=" + super.getClientUrl() + ", manager=" + super.getManager() + ", managerContact=" + super.getManagerContact()
				+ ", managerEmail=" + super.getManagerEmail() + ", startDate=" + super.getStartDate() + ", endDate=" + super.getEndDate() + "]";
	}
	
}
