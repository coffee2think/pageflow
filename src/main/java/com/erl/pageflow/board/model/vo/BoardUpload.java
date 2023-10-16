package com.erl.pageflow.board.model.vo;

import java.io.Serializable;

public class BoardUpload implements Serializable {
	
	private static final long serialVersionUID = -3702893423191583695L;
	private int uploadId;
	private int depId;
	private int boardId;
	private String uploadUrl;
	private String renameUrl;
	
	public BoardUpload(int uploadId, int depId, int boardId, String uploadUrl, String renameUrl) {
		super();
		this.uploadId = uploadId;
		this.depId = depId;
		this.boardId = boardId;
		this.uploadUrl = uploadUrl;
		this.renameUrl = renameUrl;
	}

	public int getUploadId() {
		return uploadId;
	}

	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
		return "BoardUpload [uploadId=" + uploadId + ", depId=" + depId + ", boardId=" + boardId + ", uploadUrl="
				+ uploadUrl + ", renameUrl=" + renameUrl + "]";
	}
	
	
	
}
