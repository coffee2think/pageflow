package com.erl.pageflow.common;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Paging implements Serializable{
	private static final long serialVersionUID = 5659582861753362036L;
	private static final Logger logger = LoggerFactory.getLogger(Paging.class);
	
	private int startRow;  //페이지에 출력할 시작행
	private int endRow;   //페이지에 출력할 끝행
	private int listCount;  //총 목록 갯수
	private int limit;   //한 페이지에 출력할 목록 갯수
	private int currentPage;  //출력할 현재 페이지
	private int maxPage;   //총 페이지 수
	private int startPage;   //페이지 그룹의 시작값
	private int endPage;   //페이지 그룹의 끝값
	private String urlMapping;
	
	public Paging(int listCount, int currentPage, int limit, String urlMapping) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.limit = limit;
		this.urlMapping = urlMapping;
	}
	
	public void calculator() {
		//int currentPage = 11; // The current page number
        int totalPages = listCount; // The total number of pages
        int pageSize = limit; // The number of items per page
        // Calculate the max page
        maxPage = (int) Math.ceil((double) totalPages / pageSize);

        // Calculate the start page and end page
        int pageRange = 10; // Number of pages to display in the pagination bar

        if (maxPage <= pageRange) {
            startPage = 1;
            endPage = maxPage;
        } else {
            int halfRange = pageRange / 2;
            if (currentPage <= halfRange) {
                startPage = 1;
                endPage = pageRange;
            } else if (currentPage + halfRange >= maxPage) {
                startPage = maxPage - pageRange + 1;
                endPage = maxPage;
            } else {
                startPage = currentPage - halfRange;
                endPage = currentPage + halfRange;
            }
        }

        // Calculate the start row and end row
        startRow = (currentPage - 1) * pageSize + 1;
        endRow = Math.min(currentPage * pageSize, totalPages);
        /*
        System.out.println("Current Page: " + currentPage);
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Page Size: " + pageSize);
        System.out.println("Max Page: " + maxPage);
        System.out.println("Start Page: " + startPage);
        System.out.println("End Page: " + endPage);
        System.out.println("Start Row: " + startRow);
        System.out.println("End Row: " + endRow);
        */
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUrlMapping() {
		return urlMapping;
	}

	public void setUrlMapping(String urlMapping) {
		this.urlMapping = urlMapping;
	}

	@Override
	public String toString() {
		return "Paging [startRow=" + startRow + ", endRow=" + endRow + ", listCount=" + listCount + ", limit=" + limit
				+ ", currentPage=" + currentPage + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", urlMapping=" + urlMapping + "]";
	}
}
