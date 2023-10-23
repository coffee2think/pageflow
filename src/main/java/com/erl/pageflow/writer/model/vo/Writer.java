package com.erl.pageflow.writer.model.vo;

import java.io.Serializable;

public class Writer implements Serializable {
	private static final long serialVersionUID = 2472594043505031700L;
	
	private int writerId;
	private String writerName;
	private String phone;
	private String writerBirth;
	private String email;
	private String address;
	private String bank;
	private String account;
	
	public Writer() {
		super();
	}

	public Writer(int writerId, String writerName, String phone, String writerBirth, String email, String bank,
			String account) {
		super();
		this.writerId = writerId;
		this.writerName = writerName;
		this.phone = phone;
		this.writerBirth = writerBirth;
		this.email = email;
		this.bank = bank;
		this.account = account;
	}

	public Writer(int writerId, String writerName, String phone, String writerBirth, String email, String address,
			String bank, String account) {
		super();
		this.writerId = writerId;
		this.writerName = writerName;
		this.phone = phone;
		this.writerBirth = writerBirth;
		this.email = email;
		this.address = address;
		this.bank = bank;
		this.account = account;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWriterBirth() {
		return writerBirth;
	}

	public void setWriterBirth(String writerBirth) {
		this.writerBirth = writerBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Writer [writerId=" + writerId + ", writerName=" + writerName + ", phone=" + phone + ", writerBirth="
				+ writerBirth + ", email=" + email + ", address=" + address + ", bank=" + bank + ", account=" + account
				+ "]";
	}
}