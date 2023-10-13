package com.erl.pageflow.common;

import java.io.Serializable;

public class UploadKeyword implements Serializable{
	
	private static final long serialVersionUID = -685130663186568559L;
	private String originFile;
	private String renameFile;
	private int replyId;
	
	public UploadKeyword(int replyId, String originFile, String renameFile) {
		super();
		this.originFile = originFile;
		this.renameFile = renameFile;
		this.replyId = replyId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	
	public String getOriginFile() {
		return originFile;
	}

	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}

	public String getRenameFile() {
		return renameFile;
	}

	public void setRenameFile(String renameFile) {
		this.renameFile = renameFile;
	}
	
	@Override
	public String toString() {
		return "UploadKeyword [originFile=" + originFile + ", renameFile=" + renameFile + "]";
	}
	
}
