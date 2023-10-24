package com.erl.pageflow.python.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PythonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PythonController.class);
	
	@RequestMapping("runCrawling.do")
	public String runCrawlingMethod(HttpServletRequest request) throws IOException, InterruptedException {
		
		String scriptPath = request.getSession().getServletContext().getRealPath(
				"/resources/python/crawling_book_ranking.exe");
		
		ProcessBuilder pb = new ProcessBuilder(scriptPath);
		Process p = pb.start();	
	
		return "redirect:showrank.do";
	} 
	
}
