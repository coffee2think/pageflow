package com.erl.pageflow.reply.model.vo;

import java.io.Serializable;

public class ReplyUpload implements Serializable {
	
	private static final long serialVersionUID = -1418056247957966693L;
	private int uploadId;
	private int replyId;
	private String uploadUrl;
	private String renameUrl;
	
	public ReplyUpload(int uploadId, int replyId, String uploadUrl, String renameUrl) {
		super();
		this.uploadId = uploadId;
		this.replyId = replyId;
		this.uploadUrl = uploadUrl;
		this.renameUrl = renameUrl;
	}

	public int getUploadId() {
		return uploadId;
	}

	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getRenameUrl() {
		return renameUrl;
	}

	public void setRenameUrl(String renameUrl) {
		this.renameUrl = renameUrl;
	}

	@Override
	public String toString() {
		return "ReplyUpload [uploadId=" + uploadId + ", replyId=" + replyId + ", uploadUrl=" + uploadUrl
				+ ", renameUrl=" + renameUrl + "]";
	}
	
}
